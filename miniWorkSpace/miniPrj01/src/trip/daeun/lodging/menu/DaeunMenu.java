package trip.daeun.lodging.menu;

import trip.daeun.lodging.wish.WishController;
import trip.daeun.lodging.wish.WishService;
import trip.daeun.lodging.wish.WishVo;
import trip.hyewon.lodging.LodgingVo;
import trip.min.util.InputUtil;

public class DaeunMenu {

	public int showMenu() {	
		
		System.out.print("원하는 숙소 번호를 입력해 주세요. : \n");

	
		return InputUtil.getInt();
		
	}
	
	

	public boolean showDetailMenu(String memberNo, String lodgingNo)
	{
		
		LodgingVo vo = new LodgingVo();
		vo.setNo(memberNo);
		vo.setLodgingCode(lodgingNo);
		boolean isWish = new WishService().selectWish(vo);
	//	WishVo vo = new WishVo();
	//	new WishController().wish();
		
			
//		if(isWish == true) {
//			
//			System.out.println("1. 예약하기");
//			System.out.println("2. 찜 취소하기");
//			System.out.println("3. 숙소 리뷰 보기");
//		}else {
//			
//			System.out.println("1. 예약하기");
//			System.out.println("2. 찜하기");
//			System.out.println("3. 숙소 리뷰 보기");
//		}
//		
//		System.out.println("4. 숙소 리스트로 돌아가기");
		
		return isWish;
		}
	
	
}
