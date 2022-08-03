package trip.min.manager;

import trip.min.util.InputUtil;

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
		System.out.println("===== 관리자 메뉴 입니다 =====");
		
		System.out.println("1. 숙소 조회");
		System.out.println("2. 숙소 등록");
		System.out.println("3. 숙소 변경");
		System.out.println("4. 커뮤니티 조회");
		System.out.println("5. 커뮤니티 변경");
		System.out.println("6. Q&A 조회");
		System.out.println("7. Q&A 변경");
		System.out.println("8. 회원 조회");
		System.out.println("9. 회원정보 변경");
		System.out.println("10. 숙소 예약 현황 조회");
		
		
		while(true) {
			int input = InputUtil.getInt();
			switch(input) {
				case 1:
					searchManager();
					break;
				case 2:
					insertManager();
					break;
				case 3:
					updateManager();
					break;
				case 4:
					communityselectManager();
					break;
				case 5:
					comunityupadateManager();
					break;
				case 6:
					QnASelectManager();
					break;
				case 7:
					QnAUpdateManager();
					break;
				case 8:
					memberSelectManager();
					break;
				case 9:
					memberUpdateManager();
					break;
				case 10:
					lodgingMagager();
					break;
		}		
		
		}
		
		
	}
	
	//관리자 숙소 조회
	public void searchManager() {
		//숙소 검색 or 조회(select)
		System.out.println("숙소 조회");
		
	}
	
	//관리자 숙소 등록
	public void insertManager() {
		//숙소 등록(Insert)
		System.out.println("숙소 등록");
		
	}
	
	//관리자 숙소 변경
	public void updateManager() {
		//숙소 변경(update)
		System.out.println("숙소 변경");
		
	}
	
	//관리자 커뮤니티 조회
	public void communityselectManager() {
		//커뮤니티 조회(select)
		System.out.println("커뮤니티 조회");
	}
	
	//관리자 커뮤니티 변경
	public void comunityupadateManager() {
		//커뮤니티 변경(update)
		System.out.println("커뮤니티 변경");
		
	}
	
	//관리자 Q&A 조회 및 변경
	public void QnASelectManager() {
		//Q&A 조회(select)
		System.out.println("Q&A 조회");
		
	}
	
	//관리자 Q&A 변경
	public void QnAUpdateManager() {
		//Q&A 변경(update)
		System.out.println("Q&A 변경");
		
	}
	
	//관리자 회원조회 및 회원의 닉네임 변경
	public void memberSelectManager() {
		//회원 조회(select)
		System.out.println("회원 조회");
		
	}
	
	//관리자 회원 닉네임 변경
	public void memberUpdateManager() {
		//회원 정보 변경(update)
		System.out.println("회원 정보 변경");
		
	}
	
	//숙소 예약 현황 조회 -> Lodging_reservation 테이블 목록
	public void lodgingMagager() {
		//숙소 조회
		System.out.println("숙소 예약 현황 조회");
		
	}
	
	
	
	
}//class
