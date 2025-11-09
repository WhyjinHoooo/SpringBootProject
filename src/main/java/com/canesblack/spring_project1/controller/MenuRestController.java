package com.canesblack.spring_project1.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.service.MenuRestService;



/* REST API는 프론트와 백엔드에서 데이터를 JSON형태로 주고 받으면서 사용자가에 화면을 서서히 그려주는 과정이면
 * SSR은 백엔드에서 데이터를 받아서 미리 화면을 구성한 다음에 사용자에게 만들어진 화면을 보여주는 과정
 * 그림으로 비유하면 현장에서 직접 그림을 그려주는 것이 RESTapi, 미리 그림을 그려서 주는것이 SSR
 * */
// REST API개발할 때 사용한다.
@RestController
public class MenuRestController {

	@Autowired
	private MenuRestService menuRestService;
	
	//1. 메뉴(모든 게시판)조회 : 모든 게시판들을 가져온다. 백 -> 프론트
	@GetMapping("/menu/all")
	public ResponseEntity<List<Menu>>getAllMenus() {
		List<Menu>menus = menuRestService.getLists();
		if(menus != null && !menus.isEmpty()) {
			return ResponseEntity.ok(menus);
		}else {
			return ResponseEntity.noContent().build(); // 에러 없이 빈 데이터를 전달함. 204번은 NoContent 반환
		}
	}
	
	//2. 메뉴(한 개의 게시판 생성)생성. 프론트 -> 백
	// 프론트에서 백으로 데이터르 전달할 때 JSON형태로 전달한다. 이때, 백엔드에서 JSON데이터 타입을 @RequestBody를 통해서 인식한다.
	@PostMapping("/menu/add")
	public ResponseEntity<String>addMenu(@RequestBody Menu menu){
		// 작성된 시점의 날짜를 오늘로 설정합니다.
		if(menu.getIndate() == null || menu.getIndate().isEmpty()) {
			menu.setIndate(LocalDate.now().toString());
		}
		menu.setCount(0); // 조회수는 처음에는 0으로 설정합니다.
		// 메뉴를 데이터베이스에 삽입
		menuRestService.boardInsert(menu);
		return ResponseEntity.ok("게시글이 잘 작성되었습니다.");
	}
	
	//3. 메뉴(한 개의 게시판 수정)수정
	@PutMapping("/menu/update/{idx}")
	public void UpdateMenu(@RequestBody Menu menu, @PathVariable("idx") int idx) {
		menu.setIdx(idx); // 화면에 있는 게시글 중 선택한 글의 번호가 idx, 특정 idx를 가진 게시글을 menu안의 title과 content를 가져와서 수정해준다.
		menuRestService.boardUpdate(menu);
	}
	
	//4. 메뉴(한 개의 게시판) 삭제
	@DeleteMapping("/menu/delete{idx}")
	public void DeleteMenu(@PathVariable("idx") int idx) {
		menuRestService.boardDelete(idx);
	}
	
	//5. 특정 메뉴(한 개의 게시글)을 가져오는 기능
	@GetMapping("/menu/{idx}")
	public ResponseEntity<Menu>getMenuById(@PathVariable("idx") int idx){
		Menu menu = menuRestService.boardContent(idx);
		if(menu != null) {
			return ResponseEntity.ok(menu); // 200번대의 상태코드와 menu객체를 벡에서 프론트로 데이터를 넘긴다.
		}else {
			return ResponseEntity.notFound().build(); // 메뉴가 존재하지 않을 경우, '404 NOT FOUND'를 반환하게끔 설정
		}
	}

	//6. 특정 메뉴(게시판 조회수 증가) 조회수 증가
	@PutMapping("/menu/count/{idx}")
	public void incrementMenuCount(@PathVariable("idx") int idx) {
		menuRestService.boardCount(idx);
	}
	
}
