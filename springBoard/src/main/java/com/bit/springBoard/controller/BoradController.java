package com.bit.springBoard.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit.springBoard.command.BoardCommand;
import com.bit.springBoard.command.BoardListCommand;
import com.bit.springBoard.command.BoardWriteCommand;
import com.bit.springBoard.command.BoradModifyCommand;
import com.bit.springBoard.command.BoardContentCommand;
import com.bit.springBoard.command.BoardDeleteCommand;
import com.bit.springBoard.dto.BoardDTO;

@Controller
public class BoradController {

	BoardCommand command;
	
	@RequestMapping(value ="writeView" )
	public String writeView() {return "writeView";}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
						//BoardDTO형식의 요소를 "board"변수에 담음 이걸 command로 넘겨 db와 연결하여 수행할꺼임
	public String write(@ModelAttribute("board")BoardDTO dto ,Model model) {
		command = new BoardWriteCommand();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping(value = "list")
	public String list(Model model){
		//command호출 (넘겨줄 파라메터없음)
		command = new BoardListCommand();
		//command에서 model에 담아 보내온 리스트를 list.jsp로 보냄
		command.execute(model);
		return "list";
	}
	
	@RequestMapping(value = "contentView")
	public String contentView(@RequestParam("id")int id ,Model model) {
		model.addAttribute("id", id);
		command = new BoardContentCommand();
		command.execute(model);
		return "contentView";
	}
	
	@RequestMapping(value = "modify")
	public String modify(@ModelAttribute("board")BoardDTO dto,Model model) {
		command = new BoradModifyCommand();
		command.execute(model);
		return "redirect:list";
		
	}
	
	@RequestMapping(value = "delete")
	public String delete(@RequestParam("id")int id,Model model) {
		model.addAttribute("id", id);
		command = new BoardDeleteCommand();
		command.execute(model);
		return "redirect:list";
		
	}
}
