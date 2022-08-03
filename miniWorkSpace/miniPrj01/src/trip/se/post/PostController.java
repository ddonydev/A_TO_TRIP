package trip.se.post;

import java.sql.Timestamp;
import java.util.List;

import trip.min.util.InputUtil;
import trip.se.mainPost.MenuPost;


public class PostController {
	
	// 게시글 작성
	public void postWrite() {
		
//		if(Main.loginMember == null) {
//			System.out.println("로그인을 먼저 해주세요.\n\n");
//			return;
//		}
		
		System.out.println("\n----- 게시글 작성 -----");
		
		// 데이터 받기
		System.out.print("제목 : ");
		String title = InputUtil.sc.nextLine();
		System.out.print("내용 : ");
		String content = InputUtil.sc.nextLine();
		
		int memberNo = 5;
		
		// 데이터 뭉치기
		PostVo vo = new PostVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(memberNo);
		
		int result = new PostService().write(vo);
		
		// insert 결과에 따라 로직 처리
		if(result == 1) {
			// 글 작성 성공
			System.out.println("게시글 작성 성공!\n\n");
		}else {
			//글 작성 실패
			System.out.println("게시글 작성 실패...");
		}
		
		
	}//postWrite
	
	// 게시글 조회
	public void postView() {
		
		List<PostVo> postVoList = new PostService().showList();
		
		System.out.println("----- 게시글 조회 -----");
		
		for(int i = 0; i < postVoList.size(); i++) {
			
			PostVo temp = (PostVo)postVoList.get(i);
			
			int no = temp.getNo();
			String title = temp.getTitle();
			int writer = temp.getWriter();
			int like = temp.getLike();
			int viewcount = temp.getViewCount();
			Timestamp date = temp.getDate();
			
			// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자
			System.out.println("[번호]" + " 제목  | 작성자  | 좋아요 | 조회수 | 작성일자");
			System.out.println("[" + no + "] " + title + " |   " + writer + "  |  " + like + "  |  " + viewcount + "  |  " + date + "\n");
		}
		
		
		// 상세 조회
		// 출력문, 입력받기
		int num = new MenuPost().showPostDetail();
		
		if(num == 0) {
			System.out.println("\n 메인 메뉴로 돌아갑니다. \n");
			return;
		}
	
		// 글 번호 받으면
		PostVo vo = new PostService().showPostDetail(num);
		
		// SQL 실행 결과(게시글 객체) 화면에 보여주기
		System.out.println("\n----- 게시글 상세조회 -----");
		System.out.print("제목 : " + vo.getTitle() + " | ");
		System.out.print("작성자 : " + vo.getWriter() + " | ");
		System.out.print("작성일 : " + vo.getDate());
		System.out.println();	// 줄바꿈
		System.out.println("내용 : " + vo.getContent()+"\n");
		
	}
	
	// 게시글 수정
	public void postEdit() {
		
		System.out.println("\n----- 게시글 수정 -----");
		
		// 데이터 가져와서
		
		
		// 가져온 데이터를 수정 (데이터 받기)
		System.out.print("제목 : ");
		String title = InputUtil.sc.nextLine();
		System.out.print("내용 : ");
		String content = InputUtil.sc.nextLine();
		
		// 데이터 합치기
		
	}
	
	
	
}// class

/*
 * 1. 게시글 작성
 * 2. 게시글 수정
 * 3. 게시글 삭제
 * 4. 댓글 작성
 * 5. 댓글 수정
 * 6. 댓글 삭제
 */