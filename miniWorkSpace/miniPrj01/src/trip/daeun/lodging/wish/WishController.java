package trip.daeun.lodging.wish;

import java.sql.Connection;

import trip.min.main.MemberMain;
import trip.min.util.InputUtil;

public class WishController {
	
	public void wish() {
		
		
		//찜하기 체크
	
		WishVo vo = new WishVo();
		String lodgingNo = vo.getLodgingNo();
		String memberNo = vo.getMemberNo();
		
		while(true) {
			System.out.println("찜하시겠습니까? Y/N");
			System.out.print("입력 : ");
			String num = InputUtil.sc.nextLine();

			if(num.equalsIgnoreCase("Y")) {
				vo.setMemberNo(MemberMain.LoginMember.getNo());
				vo.setLodgingNo(vo.getLodgingNo());
				vo.setCancelYn("Y");
				System.out.println("찜 되었습니다.");
				
				return;
				
			}else {
				vo.setCancelYn("N");
				
				return;
			}
		}
	}
	
	
	public void wishCancel() {
		
		
		WishVo vo = new WishVo();
		String lodgingNo = vo.getLodgingNo();
		String memberNo = vo.getMemberNo();
		
		while(true){
			
		System.out.println("찜을 해제하시겠습니까? Y/N");
		System.out.print("입력 : ");
		String num = InputUtil.sc.nextLine();

		if(num.equalsIgnoreCase("Y")) {
			vo.setMemberNo(MemberMain.LoginMember.getNo());
			vo.setLodgingNo(vo.getLodgingNo());
			vo.setCancelYn("N");
			System.out.println("찜 해제되었습니다");
			return;
		}else {
			vo.setCancelYn("Y");
			return;
		}
		
		}
	}
	
	
}
