package com.example.common.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/basic")
public class Welcome {

	@GetMapping("/welcome")
//	@Secured(value = {"ADMIN"})
	public String welcomePage(Model model){

		return "welcome";
	}
}
