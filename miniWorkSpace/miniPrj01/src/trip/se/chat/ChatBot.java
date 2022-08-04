package trip.se.chat;

import trip.min.main.MemberMenu;
import trip.min.util.InputUtil;

public class ChatBot {

	public void chatBot() {
		
		while(true) {
			
			System.out.println("=====챗봇 상담 시작=====");
			
			System.out.print("키워드를 입력 해주세요 : ");
			String input = InputUtil.sc.nextLine();

			if(input.contains("예약")) {
				System.out.println("예약하기는 메인페이지에서 해주세요.");
			}
			
			if(input.contains("취소")) {
				System.out.println("예약 취소는 마이페이지에서 해주세요.");
			}

			if(input.contains("변경")) {
				System.out.println("예약 변경은 마이페이지에서 해주세요.");
			}
			
			if(input.contains("닉네임")) {
				System.out.println("닉네임 변경은 마이페이지에서 해주세요.");
			}
			
			if(input.contains("탈퇴")) {
				System.out.println("회원탈퇴는 마이페이지에서 해주세요.");
			}
			
			if(input.contains("쿠폰")) {
				System.out.println("쿠폰 조회는 마이페이지에서 해주세요.");
			}
			
			if(input.contains("찜")) {
				System.out.println("숙소 찜 조회는 마이페이지에서 해주세요.");
			}
			
			if(input.contains("게시글")) {
				System.out.println("게시글 관련 사항은 게시판에서 해주세요.");
			}
			
			if(input.contains("종료")) {
				System.out.println("상담을 종료합니다.\n\n");
				return;
			}
		}
		
		
	}

	public void show() {
		System.out.println("[A To Trip 챗봇 입니다.]\n");
		System.out.println("안녕하세요 무엇을 도와드릴까요?");
		
		System.out.println("1. 상담 시작하기");
		System.out.println("2. 나가기");
		
		System.out.print("번호를 입력해주세요 : ");
		
		String x = InputUtil.sc.nextLine();
		switch(x) {
		case "1" : chatBot(); break;
		case "2" : new MemberMenu().loginMenu(); break;
		}
		
	}
	
	
}





