package com.exam.sbb;

import com.exam.sbb.user.SiteUser;
import com.exam.sbb.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    /*
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint()
                .getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String oauthId = oAuth2User.getName();
        SiteUser siteUser = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();
        if (!"KAKAO".equals(oauthType)) {
            throw new OAuthTypeMatchNotFoundException();
        }
        if (isNew(oauthType, oauthId)) {
            switch (oauthType) {
                case "KAKAO" -> {
                    Map attributesProperties = (Map) attributes.get("properties");
                    Map attributesKakaoAcount = (Map) attributes.get("kakao_account");
                    String nickname = (String) attributesProperties.get("nickname");
                    String email = "%s@kakao.com".formatted(oauthId);
                    String username = "KAKAO_%s".formatted(oauthId);
                    if ((boolean) attributesKakaoAcount.get("has_email")) {
                        email = (String) attributesKakaoAcount.get("email");
                    }
                  siteUser = SiteUser.builder()
                            .email(email)
                            .username(username)
                            .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                            .build();
                    memberRepository.save(member);
                }
            }
        } else {
            member = memberRepository.findByUsername("%s_%s".formatted(oauthType, oauthId))
                    .orElseThrow(MemberNotFoundException::new);
        }
        Set<GrantedAuthority> authorities = new LinkedHashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new MemberContext(member, authorities, attributes, userNameAttributeName);
    }
     */

    /*
    private boolean isNew(String oAuthType, String oAuthId) {
        return UserRepository.findByUsername("%s_%s".formatted(oAuthType, oAuthId)).isEmpty();
    }
     */
}
