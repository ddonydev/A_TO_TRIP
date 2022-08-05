package trip.dk.coupon;

import java.util.List;

import trip.dk.event.EventGameController;
import trip.dk.event.EventParticipateVo;
import trip.min.main.MemberMain;
import trip.min.member.MemberVo;
import trip.min.util.InputUtil;

public class CouponController {
	
	//[닉네임]님이 보유한 쿠폰 내역입니다.
	public void showCoupon() {
		List<CouponVo> couponVoList = new CouponService().showList();
		EventParticipateVo epv = new EventParticipateVo();
		
		System.out.println("==== "+MemberMain.LoginMember.getNick()+"님이 현재 보유 하고있는 쿠폰 내역입니다. ====");
		
		for(int i = 0; i<couponVoList.size();i++) {
					
					//쿠폰코드, 쿠폰할인금액 , 사용여부 보여주
					CouponVo cv = couponVoList.get(i);
					String mNo = cv.getMemberNo();
					String cNo = cv.getCouponInfoNo();
					String cCode = cv.getCouponCode();
					String cDiscount = cv.getDiscountRate();
					String usedYn = cv.getUsedYn();
					
					System.out.println("쿠폰코드 : "+cCode+" | 할인 금액 : "+cDiscount+" | 사용여부 : "+usedYn);
					

	}
	
	
	
	
	

}}
