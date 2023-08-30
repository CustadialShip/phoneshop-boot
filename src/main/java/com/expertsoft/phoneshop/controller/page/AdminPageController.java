package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.dto.PhoneShopUserDto;
import com.expertsoft.phoneshop.properties.PhoneShopProperties;
import com.expertsoft.phoneshop.service.PhoneShopUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.ADMIN_PATH;

@Controller
@RequestMapping(ADMIN_PATH)
public class AdminPageController {

    private static final String DISPLAY_NAME = "displayName";
    private static final String ADMIN_PAGE = "admin/adminPanelPage";
    private static final String USERS = "users";
    private static final String MAX_PAGES = "maxPages";

    @Resource
    private PhoneShopUserService userService;

    @Resource
    private PhoneShopProperties phoneShopProperties;

    @GetMapping
    public String getAdminPanel(Pageable pageable, Model model) {
        Page<PhoneShopUserDto> users = userService.getAllRegisteredUsers(pageable);
        model.addAttribute(USERS, users);
        return ADMIN_PAGE;
    }

    @ModelAttribute(DISPLAY_NAME)
    public String getCurrentUserName() {
        return userService.getCurrentUserDisplayName();
    }

    @ModelAttribute(MAX_PAGES)
    public int setMaxPagesAttribute() {
        return phoneShopProperties.getAdminMaxPages();
    }
}
