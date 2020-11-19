package com.deva.spring.oauth2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class FrontEndCotroller {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("message", "thymeleaf Hello, World!!!");
        log.info(".................... thymeleaf");
        return "index";
    }


    @GetMapping("/static")
    public String index(Model model){
        model.addAttribute("message", "static Hello, World!!!");
        log.info(".................... static");
        return "index.html";
    }
}
