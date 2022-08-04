package trip.min.manager;

import trip.min.util.InputUtil;
import trip.se.mainPost.MainPost;
import trip.se.chat.ChatBot;

public class ServiceCenter {
	/*
	 * 1. Q&A
	 *    - 문의사항 목록 보이게
	 *    - 문의 글 작성하기
	 *    - 게시글 목록 조회 (번호 입력시 문의 글 상세 페이지로 이동)
	 *      - Q&A 수정
	 *      - Q&A 삭제
	 * 2. 챗봇
	 */
	
	public void CenterView() {
		
		while(true) {
			System.out.println("\n===== 고객센터 입니다. =====");
			System.out.println("무엇을 도와드릴까요 ? ");
			System.out.println("1. QnA");
			System.out.println("2. 챗봇 상담");
			System.out.println("3. 나가기");
			String input = InputUtil.sc.nextLine();
			switch(input){
				case "1" :
					new MainPost().mainQnaPost(); 
					break;
				case "2" :
					new ChatBot().show(); 
					return;
				case "3" :
					System.out.println("이전 메뉴로 돌아갑니다.");
					return;
				default :
					System.out.println("잘못 입력하셨습니다.");
			}
		
		}
		
	}
	
	public void QnA() {
		//고객센터
		System.out.println("Q&A 관련 페이지입니다.");
	}
	
		
	public void chatBot() {
		//챗봇
		System.out.println("챗봇 페이지 입니다.");
	}
}
