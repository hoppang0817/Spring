package com.bit.mySpring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
	
	
	@RequestMapping(value = "member/loginForm")
	//views/member/loginForm.jsp 호출
	//단순히 form호출하는 controller
	public String loginForm() {return "member/loginForm";}
	
	@RequestMapping(value = "member/confirmId")
	public String confirmId(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		model.addAttribute("id",id);
		model.addAttribute("pwd",pwd);
		
		return "member/confirmId";
		
	}
	
	@RequestMapping(value = "member/confirmId1")
	public String confirmId1(@RequestParam("id")String id,
							@RequestParam("pwd")String pwd,
							Model model) {
		model.addAttribute("id",id);
		model.addAttribute("pwd",pwd);
		
		return "member/confirmId";
	}
}
