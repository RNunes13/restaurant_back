package br.com.nunes.restaurant.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitialize extends AbstractSecurityWebApplicationInitializer {
	
	public SecurityInitialize() {
        super(SecurityConfig.class);
    }

}
