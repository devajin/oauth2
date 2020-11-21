/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/19.
 */


package com.deva.spring.oauth2.dto;

import com.deva.spring.oauth2.entity.Users;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
public class SessionUser implements Serializable {
	private String name;
	private String email;
	private String picture;

	public SessionUser(Users users) {
		this.name = users.getName();
		this.email = users.getEmail();
		this.picture = users.getPicture();
	}
}
