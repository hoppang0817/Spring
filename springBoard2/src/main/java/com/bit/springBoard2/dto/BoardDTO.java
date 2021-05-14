package com.bit.springBoard2.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor   //기본생성자
public class BoardDTO {

	private int id;
	private String name;
	private String title;
	private String content;
	private Timestamp regdate;
	private int hit;
	
}
