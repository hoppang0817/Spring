package com.bit.springNote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bit.springNote.dto.NoteDTO;


public class NoteDAO {
	
	DataSource ds;
	
	public NoteDAO() {
		try {
			//Server.xml에 선언한 DataSource를 JNDI기법으로 가져온다.
			Context context = new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//write
	public void write(String writer, String content) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		String sql =null;
		try {
			conn = ds.getConnection();
			sql="insert into tblNote(id,writer,content) values(tblNoteSeq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, content);
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//list
	public ArrayList<NoteDTO> list(){
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql =null;
		ArrayList<NoteDTO> list = new ArrayList<NoteDTO>();
		try {
			sql = "select * from tblNote order by id desc";
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String writer = rs.getString("writer");
				String content = rs.getString("content");
//				NoteDTO dto = new NoteDTO(id, writer, content);
//				list.add(dto);
				list.add(new NoteDTO(id, writer, content));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) {
					rs.close();
				}
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	
	//delete
	public void delete(int id) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		String sql =null;
		try {
			sql = "delete from tblNote where id=?";
			conn=ds.getConnection();
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
