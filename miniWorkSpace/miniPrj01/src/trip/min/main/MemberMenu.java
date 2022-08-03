package trip.min.main;

import trip.min.main.MemberMain;
import trip.min.manager.Manager;
import trip.min.util.InputUtil;

public class MemberMenu {

	public void showMenu() {
		
		if(MemberMain.LoginMember == null) {
			//로그인 전
			System.out.println("\n----- 로그인 메뉴 -----");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 아이디, 비밀번호 찾기");
			System.out.println("4. 여행커뮤니티 글 목록 조회");
			System.out.println("5. 프로그램 종료");
		}else {
			//로그인 후
			showLoginMenu();
		}
		
	}
	
	public void showLoginMenu() {
		
		if(MemberMain.LoginMember != null) {
			//로그인 후
			System.out.println("=====" +MemberMain.LoginMember.getNick() +" 님 반갑습니다." + "=====");
			System.out.println("===== 원하시는정보를 입력하세요 =====");
			loginMenu();
		
		}//if
		
	}//showLoginMenu
	
	public void loginMenu() {
		
		System.out.println("1. 숙소 검색");
		System.out.println("2. 추천 숙소 조회");
		System.out.println("3. 인기 숙소 조회");
		System.out.println("4. 여행 커뮤니티");
		System.out.println("5. 마이페이지");
		System.out.println("6. 이벤트");
		System.out.println("7. 고객센터");
		System.out.println("8. 프로그램 종료");
		System.out.println("9. 관리자페이지");

		while(true) {
			String no = InputUtil.sc.nextLine();
			switch(no) {
				case "1":
					System.out.println("숙소 검색 으로 이동합니다.");
					break;
				case "2":
					System.out.println("추천 숙소 조회");
					break;
				case "3":
					System.out.println("인기 숙소 조회");
					break;
				case "4":
					System.out.println("여행 커뮤니티로 이동합니다.");
					break;
				case "5":
					System.out.println("마이페이지로 이동합니다.");
					break;
				case "6":
					System.out.println("이벤트로 이동합니다.");
					break;
				case "7":
					System.out.println("고객센터로 이동합니다.");
					break;
				case "8":
					while(true) {
						if(no.contains(no)) {
							System.out.println("5번을 입력하시면 종료됩니다.");
						}else {
							System.out.println("5번만 입력 가능합니다.");
						}
						return;
					}
				case "admin9":
					System.out.println("관리자 페이지로 이동합니다.");
					Manager manager = new Manager();
					manager.managerMenu();
				default :
					System.out.println("다시 입력해주세요.");
			}
		}
		
		
	}
	
}//class
