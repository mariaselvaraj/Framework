package pages;

import wrappers.GenericWrappers;

public class LoginPage extends GenericWrappers{

	public LoginPage(){
		
	}
	
	private LoginPage doEnterUserName(){
		this.enterByName("userName", "ITQAAUTO+26121502123460@CONSUMER.ORG");
		return this;
	}
	
	private LoginPage doEnterPassword(){
		this.enterByName("password", "Password1");
		return this;
	}
	
	
}
