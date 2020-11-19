/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/19.
 */


package com.deva.spring.oauth2.entity;

import com.deva.spring.oauth2.dto.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Users extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column
	private String picture;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleType role;

	@Builder
	public Users(String name, String mail, String picture, RoleType role) {
		this.name = name;
		this.email = mail;
		this.picture = picture;
		this.role = role;
	}

	public Users update(String name, String picture){
		this.name = name;
		this.picture = picture;
		return this;
	}

	public String getRoleKey(){
		return this.role.getRoleName();
	}


}
