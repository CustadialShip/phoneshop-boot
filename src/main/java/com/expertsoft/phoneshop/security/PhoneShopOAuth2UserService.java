package com.expertsoft.phoneshop.security;

import com.expertsoft.phoneshop.persistence.model.user.GitHubOAuth2User;
import com.expertsoft.phoneshop.service.PhoneShopUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PhoneShopOAuth2UserService extends DefaultOAuth2UserService {

    @Resource
    private PhoneShopUserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        GitHubOAuth2User gitHubOAuth2User = new GitHubOAuth2User(oAuth2User);

        userService.updateUser(gitHubOAuth2User);
        return gitHubOAuth2User;
    }
}
