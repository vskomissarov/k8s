package com.vkomissarov.customer.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class CustomerOrderException extends RuntimeException {
    public CustomerOrderException(final String uuid, final String reason) {
        super(String.format("%s Customer Microservice Error %s For Order UUID: %s, Customer Microservice Message: %s",
                ErrorConstants.CUSTOMER_MICROSERVICE, INTERNAL_SERVER_ERROR,  uuid, reason));
    }

    public CustomerOrderException(final String uuid, final int response) {
        super(String.format("%s Customer Microservice Bad Request %s For Order UUID: %s,Customer Microservice Response: %s",
                ErrorConstants.CUSTOMER_MICROSERVICE, BAD_REQUEST,  uuid, response));
    }
}
