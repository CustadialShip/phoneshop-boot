package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.dto.SearchFormDto;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.properties.PhoneShopProperties;
import com.expertsoft.phoneshop.service.PhoneService;
import com.expertsoft.phoneshop.service.PhoneShopUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneListPageController {

    private static final String PHONE_LIST_PAGE = "phoneListPage";
    private static final String PHONES = "phones";
    private static final String MAX_PAGES = "maxPages";
    private static final String SEARCH_FORM = "searchForm";
    private static final String DISPLAY_NAME = "displayName";

    @Resource
    private PhoneService phoneService;
    @Resource
    private PhoneShopProperties phoneShopProperties;
    @Resource
    private PhoneShopUserService userService;

    @GetMapping
    public String getPhoneList(@ModelAttribute SearchFormDto searchFormDto,
                               Pageable pageable, Model model) {
        Page<Phone> phones = phoneService.searchByQueryAndPrice(pageable, searchFormDto);
        model.addAttribute(PHONES, phones);
        model.addAttribute(SEARCH_FORM, searchFormDto);

        return PHONE_LIST_PAGE;
    }

    @ModelAttribute(MAX_PAGES)
    public int setMaxPagesAttribute() {
        return phoneShopProperties.getPlpMaxPages();
    }

    @ModelAttribute(DISPLAY_NAME)
    public String getCurrentUserName() {
        return userService.getCurrentUserDisplayName();
    }
}
