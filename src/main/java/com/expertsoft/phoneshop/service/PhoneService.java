package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.dto.SearchFormDto;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.properties.PhoneShopProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PhoneService {

    @Resource
    private PhoneRepository phoneRepository;

    @Resource
    private PhoneShopProperties phoneShopProperties;

    public Page<Phone> getPhonesPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    public Phone getPhoneById(Long id) {
        return phoneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<Phone> searchByQueryAndPrice(Pageable pageable, SearchFormDto searchFormDto) {
        pageable = PageRequest.of(pageable.getPageNumber(), phoneShopProperties.getPlpEntityPageQuantity(),
                pageable.getSort());
        BigDecimal fromPrice = Optional.ofNullable(searchFormDto.getFromPrice())
                .orElse(BigDecimal.ZERO);
        BigDecimal toPrice = Optional.ofNullable(searchFormDto.getToPrice())
                .orElse(BigDecimal.valueOf(Integer.MAX_VALUE));
        String searchQuery = Optional.ofNullable(searchFormDto.getSearchQuery())
                .orElse("");
        return phoneRepository.searchByQueryAndPrice(searchQuery, fromPrice, toPrice, pageable);
    }
}
