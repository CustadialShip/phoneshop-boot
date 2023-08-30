package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.dto.PhoneShopUserDto;
import com.expertsoft.phoneshop.dto.populator.UserDtoPopulator;
import com.expertsoft.phoneshop.persistence.model.user.GitHubOAuth2User;
import com.expertsoft.phoneshop.persistence.model.user.PhoneShopUser;
import com.expertsoft.phoneshop.persistence.model.user.Role;
import com.expertsoft.phoneshop.persistence.repository.PhoneShopUserRepository;
import com.expertsoft.phoneshop.properties.PhoneShopProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PhoneShopUserService implements UserDetailsService {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String GUEST = "guest";

    @Resource
    private PhoneShopProperties phoneShopProperties;

    @Resource
    private PhoneShopUserRepository phoneShopUserRepository;

    @Resource
    private UserDtoPopulator userDtoPopulator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return phoneShopUserRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public Page<PhoneShopUser> getUsersPage(Pageable pageable) {
        return phoneShopUserRepository.findAll(pageable);
    }

    public void updateUser(GitHubOAuth2User gitHubOAuth2User) {
        PhoneShopUser phoneShopUser = phoneShopUserRepository
                .findByUsername(gitHubOAuth2User.getLogin()).orElseGet(PhoneShopUser::new);
        phoneShopUser.setUsername(gitHubOAuth2User.getLogin());
        phoneShopUser.setName(gitHubOAuth2User.getName());
        phoneShopUser.setBio(gitHubOAuth2User.getBio());
        phoneShopUser.setAvatarUrl(gitHubOAuth2User.getAvatarUrl());
        phoneShopUser.setCompany(gitHubOAuth2User.getCompany());
        phoneShopUser.setLocation(gitHubOAuth2User.getLocation());
        phoneShopUser.setRole(Role.ROLE_USER);
        if (phoneShopUser.getCreatedDate() == null) {
            phoneShopUser.setCreatedDate(getCurrentTime());
        }
        phoneShopUserRepository.save(phoneShopUser);
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
    }

    public String getCurrentUserDisplayName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return GUEST;
        }
        return authentication.getName();
    }

    public Page<PhoneShopUserDto> getAllRegisteredUsers(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), phoneShopProperties.getAdminUserPageQuantity());
        return phoneShopUserRepository.findAll(pageable).map(userDtoPopulator);
    }
}
