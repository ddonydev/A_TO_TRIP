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
		System.out.println();
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("----- 내가 찜한 숙소 목록 ----------");
		System.out.println("--------------------------------");
	
		for(int i = 0; i < lodgingVoList.size(); ++i) {
			LodgingVo temp = lodgingVoList.get(i);
			
			String no = temp.getNo();
			String name = temp.getName();
			String address = temp.getAddress();
			String phone = temp.getPhone();
			
			System.out.println();
			System.out.println("   [" + no + "]");
			System.out.println();
			System.out.println("   ღ 숙소 ღ : " + name);
			System.out.println();
			System.out.println("   ღ 주소 ღ : " + address);
			System.out.println();
			System.out.println("   ღ 번호 ღ : " + phone);
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
			System.out.println("--------------------------------");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	
		
		
	}
	
	
	
	
}
