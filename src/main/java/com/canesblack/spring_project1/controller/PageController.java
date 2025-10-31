package com.canesblack.spring_project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
// @Component는 한 마디로 springBean(스프링 컨테이너의 객체)으로 등록하기 위한 라벨링 작업
public class PageController {
	
	@GetMapping("/")
	public String returnHome() {
		return "index";
	}
	/* @GetMapping: 주로 데이터를 조회하거나 페이지를 보여줄 때 사용합니다. 클라이언트는 서버 자원을 읽는 요청입니다.
	 * @PostMapping: 주로 새로운 리소스를 생성(등록)할 때 사용됩니다. HTTP 요청 본문에 데이터를 포함하여 서버에 보냅니다.
	 * @PutMapping: 기존 리소스를 전체 수정하거나 없으면 새로 생성할 때 씁니다. PUT은 주로 자원의 전체 교체입니다.
	 * @DeleteMapping: 리소스를 삭제할 때 사용합니다. 주로 자원의 식별자를 URL에 담아 호출합니다.
	 * CRUD의 기능
	 */
}
