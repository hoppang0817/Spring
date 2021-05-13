package com.bit.springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.bit.springBoard.dao.BoardDAO;
import com.bit.springBoard.dto.BoardDTO;


public class BoardContentCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		Map<String,Object> map = model.asMap();
		int id=(Integer)map.get("id");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.contentView(Integer.valueOf(id));
		model.addAttribute("contentView", dto);
	}

}
