package trip.min.manager;

import trip.hyewon.lodging.LodgingController;
import trip.min.main.MemberMain;
import trip.min.util.InputUtil;
import trip.se.mainPost.MainPost;
import trip.se.post.PostController;
import trip.se.qna.QnaController;
import trip.se.qnacmt.QnaCmtController;

public class Manager {

	/*
	 * 숙소조회, 등록 및 변경
	 * 숙소 이름 검색
	 * 숙소 등록, 변경 및 삭제
	 * 
	 * 커뮤니티 조회 및 변경
	 * Q&A 조회 및 변경
	 * 회원 조회 및 회원의 닉네임 변경
	 * 숙소 예약 현황 조회 -> Lodging_reservation 테이블 목록
	 * 
	 */
	
	//관리자 메뉴
	public void managerMenu() {
		
		while(true) {
			System.out.println("===== 관리자 메뉴 입니다 =====");
			System.out.println("1. 숙소 조회");
			System.out.println("2. 숙소 등록");
			System.out.println("3. 숙소 변경");
			System.out.println("4. 커뮤니티 조회");
			System.out.println("5. Q&A 조회");
			System.out.println("6. Q&A 변경");
			System.out.println("7. 회원 조회");
			System.out.println("8. 회원정보 변경");
			System.out.println("9. 숙소 예약 현황 조회");
			System.out.println("10. 관리자 메뉴 나가기");
			String input = InputUtil.sc.nextLine();
			switch(input) {
				case "1":
					System.out.println("숙소 조회");
					new LodgingController().searchLodging();
					break;
				case "2":
					System.out.println("숙소 등록");
					new ManagerController().joinLodging();
					break;
				case "3":
					System.out.println("숙소 변경");
					new ManagerController().editLodging();
					break;
				case "4":
					System.out.println("커뮤니티 조회");
					new MainPost().mainPost();
					break;
				case "5":
					System.out.println("Q&A 조회");
					new MainPost().mainQnaPost(); 
					break;
				case "6":
					System.out.println("Q&A 변경");
					new QnaController().qnaView();
					break;
				case "7":
					System.out.println("회원 조회");
					new ManagerController().showListMember();
					break;
				case "8":
					System.out.println("회원 정보 변경");
					new ManagerController().editMember();
					break;
				case "9":
					System.out.println("숙소 예약 현황 조회");
					new LodgingController().showMyReservation(MemberMain.LoginMember.getNo());
					break;
				case "10":
					return;
			}		
		
		}
		
	}//managerMenu
	

	
	
}//class
