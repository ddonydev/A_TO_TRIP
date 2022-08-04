package trip.min.main;

import trip.se.mainPost.MenuPost;
import trip.dk.event.EventController;
import trip.dk.menu.Menu;
import trip.hyewon.lodging.LodgingController;
import trip.min.main.MemberMain;
import trip.min.manager.Manager;
import trip.min.manager.ServiceCenter;
import trip.min.member.MemberMypage;
import trip.min.util.InputUtil;

public class MemberMenu {

	public void showMenu() {
		
		if(MemberMain.LoginMember != null) {
			//로그인 후
			showLoginMenu();
		}else {
			//로그인 전
			System.out.println("\n===== 로그인 메뉴 =====");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 아이디, 비밀번호 찾기");
			System.out.println("4. 프로그램 종료");
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
		
		while(true) {
			System.out.println("1. 숙소 검색");
			System.out.println("2. 추천 숙소 조회");
			System.out.println("3. 인기 숙소 조회");
			System.out.println("4. 여행 커뮤니티");
			System.out.println("5. 마이페이지");
			System.out.println("6. 이벤트");
			System.out.println("7. 고객센터");
			System.out.println("8. 프로그램 종료");
			System.out.println("9. 관리자페이지");
			String no = InputUtil.sc.nextLine();
			switch(no) {
				case "1":
					System.out.println("숙소 검색 으로 이동합니다.");
					new LodgingController().searchLodging();
					break;
				case "2":
					System.out.println("추천 숙소 조회");
					new LodgingController().showRecommendLodging();
					break;
				case "3":
					System.out.println("인기 숙소 조회");
					new LodgingController().showPopularLodging();
					break;
				case "4":
					System.out.println("여행 커뮤니티로 이동합니다."); 
					new MenuPost().showPost();
					break;
				case "5":
					System.out.println("마이페이지로 이동합니다.");
					new MemberMypage().myPageMenu();
					break;
				case "6":
					System.out.println("이벤트로 이동합니다.");
                    new EventController().showEventList();
					break;
				case "7":
					System.out.println("고객센터로 이동합니다.");
					new ServiceCenter().CenterView();
					break;
				case "8":
					while(true) {
						if(no.contains(no)) {
							System.out.println("4번을 입력하시면 종료됩니다.");
						}
						return;
					}
					
				case "admin9":
					System.out.println("관리자 페이지로 이동합니다.");
					new Manager().managerMenu();
				default :
					System.out.println("다시 입력해주세요.");
			}
		}
		
		
	}
	
	public int showmenu() {

        System.out.println("------ 메인 메뉴 ------");
        System.out.println("6. 이벤트");



        return InputUtil.getInt();
    }


    public String showDetailByNo() {

        System.out.println("참여할 게임을 선택 해주세요. (0번은 메인 메뉴)");

        return InputUtil.sc.nextLine();

    }

    public String eventGame() {

        return InputUtil.sc.nextLine();
    }
	
}//class
