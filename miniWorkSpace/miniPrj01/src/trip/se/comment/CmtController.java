package trip.se.comment;

import java.util.List;

import trip.se.post.PostController;
import trip.se.post.PostService;
import trip.se.post.PostVo;
import trip.min.main.MemberMain;
import trip.min.util.InputUtil;

public class CmtController {

	// 댓글 작성
	public void write(String num) {

		System.out.println("\n----- 댓글 작성 -----");
		
		// 데이터 받기
		System.out.print("내용(Q -> 이전 메뉴) : ");
		String cmt = InputUtil.sc.nextLine();
		
		if(cmt.equals("Q")) {
			new PostController().postView();
		}
		
		String memberNo = MemberMain.LoginMember.getNo();
		
		// 데이터 뭉치기
		CmtVo vo = new CmtVo();
		vo.setCmt(cmt);
		vo.setWriter(memberNo);
		vo.setPostNo(num);
		
		int result = new CmtService().write(vo);
		
		// insert 결과에 따라 로직 처리
		if(result == 1) {
			// 글 작성 성공
			System.out.println("댓글 작성 성공!\n\n");
		}else {
			//글 작성 실패
			System.out.println("댓글 작성 실패...");
		}
		
	}//write

	// 댓글 수정
	public void editCmt() {

		System.out.println("\n----- 댓글 수정 -----");
		
		System.out.print("수정할 댓글의 번호(Q -> 이전 메뉴)  : ");
		String cmtNo = InputUtil.sc.nextLine();
		
		if(cmtNo.equals("Q")) {
			new PostController().postView();
		}
		
		CmtVo cmt = new CmtService().showCmtDetail(cmtNo);
		String no = cmt.getWriter();
		
		
		cmt.setWriter(MemberMain.LoginMember.getNo());
		if(!no.equals(MemberMain.LoginMember.getNo())) {
			System.out.println("[수정 불가] 본인의 댓글을 선택해 주세요.");
			return;
		}
		System.out.println("현재 내용 : " + cmt.getCmt());
		
		System.out.print("수정할 댓글의 내용(Q -> 이전 메뉴) : ");
		String content = InputUtil.sc.nextLine();
		
		if(content.equals("Q")) {
			return;
		}
				
		// 데이터 뭉치기
		cmt.setCmt(content);
		
		
		int result = new CmtService().editCmt(cmt);
		
		// insert 결과에 따라 로직 처리
		if(result == 1) {
			// 글 수정 성공
			System.out.println("댓글 수정 성공!\n\n");
		}else {
			//글 수정 실패
			System.out.println("댓글 수정 실패...");
		}
		
	}// editCmt
	
	// 댓글 삭제
	public void deleteCmt() {
		
		System.out.println("\n----- 댓글 삭제 -----");
		
		System.out.print("삭제할 댓글의 번호(Q -> 이전 메뉴) : ");
		String cmtNo = InputUtil.sc.nextLine();
		
		if(cmtNo.equals("Q")) {
			new PostController().postView();
		}
		
		CmtVo cmt = new CmtService().showCmtDetail(cmtNo);
		String no = cmt.getWriter();
		
		cmt.setWriter(MemberMain.LoginMember.getNo());
		
		if(!no.equals(MemberMain.LoginMember.getNo())) {
			System.out.println("[삭제 불가] 본인의 댓글을 선택해 주세요.");
			return;
		}
		
		int result = new CmtService().deleteCmt(cmtNo);
		

		// insert 결과에 따라 로직 처리
		if(result == 1) {
			// 글 삭제 성공
			System.out.println("댓글 삭제 성공!\n\n");
		}else {
			//글 삭제 실패
			System.out.println("댓글 삭제 실패...");
		}
		
	}//deleteCmt
	
}//class


/*
 * 게시글 불러올때 -> 댓글도 같이 불러오기
 * 게시글 보여주고 댓글도 보여주기
 */