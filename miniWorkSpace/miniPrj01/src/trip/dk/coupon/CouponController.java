package trip.dk.coupon;

import java.util.List;

import trip.dk.event.EventGameController;
import trip.min.main.MemberMain;
import trip.min.util.InputUtil;

public class CouponController {
	
	//[닉네임]님이 보유한 쿠폰 내역입니다.
	public String showCoupon() {
		
		
		
		List<CouponVo> couponVoList = new CouponService().showList();
		System.out.println("==== "+MemberMain.LoginMember.getNick()+"님이 현재 보유 하고있는 쿠폰 내역입니다. ====");
		for(int i = 0; i<couponVoList.size();++i) {
					
					
					//쿠폰코드, 쿠폰할인금액 , 사용여부 보여주
					CouponVo cv = couponVoList.get(i);
					String cNo = cv.getCouponInfoNo();
					String cCode = cv.getCouponCode();
					String cDiscount = cv.getDiscountRate();
					String usedYn = cv.getUsedYn();
					
					
					
					if(cNo.equals("1")||cNo.equals("4")) {
						System.out.println("쿠폰코드 : "+cCode+" | 할인 금액 : "+cDiscount+" [3만원 이상시] | 사용여부 : "+usedYn);
						break;
					}
					if(cNo.equals("2")||cNo.equals("5")) {
						System.out.println("쿠폰코드 : "+cCode+" | 할인 금액 : "+cDiscount+" [10만원 이상시] | 사용여부 : "+usedYn);
						break;
					}
					if(cNo.equals("3")||cNo.equals("6")) {
						System.out.println("쿠폰코드 : "+cCode+" | 할인 금액 : "+cDiscount+" [30만원 이상시] | 사용여부 : "+usedYn);
						break;
					}
					if(cNo.equals("0")) {
						System.out.println(" 현재 가지고 있는 쿠폰이 없습니다.");
						
					}
					
					
					
				}
		
		
		
		return InputUtil.sc.nextLine();
	}
	
	
	

}
