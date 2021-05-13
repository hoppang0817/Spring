package com.bit.springBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.bit.springBoard.dto.BoardDTO;

public class BoardDAO {
	DataSource ds;
	
	Connection conn =null;
	PreparedStatement pstmt =null;
	ResultSet rs =null;
	String sql=null;

	
	public BoardDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//insert : insert into tblBoard(id, name, title, content, hit)values
	//(tblBoardSeq.nextval, ?, ?, ?, 0)
	public void write(BoardDTO dto) {
//		Connection conn =null;
//		PreparedStatement pstmt=null;
//		String sql = null;
		try {
			conn=ds.getConnection();
			sql=" insert into tblBoard(id, name, title, content, hit)values(tblBoardSeq.nextval, ?, ?, ?, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
		
	//list : select * from tblBoard order by id desc
	public ArrayList<BoardDTO> list(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = ds.getConnection();
			sql="select * from tblBoard order by id desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				BoardDTO dto = new BoardDTO(id, name, title, content, regdate, hit);
				list.add(dto);			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		
		return list;
		
	}
		
	//contentView : select * from tblBoard where id = ?
	public BoardDTO contentView(int id) {
		upHit(id);
		BoardDTO dto =new BoardDTO();
		try {
			conn=ds.getConnection();
			sql = "select * from tblBoard where id = ?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setId(id);
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setHit(rs.getInt("hit"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
		
	}
		
	//modify : update tblBoard set name = ?, title = ?, content = ? 
	//where id = ?
	public void modify(BoardDTO dto) {
		try {
			conn = ds.getConnection();
			sql="update tblBoard set name = ?, title = ?, content = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
			
		}
	}
		
	//delete : delete from tblBoard where id = ? 
	
	public void delete(int id) {
		try {
			conn = ds.getConnection();
			sql ="delete from tblBoard where id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
		
	}
	//upHit : update tblBoard set hit = hit + 1 where id = ?

	public void upHit(int id) {
		try {
			conn = ds.getConnection();
			sql="update tblBoard set hit = hit + 1 where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
	}
}
