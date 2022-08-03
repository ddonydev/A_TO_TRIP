package trip.min.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trip.min.common.JDBCTemplate;

public class MemberDao {

	public MemberVo login(String inputId, String inputPwd) throws Exception {
		//DB 가서 , id pwd 일치하는 행 조회
		
		//CONNECTION 준비
		Connection conn = JDBCTemplate.getConnection();
		/*
		 * driver, url, id, pwd
		 */
		//SQL 작성
		String sql = "SELECT NO , ID , NICK FROM MEMBER WHERE ID = ? AND PWD = ? AND QUIT_YN = 'N'";
		
		//SQL 객체에 담기
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputId);
		pstmt.setString(2, inputPwd);
		//SQL 실행
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo vo = null;
		
		if(rs.next()) {
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String nick = rs.getString("NICK");
			
			vo = new MemberVo();
			vo.setNo(no);
			vo.setId(id);
			vo.setNick(nick);
		}
		
		return vo;
	}//login
	
public int join(MemberVo vo, Connection conn) throws Exception {
		
		//DB insert
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			
			//SQL 준비
			String sql = "INSERT INTO MEMBER(NO , ID , PWD , EMAIL, NAME, BIRTH, PHONE, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL,?,?,?,?,TO_DATE(?),?,?)";
			
			//SQL 담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getPhone());
			pstmt.setString(7, vo.getNick());
			
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}//join
	
}//class
