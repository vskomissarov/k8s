package com.vkomissarov.customer.service;

import com.vkomissarov.customer.data.Customer;
import com.vkomissarov.customer.dto.CustomerDto;
import com.vkomissarov.customer.exceptions.EntityNotFoundException;
import com.vkomissarov.customer.mappers.CustomerMapper;
import com.vkomissarov.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vkomissarov.customer.utils.StringUtils.validate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public ResponseEntity<CustomerDto> save(CustomerDto dto) throws URISyntaxException {

        var customer = Optional.ofNullable(dto.getId()).map(customerId ->
                        customerRepository.findById(customerId)
                                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId)))
                .orElseGet(Customer::new);

        customerMapper.updateCustomerFromDto(dto, customer);

        final var result = customerRepository.save(customer);

        return dto.getId() == null ?
                ResponseEntity.created(new URI("/api/customers/" + result.getId())).body(dto) :
                ResponseEntity.ok().body(dto);

    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<CustomerDto> findById(String customerId) {
        validate(customerId);
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }

    public ResponseEntity<Void> deleteById(String customerId) {
        validate(customerId);

        customerRepository.deleteById(customerId);

        return ResponseEntity.noContent().build();
    }
}
