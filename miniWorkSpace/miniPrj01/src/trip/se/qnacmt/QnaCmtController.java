package trip.se.qnacmt;

import trip.min.main.MemberMain;
import trip.min.util.InputUtil;
import trip.se.comment.CmtService;
import trip.se.comment.CmtVo;

public class QnaCmtController {

	// 댓글 작성
	public void write(String num) {

		System.out.println("\n----- 댓글 작성 -----");
		
		// 데이터 받기
		System.out.print("내용 : ");
		String cmt = InputUtil.sc.nextLine();
		
		String memberNo = MemberMain.LoginMember.getNo();
		
		// 데이터 뭉치기
		QnaCmtVo vo = new QnaCmtVo();
		vo.setCmt(cmt);
		vo.setWriter(memberNo);
		vo.setQnaNo(num);
		
		int result = new QnaCmtService().write(vo);
		
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
		
		System.out.print("수정할 댓글의 번호 : ");
		int cmtNo = InputUtil.getInt();
		QnaCmtVo cmt = new QnaCmtService().showCmtDetail(cmtNo);
		String no = cmt.getWriter();

		cmt.setWriter(MemberMain.LoginMember.getNo());
		if(!no.equals(MemberMain.LoginMember.getNo())) {
			System.out.println("[수정 불가] 본인의 글을 선택해 주세요.");
			return;
		}
		
		System.out.println("현재 내용 : " + cmt.getCmt());
		
		System.out.print("수정할 댓글의 내용 : ");
		String content = InputUtil.sc.nextLine();
				
		// 데이터 뭉치기
		cmt.setCmt(content);
		
		
		int result = new QnaCmtService().editCmt(cmt);
		
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
		
		System.out.print("삭제할 댓글의 번호 : ");
		int cmtNo = InputUtil.getInt();
		QnaCmtVo cmt = new QnaCmtService().showCmtDetail(cmtNo);
		String no = cmt.getWriter();

		cmt.setWriter(MemberMain.LoginMember.getNo());
		if(!no.equals(MemberMain.LoginMember.getNo())) {
			System.out.println("[삭제 불가] 본인의 글을 선택해 주세요.");
			return;
		}
	
		int result = new QnaCmtService().deleteCmt(cmtNo);
		

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
