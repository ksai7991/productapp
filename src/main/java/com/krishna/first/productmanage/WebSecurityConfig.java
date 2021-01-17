package com.krishna.first.productmanage;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
	private DataSource dataSource;
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuider) throws Exception {
    	authBuider.jdbcAuthentication()
    	.dataSource(dataSource)
    	.passwordEncoder(new BCryptPasswordEncoder())
    	.usersByUsernameQuery("select username, password, enabled from users where username=?")
    	.authoritiesByUsernameQuery("select username, role from users where username=?")
    	;
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/h2/**","/signup","/signup/**","/saveuser","/saveuser/**","/login**","/error","/error/**").permitAll()
		.antMatchers("/edit/*","/delete/*").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout().permitAll()
	    .and()
	    .exceptionHandling().accessDeniedPage("/403")

		;
		http.csrf().disable();
		http.headers().frameOptions().disable();
		}
	

}
