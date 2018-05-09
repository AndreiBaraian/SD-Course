package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	private CustomSuccessHandler customSuccesHandler;

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        		.csrf().disable()
	                .authorizeRequests()
	                	.antMatchers("/").permitAll()
	                    .antMatchers("/css/**").permitAll()
	                    .antMatchers(HttpMethod.POST,"/lab").hasAnyRole("ADMIN","STUDENT")
	                    .antMatchers("/student/**").hasAnyRole("STUDENT","ADMIN")
	                    .antMatchers("/mainPage").hasAnyRole("ADMIN","STUDENT")
	                    .antMatchers("/hello").hasAnyRole("ADMIN","STUDENT")
	                    .antMatchers("/mainMenu").hasAnyRole("ADMIN","STUDENT")
	                    .anyRequest().authenticated()
	                .and()
	                .headers()
						.frameOptions().sameOrigin()
					.and()
	                .formLogin()
	                    .loginPage("/")
	                    .loginProcessingUrl("/app-login")
	                    .successHandler(customSuccesHandler)
	                    .permitAll()
	                .and()
	                .logout()
	                    .invalidateHttpSession(true)
	                    .clearAuthentication(true)
	                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                    .logoutSuccessUrl("/login?logout")
	                    .permitAll()
	                .and()
	                .exceptionHandling()
	                    .accessDeniedHandler(accessDeniedHandler);
	    }

	    @Autowired
	    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(customAuthenticationProvider);
	    }
}
