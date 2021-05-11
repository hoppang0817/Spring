package com.bit.springNote.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.springNote.command.NoteCommand;
import com.bit.springNote.command.NoteWriteCommand;

@Controller
public class NoteController {

	NoteCommand command;
	
	@RequestMapping(value = "noteForm")
	public String noteForm() {return "noteForm";}
	
	@RequestMapping(value = "write",method=RequestMethod.POST)
	public String write(HttpServletRequest req, Model model) {
		model.addAttribute("request",req);
		command = new NoteWriteCommand();
		command.execute(model);
		return "redirect:noteForm";
		
	}
}
