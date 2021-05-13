package com.bit.springBoard.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.bit.springBoard.dao.BoardDAO;
import com.bit.springBoard.dto.BoardDTO;

public class BoardWriteCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		//controller에서 받은 요소를 map타입으로 변경
		Map<String,Object>map = model.asMap();
		BoardDTO dto = (BoardDTO)map.get("board");
		//controller에서 받은 요소를 DAO에 선언한 메소드로 넘긴다
		BoardDAO dao = new BoardDAO();
		dao.write(dto);

	}

}
