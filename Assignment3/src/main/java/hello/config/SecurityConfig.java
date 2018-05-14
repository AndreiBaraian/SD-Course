package hello.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
	                	.antMatchers("/","/registerView").permitAll()
	                	.antMatchers(HttpMethod.POST,"/register/**").permitAll()
	                    .antMatchers("/css/**").permitAll()
	                    .antMatchers(HttpMethod.POST,"/lab").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.PUT,"/lab").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.DELETE,"/lab").hasRole("ADMIN")
	                    .antMatchers("/addLabView","/modifyLaboratory/**","/deleteLabView").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.POST,"/attendance").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.PUT,"/attendance").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.DELETE,"/attendance").hasRole("ADMIN")
	                    .antMatchers("/addAttendanceView/**").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.POST,"/assignment").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.PUT,"/assignment").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.DELETE,"/assignment").hasRole("ADMIN")
	                    .antMatchers("/addAssignmentView","modifyAssignmnent/**").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.POST,"/student").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.PUT,"/student").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.DELETE,"/student").hasRole("ADMIN")
	                    .antMatchers("/addStudenView","modifyStudent/**").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.POST,"/submission").hasRole("STUDENT")
	                    .antMatchers(HttpMethod.PUT,"/student").hasRole("ADMIN")
	                    .antMatchers(HttpMethod.DELETE,"/student").hasRole("STUDENT")
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
	                    .deleteCookies("JSESSIONID")
	                    .clearAuthentication(true)
	                    .logoutSuccessHandler(logoutSuccessHandler())
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
	    
	    @Bean
	    public LogoutSuccessHandler logoutSuccessHandler() {
	        return new CustomLogoutSuccessHandler();
	    }

}
