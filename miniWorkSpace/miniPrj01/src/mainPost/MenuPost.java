package mainPost;

import util.Util;

public class MenuPost {

	public int showMenu() {
		System.out.println("1. 게시글");
		System.out.println("2. 게시글 댓글");
		System.out.println("3. 좋아요");
		
		return Util.getInt();
	}	
		
	
	public int showPost() {
		System.out.println("===== 게시판 =====");
		System.out.println("1. 게시글 조회");
		System.out.println("2. 게시글 작성");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
	
		return Util.getInt();
	}
	

	public int showPostDetail() {
		System.out.print("\n 조회할 글 번호 (0번 -> 메인메뉴로 가기) : ");
		return Util.getInt();
	}
	
	
}

















//public int showComment() {
//System.out.println("===== 댓글 =====");
//System.out.println("3. 게시글 댓글 조회");
//System.out.println("4. 게시글 댓글 작성");
//System.out.println("5. 게시글 댓글 수정");
//System.out.println("6. 게시글 댓글 삭제");
//
//return Util.getInt();
//}
//
//public int showLike() {
//System.out.println("===== 좋아요 =====");
//System.out.println("1. 좋아요 누르기");
//System.out.println("2. 좋아요 누르기 취소");
//
//return Util.getInt();
//}
//