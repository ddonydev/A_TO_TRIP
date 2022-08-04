package trip.se.mainPost;

import trip.se.post.PostController;
import trip.se.qna.QnaController;

public class MainPost {

	public void mainPost() {

		MenuPost mp = new MenuPost();
		
		while(true) {
				
			String x = mp.showPost();
		
			switch(x) {
			
			case "1" : new PostController().postView(); break;
			
			case "2" : new PostController().postWrite(); break;
				
				
			}
			
		}
		
	}

	public void mainQnaPost() {
		
		MenuPost mp = new MenuPost();
		
		while(true) {
				
			String x = mp.showQna();
		
			switch(x) {
			
			case "1" : new QnaController().qnaView(); break;
			
			case "2" : new QnaController().qnaWrite(); break;
				
			}
			
		}
		
	}
	
	
}

// < 여행 커뮤니티 > 
/* 1. 게시물 작성
 * 	a. 댓글 작성
 * 	b. 게시물 목록 돌아가기
 * 	c. 메인 메뉴로 돌아가기
 * 	d. 댓글 수정 -> 댓글 번호 입력 -> 비밀번호 확인
 * 	e. 댓글 삭제 -> 댓글 번호 입력 -> 비밀번호 확인
 * 	f. 게시물 수정 -> 비밀번호 확인
 * 	g. 게시물 삭제 -> 비밀번호 확인
 * 	h. 좋아요(취소여부 체크 안함)
 * 
 * 2. 게시글 목록 조회
 * 	a. 조회 할 글번호(0번은 메인 메뉴)
 */

// < 고객 센터 >
/* 1. Q&A
 * 	A. 댓글 작성
 * 	B. Q&A 목록 돌아가기
 * 	C. 메인 메뉴로 돌아가기
 * 	D. 댓글 수정 -> 댓글 번호 입력 -> 비밀번호 확인
 * 	E. 댓글 삭제 -> 댓글 번호 입력 -> 비밀번호 확인
 * 
 * 		a. 문의 사항 목록 보이게 
 *  	b. 문의 글 작성하기
 * 		c. 게시글 목록 조회(번호 입력 시 문의글 상세페이지로 이동)
 * 			i.  Q&A 수정
 * 			ii. Q&A 삭제
 * 
 * 2. 챗봇
 *  a. 무슨 도움이 필요하신가요?
 *  b. 입력
 *  c. 매크로 출력
 *  d. 종료 입력하면 나가기
 */