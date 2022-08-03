package trip.daeun.lodging.menu;

import trip.daeun.lodging.utill.InputUtill;

public class DaeunMenu {

	public int showMenu() {	
		
		System.out.print("원하는 숙소 번호를 입력해 주세요. : \n");

	
		return InputUtill.getInt();
		
	}
	
	
	public int showDetailMenu() {
		
		System.out.println("1. 예약하기");
		System.out.println("2. 찜하기");
		System.out.println("3. 찜하기 취소");
		System.out.println("4. 숙소 리뷰 보기");
		System.out.println("5. 리스트로 돌아가기");
		
		return InputUtill.getInt();
		
	}
	
	
	
	
}
