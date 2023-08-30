package com.expertsoft.phoneshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneShopUserDto {

    private Long id;
    private String name;
    private String location;
    private String createdDate;
}
