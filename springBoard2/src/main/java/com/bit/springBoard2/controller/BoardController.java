package com.bit.springBoard2.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.springBoard2.dao.IDao;
import com.bit.springBoard2.dto.BoardDTO;

@Controller
public class BoardController {

	@Autowired  //servlet-context.xml에서 선언한 SqlSession 주입
	private SqlSession sqlSesstion;
	
	@RequestMapping(value = "writeView")
	public String writeView() {return "writeView";}
	
	
	@RequestMapping(value = "write", method=RequestMethod.POST)
	public String write(@ModelAttribute("dto")BoardDTO dto) {
		IDao dao = sqlSesstion.getMapper(IDao.class);
		dao.write(dto);
		return "redirect:list";
	}
	
	@RequestMapping(value = "list")
	public String list(Model model) {
		IDao dao = sqlSesstion.getMapper(IDao.class); //문법같은거임 항상따라옴
		model.addAttribute("list", dao.list());
		return "list";
	}
	
	@RequestMapping(value = "contentView")
	public String contentView(@RequestParam("id")int id,Model model) {
		IDao dao = sqlSesstion.getMapper(IDao.class);
		dao.upHit(id);
		model.addAttribute("contentView", dao.contentView(id));
		return "contentView";
	}
	
	@RequestMapping(value = "modify")
	public String modify(@ModelAttribute("dto")BoardDTO dto) {
		IDao dao = sqlSesstion.getMapper(IDao.class);
		dao.modify(dto);
		return "redirect:list";
	}
	
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("id")int id) {
		IDao dao = sqlSesstion.getMapper(IDao.class);
		dao.delete(id);
		return "redirect:list";
	}
	
}
