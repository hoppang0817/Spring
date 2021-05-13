package com.bit.springBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.bit.springBoard.dao.BoardDAO;
import com.bit.springBoard.dto.BoardDTO;

public class BoardListCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		//db와 연결된 DAO호출
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardDTO>dtos=dao.list();
		//DAO에서 만든 리스트를 list라는 변수명에 담아 controller로 보낸다
		model.addAttribute("list",dtos);

	}

}
