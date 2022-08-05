package trip.se.qna;

import java.sql.Timestamp;
import java.util.List;

import trip.min.main.MemberMain;
import trip.min.util.InputUtil;
import trip.se.comment.CmtController;
import trip.se.comment.CmtService;
import trip.se.comment.CmtVo;
import trip.se.mainPost.MenuPost;
import trip.se.qnacmt.QnaCmtController;
import trip.se.qnacmt.QnaCmtService;
import trip.se.qnacmt.QnaCmtVo;

public class QnaController {
	
	// 문의글 작성
	public void qnaWrite() {
		
		System.out.println("\n----- 문의글 작성 -----");
		
		// 데이터 받기
		System.out.print("제목 : ");
		String title = InputUtil.sc.nextLine();
		System.out.print("내용 : ");
		String content = InputUtil.sc.nextLine();
		
		String memberNo = MemberMain.LoginMember.getNo();
		
		// 데이터 뭉치기
		QnaVo vo = new QnaVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(memberNo);
		
		int result = new QnaService().qnaWrite(vo);
		
		// insert 결과에 따라 로직 처리
		if(result == 1) {
			// 글 작성 성공
			System.out.println("문의글 작성 성공!\n\n");
		}else {
			//글 작성 실패
			System.out.println("문의글 작성 실패...");
		}
		
	}//qnaWrite
	
	// 문의글 조회
	public void qnaView() {
		
		List<QnaVo> qnaVoList = new QnaService().qnaShowList();
		
		
		System.out.println("----- 문의글 조회 -----");
		
		for(int i = 0; i < qnaVoList.size(); i++) {
			
			QnaVo temp = (QnaVo)qnaVoList.get(i);
			
			String no = temp.getNo();
			String title = temp.getTitle();
			String writer = temp.getNick();
			Timestamp date = temp.getDate();
			
			// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자
			System.out.println("[" + no + "] " + title + " |   " + writer + "  |  " + "  |  " + date + "\n");
		}
		
		// 출력문, 입력받기
		String num = new MenuPost().showQnaDetail();
		
		if(num.equals("0")) {
			System.out.println("\n 메인 메뉴로 돌아갑니다. \n");
			return;
		}

		// 글 번호 받으면
		QnaVo vo = new QnaService().showQnaDetail(num);
		String no = vo.getWriter();
		
		vo.setWriter(MemberMain.LoginMember.getNo());

		if(!no.equals(MemberMain.LoginMember.getNo())) {
			System.out.println("[확인 불가] 본인의 글을 선택해 주세요.");
			return;
		}

		// SQL 실행 결과(게시글 객체) 화면에 보여주기
		System.out.println("\n----- 문의글 상세조회 -----");
		System.out.print("제목 : " + vo.getTitle() + " | ");
		System.out.print("작성자 : " + vo.getNick() + " | ");
		System.out.print("작성일 : " + vo.getDate());
		System.out.println();	// 줄바꿈
		System.out.println("내용 : " + vo.getContent()+"\n");
		
		// 댓글 목록 조회
		List<QnaCmtVo> qcmtVoList = new QnaCmtService().showList(num);
		
		if(qcmtVoList.size() > 0) {
			System.out.println("----- 댓글 -----");
			for(int i = 0; i < qcmtVoList.size(); i++) {
						
				QnaCmtVo temp = (QnaCmtVo)qcmtVoList.get(i);

				String cmtNo = temp.getCmtNo();
				String cmt = temp.getCmt();
				Timestamp date = temp.getDate();
				String nick = temp.getNick();
				
				// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자
				System.out.println("[" + cmtNo + "] " + cmt + " |   " + nick + "  |  " + date + "\n");
			}
		}
		
		String x = new MenuPost().showQnaComment();
		
		switch(x) {
		case "1" : editQna(num); break;// 게시글 수정
		case "2": deleteQna(num); break;
		case "3": new QnaCmtController().write(num); break;
		case "4" : new QnaCmtController().editCmt(); break;
		case "5" : new QnaCmtController().deleteCmt(); break;
		}
		
	}//qnaView
	

	// 문의글 수정
	public void editQna(String num) {

		System.out.println("\n-----문의 글 수정 -----");
		
		QnaVo qna = new QnaService().showQnaDetail(num);
		
		System.out.println("현재 제목 : " + qna.getTitle());
		System.out.println("현재 내용 : " + qna.getContent());
		
		// 데이터 받기
		System.out.print("수정할 글의 제목 : ");
		String title = InputUtil.sc.nextLine();
		System.out.print("수정할 글의 내용 : ");
		String content = InputUtil.sc.nextLine();
				
		// 데이터 뭉치기
		qna.setTitle(title);
		qna.setContent(content);
		
		
		int result = new QnaService().editQna(qna);
		
		// insert 결과에 따라 로직 처리
		if(result == 1) {
			// 글 수정 성공
			System.out.println("게시글 수정 성공!\n\n");
		}else {
			//글 수정 실패
			System.out.println("게시글 수정 실패...");
		}
		
	}// editQna
	
	// 문의글 삭제
	public void deleteQna(String num) {
		
		System.out.println("\n----- 문의 글 삭제 -----");
		
		QnaVo post = new QnaService().showQnaDetail(num);
		
		String result = Integer.toString(new QnaService().deleteQna(num)) ;

		// insert 결과에 따라 로직 처리
		if(result.equals("1")) {
			// 글 삭제 성공
			System.out.println("게시글 삭제 성공!\n\n");
		}else {
			//글 삭제 실패
			System.out.println("게시글 삭제 실패...");
		}
		
	}//deleteQna
}
