package trip.se.mainPost;

import trip.min.util.InputUtil;
import trip.se.post.PostController;

public class MenuPost {


	public String showPost() {
		System.out.println("===== 게시판 =====");
		System.out.println("1. 게시글 조회");
		System.out.println("2. 게시글 작성");
	
		return InputUtil.sc.nextLine();
	}
	

	public String showPostDetail() {
		System.out.print("\n 조회할 글 번호 (0번 -> 메인메뉴로 가기) : ");
		return InputUtil.sc.nextLine();
	}
	
	public String showComment() {
		System.out.println("===== 선택해 주세요 =====");
		System.out.println("1. 게시글 글 수정");
		System.out.println("2. 게시글 글 삭제");
		System.out.println("3. 게시글 댓글 작성");
		System.out.println("4. 게시글 댓글 수정");
		System.out.println("5. 게시글 댓글 삭제");
		System.out.println("6. 좋아요 누르기");

		return InputUtil.sc.nextLine();
	}
	
	public String showQna() {
		System.out.println("===== Q&A =====");
		System.out.println("1. 문의 게시글 조회");
		System.out.println("2. 문의 게시글 작성");
		
		return InputUtil.sc.nextLine();
	}
	
	public String showQnaDetail() {
		System.out.print("\n 조회할 글 번호 (0번 -> 메인메뉴로 가기) : ");
		return InputUtil.sc.nextLine();
	}
	
	public String showQnaComment() {
		System.out.println("===== 선택해 주세요 =====");
		System.out.println("1. 문의 글 수정");
		System.out.println("2. 문의 글 삭제");
		System.out.println("3. 문의 댓글 작성");
		System.out.println("4. 문의 댓글 수정");
		System.out.println("5. 문의 댓글 삭제");

		return InputUtil.sc.nextLine();
	}
	
}
	

//while(true) {
//	
//	int x = showPost();
//	
//	switch(x) {
//	
//	case 1 : new PostController().postView(); break;
//	
//	case 2 : new PostController().postWrite(); break;
//	
//	
//	}
//}
















