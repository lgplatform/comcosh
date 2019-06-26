package com.example.common.controller;

import com.example.common.dto.line.LineProfileDTO;
import com.example.common.dto.line.LineTokenDTO;
import com.example.common.utils.CommonUtils;
import com.example.common.dto.line.LineLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/v1/basic")
@Slf4j
public class Welcome {

	@Autowired
	@Qualifier("commonRestTemplate")
	private RestTemplate restTemplate;

	@Value("${line.chanel.id}")
	private String lineChanelId;

	@Value("${line.secret}")
	private String lineSecret;

	private final String GET_LINE_PROFILE_INFO_URI = "https://api.line.me/v2/profile";

	private final String GET_LINE_TOKEN_INFO_URI = "https://api.line.me/oauth2/v2.1/token";

	@GetMapping("/welcome")
	public String welcomePage(@Valid LineLoginDTO.Res res, BindingResult result) throws UnsupportedEncodingException {

		if(result.hasErrors()){
			log.error("##### {}", CommonUtils.getErrMsgValidAnnotation(result, "\n"));
		}

		//Set the headers you need send
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		//Create a new HttpEntity
		MultiValueMap<String, String> map =	new LinkedMultiValueMap<>();
		map.add("grant_type", "authorization_code");
		map.add("code", res.getCode());
		map.add("redirect_uri", "http://localhost:18080/v1/basic/welcome");
		map.add("client_id", lineChanelId);
		map.add("client_secret", lineSecret);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<LineTokenDTO.Res> tokenRes = null;
		ResponseEntity<LineProfileDTO.Res> profileRes = null;

		try{
			tokenRes = restTemplate.exchange(GET_LINE_TOKEN_INFO_URI, HttpMethod.POST, entity, LineTokenDTO.Res.class);

			headers = new HttpHeaders();
			headers.set("Authorization", tokenRes.getBody().getTokenType() + " " + tokenRes.getBody().getAccessToken());
			HttpEntity<String> entity2 = new HttpEntity<>(headers);

			profileRes = restTemplate.exchange(GET_LINE_PROFILE_INFO_URI, HttpMethod.GET, entity2, LineProfileDTO.Res.class);


		} catch (HttpClientErrorException e){
			log.error("{}", e.getMessage());
		}

		if(tokenRes != null){
			log.info("token data : {}", tokenRes.getBody());
		}

		if(profileRes != null){
			log.info("profile data : {} ", profileRes.getBody());
		}

		return "redirect:" + "/v1/basic/main?displayName=" + URLEncoder.encode(profileRes.getBody().getDisplayName(), "UTF-8");
	}

	@GetMapping("/main")
	public String mainPage(@RequestParam("displayName") String displayName, Model model){

		model.addAttribute("displayName", displayName);

		return "welcome";
	}
}
