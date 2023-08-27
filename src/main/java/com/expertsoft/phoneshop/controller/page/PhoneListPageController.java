package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneListPageController {

    private static final String PHONE_LIST_PAGE = "phoneListPage";
    private static final String PHONES = "phones";

    @Resource
    private PhoneService phoneService;

    @GetMapping
    public String getPhoneList(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model) {
        Page<Phone> phonePage = phoneService.getPhonesPage(PageRequest.of(page, size));
        model.addAttribute(PHONES, phonePage);

        return PHONE_LIST_PAGE;
    }
}
