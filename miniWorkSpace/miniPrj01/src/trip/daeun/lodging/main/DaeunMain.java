package trip.daeun.lodging.main;

import trip.daeun.lodging.information.InformationController;
import trip.daeun.lodging.menu.DaeunMenu;

public class DaeunMain {

	/*
	 * 숙소이름, 방, 1박가격, 조식여부, 위치 출력

		1. 예약하기
		2. 찜하기 → 찜 완료되었습니다 출력 
			찜취소 → 찜 취소되었습니다 출력
		3. 숙소 리뷰보기 
		4. 숙소 리스트로 돌아가기
	 * */
	
	public static void main(String[] args) {

	
		System.out.println("######## ######## ######## ######## ######## ######## ######## ######## ######## ");
		System.out.println("###  ### ######## #      # ##    ## ######## #      # #     ## ##    ## #     ## ");
		System.out.println("##    ## ######## ###  ### #  ##  # ######## ###  ### #  ##  # ###  ### #  ##  # ");
		System.out.println("#  ##  # ######## ###  ### #  ##  # ######## ###  ### #  ##  # ###  ### #  ##  # ");
		System.out.println("#      # ######## ###  ### #  ##  # ######## ###  ### #     ## ###  ### #     ## ");
		System.out.println("#  ##  # ######## ###  ### #  ##  # ######## ###  ### #  #  ## ###  ### #  ##### ");
		System.out.println("#  ##  # ######## ###  ### ##    ## ######## ###  ### #  ##  # ##    ## #  #####");
		System.out.println("######## ######## ######## ######## ######## ######## ######## ######## ######## ");
		
		
		new InformationController().showInformation();
		DaeunMenu menu = new DaeunMenu();	
		//menu.showMenu();
		
		int input = menu.showDetailMenu();
		
		switch(input) {
		case 1:
			/*예약하기*/
			System.out.println("예약 페이지로 넘어갑니다.");
			break;
			
		case 2:
			/*찜하기*/
			//new WishController().wish();
			break;
			
		case 3:
			/*찜하기 취소*/
			break;
			
		case 4:
			/*숙소 리뷰 보기*/
			System.out.println("숙소 리뷰 페이지로 넘어갑니다.");
			break;
			
		case 5:
			/*숙소 리스트로 돌아가기*/
			System.out.println("숙소 리스트로 돌아갑니다.");
			break;
		
		
		}
		
		
	}

}
