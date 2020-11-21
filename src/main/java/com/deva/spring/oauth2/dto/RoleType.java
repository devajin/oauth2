package com.deva.spring.oauth2.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

	GUEST("ROLE_GUEST", "GUEST", 0),
	USER("ROLE_USER", "USER", 1),
	ADMIN("ROLE_ADMIN","ADMIN", 100);


	private final String key;
	private final String roleName;
	private final int code;
}
