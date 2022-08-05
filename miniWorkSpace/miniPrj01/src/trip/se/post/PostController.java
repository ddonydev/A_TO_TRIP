package trip.se.post;

import java.sql.Timestamp;
import java.util.List;

import trip.se.comment.CmtController;
import trip.se.comment.CmtService;
import trip.se.comment.CmtVo;
import trip.se.mainPost.MenuPost;
import trip.se.qna.QnaVo;
import trip.dk.coupon.CouponService;
import trip.dk.coupon.CouponVo;
import trip.min.main.MemberMain;
import trip.min.member.MemberVo;
import trip.min.util.InputUtil;

public class PostController {
	
	// 게시글 작성
	public void postWrite() {
		
		System.out.println("\n----- 게시글 작성 -----");
		
		// 데이터 받기
		System.out.print("제목 : ");
		String title = InputUtil.sc.nextLine();
		System.out.print("내용 : ");
		String content = InputUtil.sc.nextLine();
		
		String memberNo = MemberMain.LoginMember.getNo();
		
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
			
			String no = temp.getNo();
			String title = temp.getTitle();
			String writer = temp.getNick();
			String like = temp.getLike();
			String viewcount = temp.getViewCount();
			Timestamp date = temp.getDate();
			
			// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자
			System.out.println("[" + no + "] " + title + " |   " + writer + "  |  " +"좋아요 : "+ like + "  |  " + "조회수 : " + viewcount + "  |  " + date + "\n");
		}
		
		// 상세 조회
		// 출력문, 입력받기
		String num = new MenuPost().showPostDetail();
		
		// 글 번호 받으면
		PostVo vo = new PostService().showPostDetail(num);
		
		// SQL 실행 결과(게시글 객체) 화면에 보여주기
		System.out.println("\n----- 게시글 상세조회 -----");
		System.out.print("제목 : " + vo.getTitle() + " | ");
		System.out.print("작성자 : " + vo.getNick() + " | ");
		System.out.print("작성일 : " + vo.getDate());
		System.out.println();	// 줄바꿈
		System.out.println("내용 : " + vo.getContent()+"\n");
		
		List<CmtVo> cmtVoList = new CmtService().showList(num);
		
		if(cmtVoList.size() > 0) {
			System.out.println("----- 댓글 -----");
			for(int i = 0; i < cmtVoList.size(); i++) {
						
				CmtVo temp = (CmtVo)cmtVoList.get(i);

				String cmtNo = temp.getCmtNo();
				String cmt = temp.getCmt();
				Timestamp date = temp.getDate();
				String nick = temp.getNick();
				
				// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자
				System.out.println("[" + cmtNo + "] " + cmt + " |   " + nick + "  |  " + date + "\n");
			}
		}
		
		String x = new MenuPost().showComment();
		
