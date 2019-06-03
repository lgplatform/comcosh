package com.example.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class Login {

	@GetMapping("/login")
	public String loginPage(Model model){

		return "login";
	}


}
