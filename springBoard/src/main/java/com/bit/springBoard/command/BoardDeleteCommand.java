package com.bit.springBoard.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.bit.springBoard.dao.BoardDAO;

public class BoardDeleteCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		Map<String,Object> map = model.asMap();
		int id = (Integer)map.get("id");
		BoardDAO dao = new BoardDAO();
		dao.delete(Integer.valueOf(id));
	}

}
