/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/19.
 */


package com.deva.spring.oauth2.config;

import com.deva.spring.oauth2.dto.OAuthAttributes;
import com.deva.spring.oauth2.dto.SessionUser;
import com.deva.spring.oauth2.entity.Users;
import com.deva.spring.oauth2.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UsersRepository usersRepository;
	private final HttpSession httpSession;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);

		String registraionId = oAuth2UserRequest.getClientRegistration().getRegistrationId();
		String userNameAttributeName = oAuth2UserRequest.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		OAuthAttributes authAttributes = OAuthAttributes.of(registraionId, userNameAttributeName, oAuth2User.getAttributes());
		log.info(authAttributes.toString());

		Users users = saveOrUpdate(authAttributes);
		httpSession.setAttribute("user", new SessionUser(users));

		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(users.getRoleKey())),
				authAttributes.getAttributes(),
				authAttributes.getNameAttributeKey());
	}

	private Users saveOrUpdate(OAuthAttributes authAttributes){
		Users users = usersRepository.findByEmail(authAttributes.getEmail())
				.map(entity -> entity.update(authAttributes.getName(), authAttributes.getPicture()))
				.orElse(authAttributes.toEntity());
		return usersRepository.save(users);
	}

}
