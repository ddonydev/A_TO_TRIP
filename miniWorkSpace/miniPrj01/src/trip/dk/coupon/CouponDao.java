package trip.dk.coupon;

import static trip.min.common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponDao {

	
	//발급 쿠폰 조회 (사용한것들 제외하고) 셀렉트 
	public List<CouponIssuedVo> showList(Connection conn) throws Exception {
		//CONN 준비
		//SQL 준비
		String sql = "";
		
		//SQL 담을 객체 준비 및 SQL 완성
		PreparedStatement pstmt=null;
		
		
		ResultSet rs = null;
		List<CouponIssuedVo> couponVoList = new ArrayList<CouponIssuedVo>();
		try {	
			pstmt = conn.prepareStatement(sql);
			//SQL실행 및 결과 저장
			rs = pstmt.executeQuery();

			while(rs.next()) {
				CouponIssuedVo civ = new CouponIssuedVo();
				couponVoList.add(civ);
			}
			
		} finally {
			close(rs);
			close(pstmt);
			
			
		}
		
		//SQL 실행 결과 리턴 
		return couponVoList;
	}


	
	
	
	// 쿠폰 발급 인설트
	public int issuedCoupon(CouponIssuedVo civ,Connection conn) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			//sql작성 
			String sql ="INSERT INTO BOARD(NO,TITLE,CONTENT,WRITER_NO) VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?)";
			
			//sql객체에 담아주기.(물음표 채우기)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "NO");
			pstmt.setString(2, "COUPON_INFO_NO");
			pstmt.setString(3, "MEMBER_NO");
			
			//sql실행 및 결과 저장.
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
			
		}
		
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	

}
