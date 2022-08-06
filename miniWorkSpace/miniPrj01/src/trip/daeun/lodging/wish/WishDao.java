package trip.daeun.lodging.wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import trip.hyewon.lodging.LodgingVo;
import trip.min.common.JDBCTemplate;
import trip.min.main.MemberMain;

public class WishDao {

	// db 가서 회원 번호랑 숙박 번호 일치하는지
	// 입력 안 받고 확인되는 법?
	// 맞으면 찜되기

	public int wish(WishVo vo, Connection conn) throws Exception {

		int result = 0;
		PreparedStatement pstmt = null;

		try {

			// SQL 준비
			String sql = "INSERT INTO LODGING_WISH (NO, MEMBER_NO, LODGING_NO, CANCEL_YN) VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?)";
			// SQL 담을 객체 만들기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMemberNo());
			pstmt.setString(2, vo.getLodgingNo());
			pstmt.setString(3, vo.getCancelYn());

			// sql 실행 및 결과 저장
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public boolean selectWish(LodgingVo vo, Connection conn) throws Exception {

		String sql = "SELECT * FROM LODGING_WISH WHERE MEMBER_NO = ? AND LODGING_NO = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean isWish = false;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberMain.LoginMember.getNo());
			pstmt.setString(2, vo.getLodgingCode());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				isWish = true;
			}
		} catch (Exception e) {

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

		return isWish;
	}
	

	public List<LodgingVo> showZzimList(Connection conn) throws Exception {

		// SQL 준비
		String sql = "SELECT I.NO, I.NAME , I.ADDRESS , I.PHONE FROM LODGING_WISH W JOIN LODGING_INFORMATION I ON W.LODGING_NO = I.NO WHERE W.MEMBER_NO = ? AND W.CANCEL_YN = 'N'";

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LodgingVo> lodgingVoList = new ArrayList<LodgingVo>();

		try {

			// SQL 담을 객체 준비 및 SQL 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberMain.LoginMember.getNo());

			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();			
			
			while(rs.next()) {

				LodgingVo vo = new LodgingVo();
				String no = rs.getString("NO");
				String name = rs.getString("NAME");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");

				vo.setNo(no);
				vo.setName(name);
				vo.setAddress(address);
				vo.setPhone(phone);
				
				lodgingVoList.add(vo);

			}

		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);

		}

		// SQL 실행 결과 리턴
		
		return lodgingVoList;

	}

}