package com.expertsoft.phoneshop.dto.populator;

import com.expertsoft.phoneshop.dto.PhoneShopUserDto;
import com.expertsoft.phoneshop.persistence.model.user.PhoneShopUser;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDtoPopulator implements Function<PhoneShopUser, PhoneShopUserDto> {

    private static final String UNKNOWN = "unknown";

    @Override
    public PhoneShopUserDto apply(PhoneShopUser phoneShopUser) {
        if (phoneShopUser == null) {
            throw new IllegalArgumentException("PhoneShopUser must not be null");
        }
        PhoneShopUserDto userDto = new PhoneShopUserDto();
        userDto.setName(phoneShopUser.getUsername());
        userDto.setLocation(phoneShopUser.getLocation());
        userDto.setId(phoneShopUser.getId());

        userDto.setCreatedDate(phoneShopUser.getCreatedDate() == null ? UNKNOWN : phoneShopUser.getCreatedDate());

        return userDto;
    }
}
