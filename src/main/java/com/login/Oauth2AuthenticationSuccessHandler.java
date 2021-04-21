package com.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;





@Component("oauth2authSuccessHandler")

public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private  RedirectStrategy redirectStrategy=  new DefaultRedirectStrategy();;

	
	@Autowired
	private  PasswordEncoder encoder;
	
	@Autowired
	userRepository repo;
	
	@Autowired
	MyUserDetailsService serv;
	 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		JwtTokenUtil jwt = new JwtTokenUtil();
		OAuth2AuthenticationToken oToken = (OAuth2AuthenticationToken)authentication;
		String firstName = oToken.getPrincipal().getAttributes().get("given_name").toString();
		String lastName = oToken.getPrincipal().getAttributes().get("family_name").toString();
		String email = oToken.getPrincipal().getAttributes().get("email").toString();
		String jwtt = jwt.generateToken(firstName);
		repo.save(new userDetailsModel(firstName, email, "pass",jwtt));
		this.redirectStrategy.sendRedirect(request, response, "/order/"+firstName);
	
	}
}

