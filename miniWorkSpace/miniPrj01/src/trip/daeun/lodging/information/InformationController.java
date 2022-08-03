package trip.daeun.lodging.information;

import trip.daeun.lodging.menu.DaeunMenu;

public class InformationController {

	/*
	 * 
	 * 숙소 리스트에서 입력 	받기
	 * 숙소이름, 방, 1박 가격, 조식 여부, 위치 조회

		1. 예약하기
		2. 찜하기 → 찜 완료되었습니다 출력 
			찜취소 → 찜 취소되었습니다 출력
		3. 숙소 리뷰보기 
		4. 목록으로 돌아가기
	 * */
	
	public void showInformation(int num) {

	//숙소 번호 받기
	
	//숙소 번호 받으면 ? -> 해당 숙소 정보 상세 조회
	InformationVo vo = new InformationService().showDetailByNo(num);
	
	//실행 결과(게시글 객체) 화면에 보여주기
	System.out.println("-----------------------");
	System.out.println("----- 숙소 상세 정보 -----");
	System.out.println("-----------------------");
	System.out.println("숙소 이름 : " + vo.getName() + vo.getAddress());
	System.out.println("숙소 타입 : " + vo.getRoom());
	System.out.println("가격 : " + vo.getPrice() + "원");
	System.out.println("조식 여부 : " + vo.getBreakfast());
	System.out.println("-----------------------");
	System.out.println("-----------------------");
	System.out.println("맞으시면 다음으로 넘어가주세요.");	
	
	

		//DB에서 숙소 불러오기
		//오늘 한 거 토대로 적용해보기
		
	}
	
	
	
	public void wish() {
		// 1. 찜하기 누른 사람(== 로그인 한 사람)
		// 2. 찜하기 누른 숙소 번호
		
		
	}
	
	
	
	
}
