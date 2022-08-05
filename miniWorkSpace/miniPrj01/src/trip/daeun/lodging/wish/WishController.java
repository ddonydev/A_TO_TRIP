package trip.daeun.lodging.wish;

import java.sql.Connection;
import java.util.List;

import trip.hyewon.lodging.LodgingVo;
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
	
	
	public void showZzimList() {
		
		List<LodgingVo> lodgingVoList = new WishService().showZzimList();
		System.out.println("----- 찜한 숙소 목록 -----");
		
		for(int i = 0; i < lodgingVoList.size(); ++i) {
			LodgingVo temp = lodgingVoList.get(i);
			
			String no = temp.getNo();
			String name = temp.getName();
			String address = temp.getAddress();
			String phone = temp.getPhone();
			
			System.out.println("[" + no + "]");
			System.out.println("숙소 : " + name);
			System.out.println();
			System.out.println("------------------------");
			System.out.println("주소 : " + address);
			System.out.println();
			System.out.println("------------------------");
			System.out.println("번호 : " + phone);
			System.out.println();
			System.out.println("------------------------");
	
		}
		
		
		
		
		
	}
	
	
	
	
}
