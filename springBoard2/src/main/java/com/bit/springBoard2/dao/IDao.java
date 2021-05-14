package com.bit.springBoard2.dao;

import java.util.ArrayList;

import com.bit.springBoard2.dto.BoardDTO;

public interface IDao {

	public void write(BoardDTO dto);
	public ArrayList<BoardDTO> list();
	public BoardDTO contentView(int id);
	public void modify(BoardDTO dto);
	public void delete(int id);
	public void upHit(int id);
}
