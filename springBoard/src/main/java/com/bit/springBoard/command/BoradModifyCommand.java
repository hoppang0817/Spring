package com.bit.springBoard.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.bit.springBoard.dao.BoardDAO;
import com.bit.springBoard.dto.BoardDTO;

public class BoradModifyCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		Map<String,Object> map = model.asMap();
		BoardDTO dto = (BoardDTO)map.get("board");
		BoardDAO dao = new BoardDAO();
		dao.modify(dto);
	}

}
