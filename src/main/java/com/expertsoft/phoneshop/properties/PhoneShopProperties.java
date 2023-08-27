package com.expertsoft.phoneshop.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "phoneshop")
public class PhoneShopProperties {
    private int plpMaxPages;
    private int plpEntityPageQuantity;

    public int getPlpMaxPages() {
        return plpMaxPages;
    }

    public void setPlpMaxPages(int plpMaxPages) {
        this.plpMaxPages = plpMaxPages;
    }

    public int getPlpEntityPageQuantity() {
        return plpEntityPageQuantity;
    }

    public void setPlpEntityPageQuantity(int plpEntityPageQuantity) {
        this.plpEntityPageQuantity = plpEntityPageQuantity;
    }
}
