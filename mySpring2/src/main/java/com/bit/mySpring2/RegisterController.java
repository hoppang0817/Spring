package com.bit.mySpring2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

	@RequestMapping(value = "register")
	public String register() {return "register";}
	
	@RequestMapping(value = "join")
	public String join(@RequestParam("id")String id,
						@RequestParam("pwd")String pwd,
						@RequestParam("name")String name, 
						Model model) 
	{
		model.addAttribute("id",id);
		model.addAttribute("pwd",pwd);
		model.addAttribute("name",name);
		
		return "join";
		
		
	}
}
