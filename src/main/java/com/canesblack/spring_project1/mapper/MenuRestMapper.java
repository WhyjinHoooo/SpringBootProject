package com.canesblack.spring_project1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.canesblack.spring_project1.entity.Menu;

//@Mapper는 @Component9@ComponentScane와 비슷)가 없지만 @MapperScan이 있어 자동으로 스프링컨테이너에 등록이 된다.
@Mapper
public interface MenuRestMapper {

	@Insert("INSERT INTO backend_spring_project.menu(memID, title, content, writer, indate) "
			+ "VALUES(#{memID}, #{title}, #{content}, #{writer}, #{indate})") // CRUD 중 C
	public void boardInsert(Menu menu);
	
	@Select("SELECT idx, memID, title, content, indate, count FROM backend_spring_project.menu ORDER BY idx DESC")
	public List<Menu>getLists();// CRUD 중 R
	
	@Select("SELECT idx, memID, title, content, indate, count FROM backend_spring_project.menu "
			+ "WHERE idx = #{idx}")
	public Menu boardContent(int idx);
	
	@Update("UPDATE backend_spring_project.menu SET title = #{title}, content = #{content}, writer = #{writer} "
			+ "WHERE idx = #{idx}") // CRUD 중 U
	public void boardUpdate(Menu menu);
	
	@Update("UPDATE backend_spring_project.menu SET count = count + 1 WHERE idx = #{idx}")
	public void boardCount(int idx);
	
	@Delete("DELETE FROM backend_spring_project.menu WHERE idx = #{idx}") // CRUD 중 D
	public void boardDelete(int idx);
}
