package com.login;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userDetailsModel {
	@Id
	@GeneratedValue
	private Integer id;
   private String name;
   private String emailId;
   private String passWord="password";
   private String token;
public userDetailsModel(String name, String emailId, String passWord,String jwtt) {
	super();
	this.name = name;
	this.emailId = emailId;
	this.passWord = passWord;
	this.token=jwtt;
}
    
}
