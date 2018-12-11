package kr.co.jboard2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.jboard2.utils.DBConfig;
import kr.co.jboard2.utils.SQL;
import kr.co.jboard2.vo.MemberVO;
import kr.co.jboard2.vo.TermsVO;

public class MemeberDAO {
	
	// Singleton Instance
	private static MemeberDAO dao = new MemeberDAO();
	public static MemeberDAO getInstance() {
		return dao;
	}	
	private MemeberDAO() {}

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private Statement stmt = null;
	private ResultSet rs   = null;
	
	public MemberVO login(String id, String pw) throws Exception {
		
		conn = DBConfig.getConnection();
		psmt = conn.prepareStatement(SQL.SELECT_MEMBER_LOGIN);
		psmt.setString(1, id);
		psmt.setString(2, pw);
		
		rs = psmt.executeQuery();
		
		MemberVO vo = null;
		if(rs.next()) {
			vo = new MemberVO();
			vo.setSeq(rs.getInt(1));
			vo.setUid(rs.getString(2));
			vo.setPass(rs.getString(3));
			vo.setName(rs.getString(4));
			vo.setNick(rs.getString(5));
			vo.setEmail(rs.getString(6));
			vo.setHp(rs.getString(7));
			vo.setGrade(rs.getInt(8));
			vo.setZip(rs.getString(9));
			vo.setAddr1(rs.getString(10));
			vo.setAddr2(rs.getString(11));
			vo.setRegip(rs.getString(12));
			vo.setRdate(rs.getString(13));
		}
	
		DBConfig.close(rs);
		DBConfig.close(psmt);
		DBConfig.close(conn);
		
		return vo;
	}	
	
	public TermsVO terms() throws Exception {
		
		TermsVO vo = null;
		
		conn = DBConfig.getConnection();			
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL.SELECT_TERMS);
		if(rs.next()) {
			vo = new TermsVO();
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}
		
		DBConfig.close(rs);
		DBConfig.close(stmt);
		DBConfig.close(conn);
		
		return vo;
	}
	
	public void register(MemberVO vo) throws Exception {
		
		conn = DBConfig.getConnection();
		psmt = conn.prepareStatement(SQL.INSERT_MEMBER);
		psmt.setString(1, vo.getUid());
		psmt.setString(2, vo.getPass());
		psmt.setString(3, vo.getName());
		psmt.setString(4, vo.getNick());
		psmt.setString(5, vo.getEmail());
		psmt.setString(6, vo.getHp());
		psmt.setString(7, vo.getZip());
		psmt.setString(8, vo.getAddr1());
		psmt.setString(9, vo.getAddr2());
		psmt.setString(10, vo.getRegip());
		
		psmt.executeUpdate();
		
		DBConfig.close(psmt);
		DBConfig.close(conn);		
	}
	
	public int checkUser(String check, String value) throws Exception {
		
		int result = 0;
		
		conn = DBConfig.getConnection();			
		
		switch (check) {
		case "uid":
			psmt = conn.prepareStatement(SQL.SELECT_CHECK_UID);
			break;
		case "nick":
			psmt = conn.prepareStatement(SQL.SELECT_CHECK_NICK);
			break;
		case "hp":
			psmt = conn.prepareStatement(SQL.SELECT_CHECK_HP);
			break;
		case "email":
			psmt = conn.prepareStatement(SQL.SELECT_CHECK_EMAIL);
			break;
		}
		psmt.setString(1, value);

		rs = psmt.executeQuery();
		
		if(rs.next()) {
			result = rs.getInt(1);
		}
		
		DBConfig.close(rs);
		DBConfig.close(psmt);
		DBConfig.close(conn);
		
		return result;
	}
	
}