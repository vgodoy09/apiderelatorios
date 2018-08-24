package com.novotempo.endofyeartoast.config.filter;
import org.springframework.stereotype.Component;

@Component
public class Authentication {
	
	public boolean checkLogin(String user, String password) {
		return "requestmaterial".equals(user) && "webdesen".equals(password);
	}

}
