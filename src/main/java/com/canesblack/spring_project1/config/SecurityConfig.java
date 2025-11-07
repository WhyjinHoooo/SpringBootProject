package com.canesblack.spring_project1.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		/*.csrf : 보안 코드로 프론트엔드 페이지에서 백엔드페이지로 데이터를 넘길때 프론트엔드 페이지가 안전하다라는
		 * 일종의 통과할 수 있는 보안이겸유된 인증된 표를 준다.
		 * 이때, 표는 토큰 정도로 보면된다. 프론트엔드 서버와 백엔드서버가 서로 표를 확인하고 통과 싸인이 되면 이제 해킹으로부터
		 * 안전하다고 생각하고 데이터를 넘긴다.
		 * */ 
		http
		.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
		/* csrf해킹기법으로 보호조치를 하는 코드방법. => 나중에 따로 HTML과 자바스크립트에 csrf보호기능도 넣어줄 것이다. 
		 * 프론트영역 -> 백엔드 영역으로 데이터가 넘어갈때 해당 기능을 따로 적용해야 한다. 그래서 False로 지정
		 * 그럼 왜 따로 지정해야 하냐? 한번에 적용하면 RESTAPI 형식을 적용할 수 없다.
		 * */
		.cors(cors -> cors.configurationSource(corsCorsfigurationSource()))
		// 특정 도메인으로만 데이터를 주고 받을 수 있게 허용해주는 코드
		// 한국 <-> 미국 서버간의 데이터를 캄보디아 서버에서 탈취하는 것을 방지하는 코드
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
		// 세션 설정
		.authorizeHttpRequests( // 권한 설정
			authz -> authz.requestMatchers("/","/loginPage","/logout", "/noticeCheckPage", "/registerPage", "/menu/all")
			.permitAll()
			.requestMatchers(HttpMethod.POST, "/login", "/register").permitAll() // 모든 사람들이 접속 가능하게 함
			.requestMatchers("/resource/**", "/WEB-INF/**").permitAll() // **의 의미는 해당 폴더의 하위 폴더를 의미
			.requestMatchers("/noticeAdd", "/noticeModifyPage").hasAnyAuthority("ADMIN", "MANAGER") // 해당 페이지는 특정 권한을 가진 사람만 접속하게 함
			.requestMatchers(HttpMethod.POST, "/menu/add").hasAnyAuthority("ADMIN", "MANAGER") // 해당 페이지는 특정 권한을 가진 사람만 접속하게 함
			.requestMatchers(HttpMethod.POST, "/menu/update").hasAnyAuthority("ADMIN", "MANAGER") // 해당 페이지는 특정 권한을 가진 사람만 접속하게 함
			.requestMatchers(HttpMethod.DELETE, "/menu/delete").hasAnyAuthority("ADMIN", "MANAGER") // 해당 페이지는 특정 권한을 가진 사람만 접속하게 함
			.anyRequest().authenticated() /*위에 등록돼있지 않은 주소를 입력할 때는 로그인을 해야 접근이 가능합니다.
			* 그렇기떄문에 로그인페이지로 자동으로 이동됩니다.
			*/
		) 
		.formLogin( // 로그인 규칙
			login -> login.loginPage("/loginPage") //url을 작성해서 로그인페이지로 이동할 때,
			.loginProcessingUrl("/login") // 로그인 페이지에 있는 form태그의 액션에 작성된 최종 url부분
			.failureUrl("/loginPage?error=true") // 로그인 실패했을 때 발생하는 에러
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(authenticationSuccessHandler())
			.permitAll()
		)
		.logout(
			logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL을 통해서 로그아웃이 진행됨
			.logoutSuccessUrl("/") // 로그아웃이 성공하면 해당 URL로 리다이렉팅해주세요.
			.invalidateHttpSession(true) // 세션 기능 무효화 => 세션 공간에 있던 데이터는 전부 사라져서 로그아웃 상태가 된다.
			.deleteCookies("JSESSIONID") // cookie삭제
			.permitAll()
		);
		return http.build();
		// 최종 HTTP에 적용시킬 때 사용하는 메서드 입니다.
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
				session.setAttribute("username", authentication.getName()); // 세션에 사용자의 아이디를 저장한다.
				session.setAttribute("isAuthenticated", true); // 세션에 로그인 여부를 저장
				// request.getContextPath() => locahost:8090
				response.sendRedirect(request.getContextPath() + "/");
				super.onAuthenticationSuccess(request, response, authentication);
			}
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 스프링 프레임워크의 비밀번호 암호화
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public CorsConfigurationSource corsCorsfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8090", "https://localhost:8090"));
		// localhost:8090 서버에서는 프론트 <-> 백엔드 데이터를 주고받을 수 있게 만든것
		// 일반적으로 프론트(localhost:3000)와 벡엔드(localhost:8090) 서버의 주소는 다름
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // 모든 도메인에 위의 설정이 적용될 수 있게 함
		return source;
	}
}