		switch(x) {
		case "1" : editPost(num); break;// 게시글 수정
		case "2": deletePost(num); break;
		case "3": new CmtController().write(num); break;
		case "4" : new CmtController().editCmt(); break;
		case "5" : new CmtController().deleteCmt(); break;
		case "6" : likePost(num); break;
		}
		
	}//postView
	
	// 게시글 수정
	public void editPost(String num) {

		System.out.println("\n----- 게시글 수정 -----");
		
		PostVo post = new PostService().showPostDetail(num);
		String no = post.getWriter();
		post.setWriter(MemberMain.LoginMember.getNo());
		
		if(!no.equals(MemberMain.LoginMember.getNo())) {
			System.out.println("[수정 불가] 본인의 글을 선택해 주세요.");
			return;
		}
		
		System.out.println("현재 제목 : " + post.getTitle());
		System.out.println("현재 내용 : " + post.getContent());
		
		// 데이터 받기
		System.out.print("수정할 글의 제목 : ");
		String title = InputUtil.sc.nextLine();
		System.out.print("수정할 글의 내용 : ");
		String content = InputUtil.sc.nextLine();
				
		// 데이터 뭉치기
		post.setTitle(title);
		post.setContent(content);
		
		
		int result = new PostService().editPost(post);
		
		// insert 결과에 따라 로직 처리
		if(result == 1) {
			// 글 수정 성공
			System.out.println("게시글 수정 성공!\n\n");
		}else {
			//글 수정 실패
			System.out.println("게시글 수정 실패...");
		}
		
	}// editPost
	
	// 게시글 삭제
	public void deletePost(String num) {
		
		System.out.println("\n----- 게시글 삭제 -----");
		
		PostVo post = new PostService().showPostDetail(num);
		String no = post.getWriter();
		post.setWriter(MemberMain.LoginMember.getNo());
		
		if(!no.equals(MemberMain.LoginMember.getNo())) {
			System.out.println("[삭제 불가] 본인의 글을 선택해 주세요.");
			return;
		}
		
		String result = Integer.toString(new PostService().deletePost(num)) ;
		

		// insert 결과에 따라 로직 처리
		if(result.equals("1")) {
			// 글 삭제 성공
			System.out.println("게시글 삭제 성공!\n\n");
		}else {
			//글 삭제 실패
			System.out.println("게시글 삭제 실패...");
		}
		
	}//deletePost
	
	// 좋아요
	public void likePost(String num) {
		
		String result = Integer.toString(new PostService().likePost(num));
		
		// insert 결과에 따라 로직 처리
		if(result.equals("1")) {
			// 글 삭제 성공
			System.out.println("좋아요 누르기 성공!\n\n");
		}else {
			//글 삭제 실패
			System.out.println("좋아요 누르기 실패...");
		}
		
	}//likePost
	
	public void showMyPost(MemberVo loginMember) {
		
		String nick = loginMember.getNick();
	
		List<PostVo> myPostList = new PostService().showMyPost();
	
		System.out.println("----- " + nick +"님의 게시글 조회 -----");
		
		for(int i = 0; i < myPostList.size(); i++) {
			
			PostVo temp = myPostList.get(i);
			
			String no = temp.getNo();
			String title = temp.getTitle();
			String writer = temp.getNick();
			String like = temp.getLike();
			String viewcount = temp.getViewCount();
			Timestamp date = temp.getDate();
			
			// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자
			System.out.println("[" + no + "] " + title + " |   " + writer + "  |  " +"좋아요 : "+ like + "  |  " + "조회수 : " + viewcount + "  |  " + date + "\n");
			}
			
		String num = new MenuPost().showPostDetail();

		// 글 번호 받으면
		PostVo vo = new PostService().showMyPostDetail(num);
		
		// SQL 실행 결과(게시글 객체) 화면에 보여주기
		System.out.println("\n----- " + nick +"님의 게시글 상세 조회 -----");
		System.out.print("제목 : " + vo.getTitle() + " | ");
		System.out.print("작성자 : " + vo.getNick() + " | ");
		System.out.print("작성일 : " + vo.getDate());
		System.out.println();	// 줄바꿈
		System.out.println("내용 : " + vo.getContent()+"\n");
	
		List<CmtVo> cmtVoList = new CmtService().showList(num);
		
		if(cmtVoList.size() > 0) {
			System.out.println("----- 댓글 -----");
			for(int i = 0; i < cmtVoList.size(); i++) {
						
				CmtVo temp = (CmtVo)cmtVoList.get(i);

				String cmtNo = temp.getCmtNo();
				String cmt = temp.getCmt();
				Timestamp date = temp.getDate();
				String writer = temp.getNick();
				
				// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자
				System.out.println("[" + cmtNo + "] " + cmt + " |   " + writer + "  |  " + date + "\n");
			}
		}
		
		String x = new MenuPost().showComment();

		switch(x) {
		case "1" : editPost(num); break;// 게시글 수정
		case "2": deletePost(num); break;
		case "3": new CmtController().write(num); break;
		case "4" : new CmtController().editCmt(); break;
		case "5" : new CmtController().deleteCmt(); break;
		case "6" : likePost(num); break;
		}
		
		
	}//showMyPost
	
	
}// class

/*
 * 1. 게시글 작성
 * 2. 게시글 수정
 * 3. 게시글 삭제
 * 4. 댓글 작성
 * 5. 댓글 수정
 * 6. 댓글 삭제
 */