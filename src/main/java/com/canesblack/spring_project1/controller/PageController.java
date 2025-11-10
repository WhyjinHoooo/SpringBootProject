package com.canesblack.spring_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.service.MenuRestService;
import com.canesblack.spring_project1.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
// @Component는 한 마디로 springBean(스프링 컨테이너의 객체)으로 등록하기 위한 라벨링 작업
public class PageController {

	@Autowired
	private MenuRestService menuRestService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	/*
	 * @GetMapping: 주로 데이터를 조회하거나 페이지를 보여줄 때 사용합니다. 클라이언트는 서버 자원을 읽는 요청입니다.
	 * 
	 * @PostMapping: 주로 새로운 리소스를 생성(등록)할 때 사용됩니다. HTTP 요청 본문에 데이터를 포함하여 서버에 보냅니다.
	 * 
	 * @PutMapping: 기존 리소스를 전체 수정하거나 없으면 새로 생성할 때 씁니다. PUT은 주로 자원의 전체 교체입니다.
	 * 
	 * @DeleteMapping: 리소스를 삭제할 때 사용합니다. 주로 자원의 식별자를 URL에 담아 호출합니다. CRUD의 기능
	 */

	@GetMapping("/registerPage") /* => localhost:port번호/register를 의미한다 */
	public String registerPage(HttpServletRequest request, Model model) {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		model.addAttribute("_csrf", csrfToken);
		return "register/index";
	}

	@GetMapping("/loginPage") /* => localhost:port번호/register를 의미한다 */
	public String loginPage(HttpServletRequest request, Model model) {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		model.addAttribute("_csrf", csrfToken);
		return "login/index";
	}
	@GetMapping("/noticeAddPage")
	public String noticeAddPage(Model model, Authentication authentication) {
		/* Model은 일종의 공간으로 백엔드에서 프론트로 데이터를 전달할 때 데이터가 담기는 공간이다.
		 * 세션과 차이는 화면이 새로고침을 하는 경우, 모델에 담겼던 데이터는 소멸된다. 즉, 일회용이다. */
		String writer = userService.findWriter(authentication.getName());
		model.addAttribute("writer", writer);
		return "noticeAdd/index";
	}
//	jsp에서 a태그는 기본적으로 Get이기 때문에 GetMapping을 적용한다.
	@GetMapping("/noticeCheckPage")
	public String showNoticeCheckPage(@RequestParam("idx") int idx, Model model) {
		/* 공지사항 게시판에서 특정 게시글을 선택했을 때, URL이 /noticeCheckPage?idx=${menu.idx}이므로 
		 * 아래의 boardContent의 매개변수로 사용되는 idx는 URL에 해당 하는 idx값이다.
		 * 해당 idx의 값은 @RequestParam을 통해서 가져올 수 있디.
		 * */		
		Menu menu = menuRestService.boardContent(idx); // 게시판을 가져왔다는 것을 의미
		model.addAttribute("menu", menu);
		return "noticeCheck/index";
	} 
	
	@GetMapping("/noticeModifyPage")
	public String showNoticeModifyPage(@RequestParam("idx") int idx, Model model) {
		Menu menu = menuRestService.boardContent(idx); // 게시판을 가져왔다는 것을 의미
		model.addAttribute("menu", menu);
		return "noticeModify/index";
	} 
}
