package com.bit.springNote2.dao;

import java.util.ArrayList;

import com.bit.springNote2.dto.NoteDTO;

public interface IDao {

	public void write(String writer, String content);
	public ArrayList<NoteDTO> list();
	public void delete(int id);
}
