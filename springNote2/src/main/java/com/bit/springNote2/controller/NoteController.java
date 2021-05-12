package com.bit.springNote2.controller;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.springNote2.dao.IDao;

@Controller
public class NoteController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "noteForm")
	public String noteForm() {return "noteForm";}
	
	@RequestMapping(value = "write")
	public String write(@RequestParam("writer")String writer,
						@RequestParam("content")String content) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.write(writer, content);
		return "redirect:list";
		
	}
	
	@RequestMapping(value = "list")
	public String list(Model model) {
		IDao dao = sqlSession.getMapper(IDao.class);
		model.addAttribute("list", dao.list());
		return "noteList";
		
	}
	
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("id")int id) {
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.delete(id);
		return "redirect:list";
		
	}
}
