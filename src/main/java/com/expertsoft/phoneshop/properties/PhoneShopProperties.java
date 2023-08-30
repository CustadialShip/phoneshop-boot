package com.expertsoft.phoneshop.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "phoneshop")
@Getter
@Setter
public class PhoneShopProperties {

    private int plpMaxPages;
    private int adminMaxPages;
    private int plpEntityPageQuantity;
    private int adminUserPageQuantity;
}
