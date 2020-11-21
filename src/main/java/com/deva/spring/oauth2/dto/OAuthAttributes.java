/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/19.
 */


package com.deva.spring.oauth2.dto;

import com.deva.spring.oauth2.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
		if("naver".equals(registrationId)){
			return ofNaver(userNameAttributeName, attributes);
		}

		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
		return OAuthAttributes.builder()
				.name(String.valueOf(attributes.get("name")))
				.email(String.valueOf(attributes.get("email")))
				.picture(String.valueOf(attributes.get("picture")))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes ){
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");
		return OAuthAttributes.builder()
				.name(String.valueOf(response.get("name")))
				.email(String.valueOf(response.get("email")))
				.picture(String.valueOf(response.get("profile_image")))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}


	public Users toEntity(){
		return Users.builder()
				.name(name)
				.mail(email)
				.picture(picture)
				.role(RoleType.GUEST)
				.build();

	}

}
