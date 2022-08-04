package trip.dk.coupon;

import java.util.List;


import trip.min.main.MemberMain;
import trip.min.util.InputUtil;

public class CouponController {
	
	//[닉네임]님이 보유한 쿠폰 내역입니다.
	public String showCoupon() {
		List<CouponIssuedVo> CouponVoList = new CouponService().showList();
		System.out.println("==== "+MemberMain.LoginMember.getNick()+"님이 현재 보유 하고있는 쿠폰 내역입니다. ====");
		
		
		
		
		return InputUtil.sc.nextLine();
	}
	
	
	

}
