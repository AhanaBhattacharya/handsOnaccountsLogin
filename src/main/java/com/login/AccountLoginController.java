package com.login;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class AccountLoginController {
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	MyUserDetailsService serv;
	
	@Autowired
	userRepository repo;
	//@ResponseBody
	@GetMapping("/order/{name}")
	public String login(@PathVariable String name) {
		Response drequest =  serv.createResponse(name, repo.findByName(name).getToken());
		System.out.println("request is"+drequest);
		String url = "http://localhost:8082/order/"+repo.findByName(name).getToken();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Response> dre = new HttpEntity<Response>(drequest);
		ResponseEntity entity=restTemplate.getForEntity(url, String.class);
		System.out.println(entity.getBody());
		System.out.println(entity.getStatusCode());
		if(entity.getStatusCodeValue()==200) {
			return "wow";
		}
		return "hello";
	}
	
	@GetMapping("/login")
	public String loginn() {
		return "index";
		
	}
	
	/*@GetMapping("/login/oauth2/code/google")
	public String log() {
		return "hey";
	}*/

}
