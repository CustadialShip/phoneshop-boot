package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.service.PhoneService;
import com.expertsoft.phoneshop.service.PhoneShopUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneDetailsPageController {

    private static final String PHONE_DETAILS_PAGE = "phoneDetailsPage";
    private static final String PHONE = "phone";
    private static final String DISPLAY_NAME = "displayName";

    @Resource
    private PhoneShopUserService userService;

    @Resource
    private PhoneService phoneService;

    @GetMapping( "/{phoneId}")
    public String getPhoneDetails(@PathVariable("phoneId") Long phoneId, Model model) {
        Phone phone = phoneService.getPhoneById(phoneId);
        model.addAttribute(PHONE, phone);

        return PHONE_DETAILS_PAGE;
    }

    @ModelAttribute(DISPLAY_NAME)
    public String getCurrentUserName() {
        return userService.getCurrentUserDisplayName();
    }
}
