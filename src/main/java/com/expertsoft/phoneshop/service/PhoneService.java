package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.dto.SearchFormDto;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import org.springframework.data.domain.Page;
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

    public Page<Phone> getPhonesPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    public Phone getPhoneById(Long id) {
        return phoneRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<Phone> searchByQueryAndPrice(Pageable pageable, SearchFormDto searchFormDto) {
        BigDecimal fromPrice = Optional.ofNullable(searchFormDto.getFromPrice())
                .orElse(BigDecimal.ZERO);
        BigDecimal toPrice = Optional.ofNullable(searchFormDto.getToPrice())
                .orElse(BigDecimal.valueOf(Integer.MAX_VALUE));
        String searchQuery = Optional.ofNullable(searchFormDto.getSearchQuery())
                .orElse("");
        return phoneRepository.searchByQueryAndPrice(searchQuery, fromPrice, toPrice, pageable);
    }
}
