package com.bit.mySpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RandomController {

	//jsp 에서 random호출시 여기로
	@RequestMapping(value = "/random")
	public String random(Model model) {
		int lucky = (int)(Math.random()*45)+1;
//		System.out.println("lucky:"+ lucky);
		model.addAttribute("Lucky",lucky);
		return "random"; //views/random.jsp
	}
	
	
	
}
