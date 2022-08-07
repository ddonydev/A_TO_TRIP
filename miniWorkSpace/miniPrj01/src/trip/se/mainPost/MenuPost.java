package trip.se.mainPost;

import trip.min.util.InputUtil;
import trip.se.post.PostController;

public class MenuPost {

	public String showPost() {
		System.out.println("===== 게시판 =====");
		System.out.println("1. 게시글 조회");
		System.out.println("2. 게시글 작성");
		System.out.println("3. 메인 메뉴");
		
		System.out.print("\n입력 : ");
	
		return InputUtil.sc.nextLine();
	}//showPost

	public String showPostDetail() {
		System.out.print("\n 조회할 글 번호(Q -> 이전 메뉴) : ");
		
		return InputUtil.sc.nextLine();
	}//showPostDetail
	
	public String showComment() {
		System.out.println("===== 선택해 주세요 =====");
		System.out.println("1. 게시글 글 수정");
		System.out.println("2. 게시글 글 삭제");
		System.out.println("3. 게시글 댓글 작성");
		System.out.println("4. 게시글 댓글 수정");
		System.out.println("5. 게시글 댓글 삭제");
		System.out.println("6. 👍 좋아요 누르기 👍");
		System.out.println("7. 이전 메뉴");
		
		System.out.print("\n입력 : ");

		return InputUtil.sc.nextLine();
	}//showComment
	
	public String showQna() {
		System.out.println("===== Q&A =====");
		System.out.println("1. 문의 게시글 조회");
		System.out.println("2. 문의 게시글 작성");
		System.out.println("3. 메인 메뉴");		
		
		System.out.print("\n입력 : ");

		return InputUtil.sc.nextLine();
	}//showQna
	
	public String showQnaDetail() {
		System.out.print("\n 조회할 글 번호(Q -> 이전 메뉴) : ");
		return InputUtil.sc.nextLine();
	}//showQnaDetail
	
	public String showQnaComment() {
		System.out.println("===== 선택해 주세요 =====");
		System.out.println("1. 문의 글 수정");
		System.out.println("2. 문의 글 삭제");
		System.out.println("3. 문의 댓글 작성");
		System.out.println("4. 문의 댓글 수정");
		System.out.println("5. 문의 댓글 삭제");
		System.out.println("6. 이전 메뉴");
		
		System.out.print("\n입력 : ");

		return InputUtil.sc.nextLine();
	}//showQnaComment
	
	
}//class
	















