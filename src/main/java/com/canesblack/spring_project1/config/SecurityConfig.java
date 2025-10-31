package com.canesblack.spring_project1.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
// SpringSecurity기능을 사용하기 위해서는 위의 두 어노테이션을 작성해야한다.
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		/* SecurityFilterChain메소드는 springSecurity기능을 사용하고자 할 떄 사용한다.
		 * HttpSecurity을 기능을 사용한다. */
		http.formLogin(
			login -> login.loginPage("/loginPage") //url을 작성해서 로그인페이지로 이동할 때,
			.loginProcessingUrl("/login") // 로그인 페이지에 있는 form태그의 액션에 작성된 최종 url부분
			.failureUrl("/loginPate?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(authenticationSuccessHandler())
			.permitAll()
		)
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new SimpleUrlAuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// 로그인이 성공했을 때, 특별기능을 넣고 싶을 때(세션, 권한 기능 부여)
				HttpSession session = request.getSession(); //세션기능을 가지고 온다
				boolean isManager = authentication.getAuthorities().stream()
						.anyMatch(grantedAuthority -> 
						grantedAuthority.getAuthority().equals("ADMIN") ||
						grantedAuthority.getAuthority().equals("MANAGER"));
				if(isManager) {
					session.setAttribute("MANAGER", true);
				}
				session.setAttribute("username", authentication.getName());
				session.setAttribute("isAuthenticatied", true);
				// request.getContextPath() => locahost:8090
				response.sendRedirect(request.getContextPath() + "/");
				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}
}
