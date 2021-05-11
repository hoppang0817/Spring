package com.bit.springNote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor 
//생성자 만듬
public class NoteDTO {

	private int id;
	private String writer;
	private String content;
	
}
