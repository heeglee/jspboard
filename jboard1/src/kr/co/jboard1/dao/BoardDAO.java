package kr.co.jboard1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.jboard1.config.DBConfig;
import kr.co.jboard1.vo.BoardVO;

// DAO : Data Access Object
// 데이터베이스와 쿼리를 수행하는 객체
public class BoardDAO {
	
	// Singleton Instance: 하나의 객체로 관리
	public static BoardDAO dao = new BoardDAO();
	public static BoardDAO getInstance() {
		return dao;
	}
	
	// BoardDAO 객체 생성 금지
	private BoardDAO() {}
	
	// 전체 글 갯수
	public int getTotalCount() throws Exception {
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		
		int count = 0;
		
		conn = DBConfig.getConnect();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL.BOARD_COUNT);
		
		if(rs.next()) {
			count = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		
		return count;
	}
	
	// 페이지 계산
	public int getPage(int total) {
		int page = 0;
		
		if(total % 10 == 0) {
			page = total / 10;
		} else {
			page = (total / 10) + 1;
		}
		
		return page;
	}
	
	// limit start 값 계산
	public int getLimit(String pg) {
		int start;
		
		if(pg == null) {
			start = 0;
		} else {
			start = (Integer.parseInt(pg)-1) * 10;
		}
		
		return start;
	}
	
	// 글쓰기
	public void write(BoardVO vo) throws Exception {
		Connection conn;
		PreparedStatement psmt;

		conn = DBConfig.getConnect();
		psmt = conn.prepareStatement(SQL.BOARD_WRITE);
		psmt.setString(1, vo.getCate());
		psmt.setString(2, vo.getTitle());
		psmt.setString(3, vo.getContent());
		psmt.setString(4, vo.getUid());
		psmt.setString(5, vo.getRegip());
		psmt.executeUpdate();

		psmt.close();
		conn.close();
	}
	
	// 글목록
	public List<BoardVO> list(int start) throws Exception {
		Connection conn;
		PreparedStatement psmt;
		ResultSet rsList;
		List<BoardVO> list = new ArrayList<>();
		
		conn = DBConfig.getConnect();
		
		psmt = conn.prepareStatement(SQL.BOARD_LIST);
		psmt.setInt(1, start);
		
		rsList = psmt.executeQuery();

		while(rsList.next()) {
			BoardVO vo = new BoardVO();
			
			vo.setSeq(rsList.getInt(1));
			vo.setParent(rsList.getInt(2));
			vo.setComment(rsList.getInt(3));
			vo.setCate(rsList.getString(4));
			vo.setTitle(rsList.getString(5));
			vo.setContent(rsList.getString(6));
			vo.setFile(rsList.getInt(7));
			vo.setHit(rsList.getInt(8));
			vo.setUid(rsList.getString(9));
			vo.setRegip(rsList.getString(10));
			vo.setRdate(rsList.getString(11));
			
			list.add(vo);
		}
		
		rsList.close();
		psmt.close();
		conn.close();
		
		return list;
	}
	
	// 글보기
	public BoardVO view(String seq) throws Exception {
		BoardVO vo = null;
		
		Connection conn = DBConfig.getConnect();
		PreparedStatement psmt = conn.prepareStatement(SQL.BOARD_VIEW);
		psmt.setString(1, seq);
		ResultSet rs = psmt.executeQuery();
		if(rs.next()) {
			vo = new BoardVO();
			
			vo.setSeq(rs.getInt(1));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setCate(rs.getString(4));
			vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));
		}
		
		rs.close();
		psmt.close();
		conn.close();
		
		return vo;
		
	}
	
	// 글수정
	public void modify() {}
	
	// 댓글쓰기
	public void commentWrite() {}
	
	// 댓글목록
	public void commentList() {}
	
	
}
