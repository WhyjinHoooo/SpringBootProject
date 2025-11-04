package com.canesblack.spring_project1.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.canesblack.spring_project1.entity.User;

@Mapper
// 자동으로 @Component의 기능과 비슷하게 스프링컨테이너에 등록이된다(인터페이스)
// 자바언어와 Mysql언어를 통역해주는 역할을 하는 클래스다.
public interface UserMapper {
	
	@Insert("INSERT INTO backend_spring_project.user(username, password, writer, role) "
			+ "VALUES(#{username}, #{password}, #{writer}, #{role})")  // CRUD 중 Create
	void insertUser(User user);
	// void -> 우리가 데이터베이스에서 백엔드 영역(스프링프레임워크)로 데이터를 가져오는게 없기 때문에
	// void로 작성한다.
	@Select("SELECT username, password, writer, role FROM backend_spring_project.user "
			+ "WHERE username = #{username}") // CRUD 중 Read
	User findByUsername(String username);
	
	@Select("SELECT writer FROM backend_spring_project.user "
			+ "WHERE username = #{username}")
	String findByWriter(String username);
	
//	@Update()  // CRUD 중 Update
//	
//	@Delete()  // CRUD 중 Delete
	
}
