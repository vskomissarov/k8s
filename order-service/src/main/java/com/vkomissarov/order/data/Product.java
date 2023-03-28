package com.vkomissarov.order.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    private String id;

    private String name;

    private String description;

    private String modelNumber;

    private String manufacturerName;

    private Double price;

    private String detailInfo;

    private String imageUrl;

    private Integer quantity;
}