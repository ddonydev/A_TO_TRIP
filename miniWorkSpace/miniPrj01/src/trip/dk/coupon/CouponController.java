package trip.dk.coupon;

import java.util.List;

import trip.dk.event.EventGameController;
import trip.dk.event.EventParticipateVo;
import trip.min.main.MemberMain;
import trip.min.member.MemberMypage;
import trip.min.member.MemberVo;
import trip.min.util.InputUtil;

public class CouponController {
	
	//[닉네임]님이 보유한 쿠폰 내역입니다.
	public void showCoupon() {
		List<CouponVo> couponVoList = new CouponService().showList();
		EventParticipateVo epv = new EventParticipateVo();
		
		System.out.println("==== "+MemberMain.LoginMember.getNick()+"님이 현재 보유 하고있는 쿠폰 내역입니다. ====");
		if(couponVoList.size() != 0) {
			
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
		}else {
			System.out.println("보유한 쿠폰이 없습니다.");
		}
	
	System.out.println();
	
	System.out.println(" 0번을 누르시면 마이페이지로 돌아갑니다 : ");
	String num = InputUtil.sc.nextLine();
	if(num == "0") {
		new MemberMypage().myPageMenu();
	}
	
	

}
	

}
