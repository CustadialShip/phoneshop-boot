package com.expertsoft.phoneshop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Getter
@Setter
public class SearchFormDto {

    private String searchQuery;
    @DecimalMin("0")
    private BigDecimal fromPrice;
    @DecimalMin("0")
    private BigDecimal toPrice;
}
