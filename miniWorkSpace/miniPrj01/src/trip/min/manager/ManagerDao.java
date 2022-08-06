package trip.min.manager;

import static trip.min.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import trip.hyewon.lodging.LodgingVo;
import trip.min.common.JDBCTemplate;
import trip.min.member.MemberVo;
import trip.min.util.InputUtil;

public class ManagerDao {

	public int InsertLodging(LodgingVo vo, Connection conn) throws Exception {
		//숙소 등록
		//DB insert
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			
			//SQL 준비
			String sql = "INSERT INTO LODGING_INFORMATION(NO, LODGING_CODE, NAME, PHONE, ADDRESS, LODGING_LIKE, BREAKFAST_YN, LOCATION_CODE) VALUES (SEQ_LODGING_INFORMATION_NO.NEXTVAL,?,?,?,?,?,'N',?)";
			
			//SQL 담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getLodgingCode());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getLodgingLike());
			pstmt.setString(6, vo.getLocationCode());
			
			//SQL 실행 및 결과 저장
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}//InsertLodging
	
	public int UpdateLodging(LodgingVo vo, Connection conn) throws Exception {
		//숙소 수정

		PreparedStatement pstmt = null;
		int result = 0;
		
			// SQL 준비
		String sql = "UPDATE LODGING_INFORMATION SET LODGING_CODE = ?, NAME = ?, PHONE = ?, ADDRESS = ?, LODGING_LIKE = ?, BREAKFAST_YN = ?, LOCATION_CODE = ? WHERE NO = ?";
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getLodgingCode());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getPhone());
				pstmt.setString(4, vo.getAddress());
				pstmt.setString(5, vo.getLodgingLike());
				pstmt.setString(6, vo.getBreakfastYn());
				pstmt.setString(7, vo.getLocationCode());
				pstmt.setString(8, vo.getNo());
				
				result = pstmt.executeUpdate();
				
			}finally {
				JDBCTemplate.close(pstmt);
			}
			
		return result;
	}//Update
	
	public List<MemberVo> selectMember(Connection conn) throws Exception {
		//conn = 파라미터
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVo> memberVoList = new ArrayList<MemberVo>();
		//sql 준비
		String sql = "SELECT ID, EMAIL, NAME, BIRTH, PHONE, NICK, ENROLL_DATE, QUIT_YN FROM MEMBER";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("ID");
				String email = rs.getString("EMAIL");
				String name = rs.getString("NAME");
				String birth = rs.getString("BIRTH");
				String phone = rs.getString("PHONE");
				String nick = rs.getString("NICK");
				Timestamp enrollDate = rs.getTimestamp("ENROLL_DATE");
				String quitYn = rs.getString("QUIT_YN");
				
				MemberVo vo = new MemberVo();
				
				vo.setId(id);
				vo.setEmail(email);
				vo.setName(name);
				vo.setBirth(birth);
				vo.setNick(nick);
				vo.setEnrollDate(enrollDate);
				vo.setQuitYn(quitYn);
				
				memberVoList.add(vo);
				
			}
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return memberVoList;
	}//listShowMember
	
	public int modifyMember(MemberVo vo, Connection conn) throws Exception {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		//SQL
		String sql = "UPDATE MEMBER SET EMAIL = ? , NAME = ? , PHONE = ? , NICK = ? , QUIT_YN = ? WHERE ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getNick());
			pstmt.setString(5, vo.getQuitYn());
			pstmt.setString(6, vo.getId());
			
			result = pstmt.executeUpdate();
			
		}finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}//editMember
	
	public MemberVo managerLogin(String inputId, String inputPwd) throws Exception {
		//DB 가서 , id pwd 일치하는 행 조회
		
		//CONNECTION 준비
		Connection conn = JDBCTemplate.getConnection();
		/*
		 * driver, url, id, pwd
		 */
		//SQL 작성
		String sql = "SELECT NO , ID , NICK FROM MEMBER WHERE ID = ? AND PWD = ? AND NICK LIKE '%관리자%'";
		
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
	}//managerLogin
	
}
