package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.dto.SearchFormDto;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.properties.PhoneShopProperties;
import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneListPageController {

    private static final String PHONE_LIST_PAGE = "phoneListPage";
    private static final String PHONES = "phones";
    private static final String MAX_PAGES = "maxPages";
    private static final String SEARCH_FORM = "searchForm";

    @Resource
    private PhoneService phoneService;
    @Resource
    private PhoneShopProperties phoneShopProperties;

    @GetMapping
    public String getPhoneList(@ModelAttribute SearchFormDto searchFormDto,
                               @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Phone> phones = phoneService
                .searchByQueryAndPrice(PageRequest.of(page, phoneShopProperties.getPlpEntityPageQuantity()), searchFormDto);
        model.addAttribute(PHONES, phones);
        model.addAttribute(SEARCH_FORM, searchFormDto);

        return PHONE_LIST_PAGE;
    }

    @ModelAttribute
    public void setMaxPagesAttribute(Model model) {
        model.addAttribute(MAX_PAGES, phoneShopProperties.getPlpMaxPages());
    }
}
