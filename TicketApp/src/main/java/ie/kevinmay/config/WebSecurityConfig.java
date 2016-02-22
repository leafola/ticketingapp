package ie.kevinmay.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import ie.kevinmay.config.handler.MySuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	MySuccessHandler mySuccessHandler;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user_roles where username=?");
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
	  .antMatchers("/list_tickets/**").access("hasRole('ROLE_USER')")
		.antMatchers("/admin/**").access("hasRole('ROLE_USER')")
		.and()
		.formLogin().loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.successHandler(mySuccessHandler);
		
	}
	
}