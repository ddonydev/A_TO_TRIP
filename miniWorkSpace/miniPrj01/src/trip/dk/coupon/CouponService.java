package trip.dk.coupon;

import java.sql.Connection;
import java.util.List;

import trip.dk.event.EventGame;
import trip.dk.event.EventGameController;
import trip.dk.event.EventService;
import trip.min.main.MemberMain;
import trip.min.member.MemberDao;

import static trip.min.common.JDBCTemplate.*;

public class CouponService extends EventGameController {
	
	
	// 보유 쿠폰 조회
	public List<CouponVo> showList() {
		
		Connection conn = null;
		List<CouponVo> couponVoList = null;
		try {
			conn = getConnection();
			couponVoList =new CouponDao().showList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return couponVoList;
		
	}
	
	//게임 결과에 따라 쿠폰을 이벤트 번호, 회원번호에 맞춰 발급
	public int couponIssued(CouponIssuedVo civ) {
		
		int rpsresult = rpsResult;
		int upDownresult = upDownResult;
		
		if(rpsResult>=3) {
			civ.setMemberNo(MemberMain.LoginMember.getNo());
			civ.setCouponInfoNo("3");

		}if(rpsResult>=2) {
			civ.setMemberNo(MemberMain.LoginMember.getNo());
			civ.setCouponInfoNo("2");

		}if(rpsResult>=1) {
			civ.setMemberNo(MemberMain.LoginMember.getNo());
			civ.setCouponInfoNo("1");
			
		}if(upDownResult<=3) {
			civ.setMemberNo(MemberMain.LoginMember.getNo());
			civ.setCouponInfoNo("6");

		}if(upDownResult<=5) {
			civ.setMemberNo(MemberMain.LoginMember.getNo());
			civ.setCouponInfoNo("5");

		}if(upDownResult<=7) {
			civ.setMemberNo(MemberMain.LoginMember.getNo());
			civ.setCouponInfoNo("4");
			
			
		}if(upDownResult>=8||rpsResult==0) {
			civ.setMemberNo(MemberMain.LoginMember.getNo());
			civ.setCouponInfoNo("0");
		}
		
		Connection conn = null;
		int result = 0;
		
		try {
			
			conn = getConnection();
			result = new CouponDao().couponIssued(civ, conn);
			
			
			if (result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
		} catch (Exception e) {
			//롤백 해야함.
			e.printStackTrace();
			rollBack(conn);
		}finally {
			close(conn);
		}
		
		return result;
		
		
		
	}

}
