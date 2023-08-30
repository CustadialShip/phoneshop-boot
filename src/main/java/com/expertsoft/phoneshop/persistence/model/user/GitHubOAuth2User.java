package com.expertsoft.phoneshop.persistence.model.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.expertsoft.phoneshop.PhoneShopConstants.AVATAR_URL;
import static com.expertsoft.phoneshop.PhoneShopConstants.BIO;
import static com.expertsoft.phoneshop.PhoneShopConstants.COMPANY;
import static com.expertsoft.phoneshop.PhoneShopConstants.LOCATION;
import static com.expertsoft.phoneshop.PhoneShopConstants.LOGIN;
import static com.expertsoft.phoneshop.PhoneShopConstants.NAME;

@Getter
@Setter
public class GitHubOAuth2User implements OAuth2User {

    private String login;
    private String name;
    private String bio;
    private String avatarUrl;
    private String location;
    private String company;
    private Map<String, Object> attributes;

    public GitHubOAuth2User(OAuth2User oAuth2User) {
        this.login = oAuth2User.getAttribute(LOGIN);
        this.name = oAuth2User.getAttribute(NAME);
        this.bio = oAuth2User.getAttribute(BIO);
        this.avatarUrl = oAuth2User.getAttribute(AVATAR_URL);
        this.location = oAuth2User.getAttribute(LOCATION);
        this.company = oAuth2User.getAttribute(COMPANY);
    }

    @Override
    public Map<String, Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap<>();
            this.attributes.put(LOGIN, this.login);
            this.attributes.put(NAME, this.name);
            this.attributes.put(BIO, this.bio);
            this.attributes.put(AVATAR_URL, this.avatarUrl);
            this.attributes.put(LOCATION, this.location);
            this.attributes.put(COMPANY, this.company);
        }
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getName() {
        return name;
    }
}
