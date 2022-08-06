package trip.min.main;

import trip.min.manager.Manager;
import trip.min.manager.ManagerController;
import trip.min.member.MemberController;
import trip.min.member.MemberVo;
import trip.min.util.InputUtil;

public class MemberMain {

	//로그인 멤버 변수 만들기 (전역으로)
	public static MemberVo LoginMember;
	
	public static void main(String[] args) {
		/* 실행시킬때 주의 사항
		 * JDBCTemplate 가서 
		 * url localhost 아닌사람!
		 * 본인 아이피로 변경
		 * 포트값이 다르다면 그것도 변경
		 * 후 실행
		 */
		System.out.println("======== 환영합니다 ========");
		System.out.println("===== A to Trip 입니다 =====");
		
		boolean isManager = false;
		while(true) {
			new MemberMenu().showMenu(isManager);
			//로그인 전
			System.out.println("\n===== 로그인 메뉴 =====");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 아이디, 비밀번호 찾기");
			System.out.println("4. 프로그램 종료");
			System.out.println("5. 관리자 로그인");
			System.out.println("======================");
			System.out.print("입력 : ");
			String input = InputUtil.sc.nextLine();
			switch(input) {
			case "1":
				//로그인
				new MemberController().login(); 
				isManager = false;
				break;
			case "2":
				//회원가입
				new MemberController().join(); 
				break;
			case "3":
				//아이디, 비밀번호 찾기
				new MemberController().findIdPwdView(); 
				break;
			case "4":
				//프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			case "5":
				//관리자 로그인
				boolean ismanager = new ManagerController().managerLogin();
				isManager = ismanager;
				break;
			default :
				System.out.println("다시 입력해 주세요");
			}//switch
			
		}//while
		
	}//main

}//class
