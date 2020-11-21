/**
 * project name : oauth2
 * Created by deva.jin on 2020/11/19.
 */


package com.deva.spring.oauth2.controller;

import com.deva.spring.oauth2.dto.LoginUser;
import com.deva.spring.oauth2.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

	private final HttpSession httpSession;

	@GetMapping("/")
	public String login(Model model) throws Exception{
		return "login";
	}

	@GetMapping("/index")
	public String index(Model model, @LoginUser SessionUser user) throws Exception {
		if(!ObjectUtils.isEmpty(user)){
			log.info(user.toString());
			model.addAttribute("user", user);
		}
		return "index";
	}
}
