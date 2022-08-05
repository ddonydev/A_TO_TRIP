package trip.dk.coupon;

import static trip.min.common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import trip.min.main.MemberMain;

public class CouponDao {

	
	//발급 쿠폰 조회 (사용한것들 제외하고) 셀렉트 
	public List<CouponVo> showList(Connection conn) throws Exception {
		//CONN 준비
		//SQL 준비
		String sql = "SELECT INFO.NO,M.NO,E.NO,INFO.COUPON_CODE,INFO.DISCOUNT_RATE,ISS.USED_YN,ISS.USED_DATE FROM COUPON_ISSUED ISS JOIN COUPON_INFO INFO ON INFO.NO = ISS.COUPON_INFO_NO JOIN MEMBER M ON M.NO = ISS.MEMBER_NO JOIN EVENT E ON E.NO = INFO.EVENT_NO WHERE M.NO = ?";
		
		ResultSet rs = null;
		PreparedStatement pstmt=null;
		List<CouponVo> couponVoList = new ArrayList<CouponVo>();
		try {	
			//SQL 담을 객체 준비 및 SQL 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,MemberMain.LoginMember.getNo());
			//SQL실행 및 결과 저장
			rs = pstmt.executeQuery();

			while(rs.next()) {
			
				String no=rs.getString("NO");
				String couponInfoNo=rs.getString("NO");
				String memberNo=rs.getString("NO");
				String eventNo=rs.getString("NO");
				String discountRate = rs.getString("DISCOUNT_RATE");
				String couponCode = rs.getString("COUPON_CODE");
				String usedYn=rs.getString("USED_YN");
				Timestamp usedDate=rs.getTimestamp("USED_DATE");
				
				
				CouponVo cv = new CouponVo();
				cv.setCouponIssuedNo(no);
				cv.setCouponInfoNo(couponInfoNo);
				cv.setMemberNo(MemberMain.LoginMember.getNo());
				cv.setEventNo(eventNo);
				cv.setDiscountRate(discountRate);
				cv.setCouponCode(couponCode);
				cv.setUsedYn(usedYn);
				cv.setUsedDate(usedDate);
				
				couponVoList.add(cv);
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
			
		}
		
		//SQL 실행 결과 리턴 
		return couponVoList;
	}


	
	
	
	// 쿠폰 발급 인설트
	public int couponIssued(String cv,Connection conn) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			//sql작성 
			String sql ="INSERT INTO COUPON_ISSUED(NO,COUPON_INFO_NO,MEMBER_NO,USED_YN,USED_DATE) VALUES(SEQ_COUPON_ISSUED_NO.NEXTVAL,?,?,?,SYSDATE)";
			
			//sql객체에 담아주기.(물음표 채우기)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cv);
			pstmt.setString(2, MemberMain.LoginMember.getNo());
			pstmt.setString(3, "N");
			
			//sql실행 및 결과 저장.
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	

}
