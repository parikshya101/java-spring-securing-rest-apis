package io.jzheaux.springsecurity.resolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

import static org.springframework.http.HttpMethod.GET;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class ResolutionsApplication extends WebSecurityConfigurerAdapter {

/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authz -> authz
						.mvcMatchers(GET, "/resolutions", "/resolution/**").hasAuthority("resolution:read")
						.anyRequest().hasAuthority("resolution:write"))
				.httpBasic(basic -> {});
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authz -> authz
						.anyRequest().authenticated())
				.httpBasic(basic -> {});
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository users) {
		return new UserRepositoryUserDetailsService(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(ResolutionsApplication.class, args);
	}

}
