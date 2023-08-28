package com.expertsoft.phoneshop.dto;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class SearchFormDto {
    private String searchQuery;

    @DecimalMin("0")
    private BigDecimal fromPrice;

    @DecimalMin("0")
    private BigDecimal toPrice;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public BigDecimal getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(BigDecimal fromPrice) {
        this.fromPrice = fromPrice;
    }

    public BigDecimal getToPrice() {
        return toPrice;
    }

    public void setToPrice(BigDecimal toPrice) {
        this.toPrice = toPrice;
    }
}
