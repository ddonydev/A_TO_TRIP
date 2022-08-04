package trip.kms.lodgingReview;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import trip.kms.lodgingReviewLike.LodgingReviewLikeController;
import trip.kms.lodgingReviewLike.LodgingReviewLikeVo;
import trip.min.main.MemberMain;
import trip.min.member.MemberVo;

public class LodgingReviewController {

	//review 보여주기
	public void showReview() {
		
		while(true) {
			List<LodgingReviewVo> lodgingReviewVoList = new LodgingReviewService().showList();
			System.out.println("====== 숙소 리뷰 =====");
			for(int i = 0 ; i < lodgingReviewVoList.size() ; i++) {
				LodgingReviewVo tmp = lodgingReviewVoList.get(i);
				
				int no = tmp.getNo(); 
				int writer = tmp.getWriter(); 
				int lodgingNo = tmp.getLodgingNo(); 
				String title = tmp.getTitle(); 
				String content = tmp.getContent(); 
				Timestamp reviewDate = tmp.getReviewDate(); 
				Timestamp editDate = tmp.getEditDate(); 
				int reviewLike = tmp.getReviewLike();
				
				System.out.print("no=" + no + " || ");
				System.out.print("writer=" + writer + " || ");
				System.out.print("lodgingNo=" + lodgingNo + " || ");
				System.out.print("title=" + title + " || ");
				System.out.print("content=" + content + " || ");
				System.out.print("reviewLike=" + reviewLike);
				System.out.println();
				
//				System.out.println(tmp.toString());
			}
			
			LodgingReviewVo vo = new LodgingReviewVo();
		
			System.out.println("==== 리뷰 번호 선택 ====");
			Scanner scanner = new Scanner(System.in);
			System.out.print("조회할 리뷰 번호  : ");
			String inputNumStr = scanner.nextLine();
			int reviewNum = Integer.valueOf(inputNumStr);
			
			if(reviewNum > lodgingReviewVoList.size()) {
				System.out.println("리뷰 범위를 초과하였습니다.");
			}
			for(int i = 0 ; i < lodgingReviewVoList.size() ; i++) {
				LodgingReviewVo tmp = lodgingReviewVoList.get(i);
				if(tmp.getNo() == reviewNum) {
					int no = tmp.getNo(); 
					int writer = tmp.getWriter(); 
					int lodgingNo = tmp.getLodgingNo(); 
					String title = tmp.getTitle(); 
					String content = tmp.getContent(); 
					Timestamp reviewDate = tmp.getReviewDate(); 
					Timestamp editDate = tmp.getEditDate(); 
					int reviewLike = tmp.getReviewLike();
					
					vo.setNo(no);
					vo.setWriter(writer);
					vo.setLodgingNo(lodgingNo);
					vo.setTitle(title);
					vo.setContent(content);
					vo.setReviewDate(reviewDate);
					vo.setEditDate(editDate);
					vo.setReviewLike(reviewLike);

					reviewChoose(vo);
					break;
				} 
			}
		}
		
	}
	
	public void reviewChoose(LodgingReviewVo vo) {
		//리뷰 번호 선택 후 입장 시
		System.out.println(vo.getNo() + "번 리뷰에 입장하셨습니다.");
		System.out.println("-----------------------");
		
		while(true) {
			System.out.println("1. 수정");
			System.out.println("2. 이전으로 돌아가기");
			System.out.println("-----------------------");
			System.out.print("번호를 입력하세요 : ");
			Scanner scanner = new Scanner(System.in);
			String reviewLikeNum = scanner.nextLine();
			
			if(reviewLikeNum.equals("1")) {
				System.out.println("수정페이지로 이동합니다.");
				showReviewChoose(vo);
				break;
			} else if(reviewLikeNum.equals("2")) {
				System.out.println("이전페이지로 돌아갑니다.");
				showReview();
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	//1. 수정 선택 시 입장
	public void showReviewChoose(LodgingReviewVo vo) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("-----------------------");
			System.out.println("1. 글 작성");
			System.out.println("2. 글 수정"); 
			System.out.println("3. 글 삭제");
			System.out.println("4. 좋아요 누르기");
			System.out.println("5. 좋아요 취소하기");
			
			System.out.print("번호를 입력하세요 : ");
			String reviewEditNumStr = scanner.nextLine();
			int reviewEditNum = Integer.valueOf(reviewEditNumStr);
			
			if(reviewEditNum == 1) {
				writeReview();
				break;
			} else if(reviewEditNum == 2) {
				editReview(vo);
				break;
			} else if(reviewEditNum == 3) {
				deleteReview(vo);
				break;
			} else if(reviewEditNum == 4) {
				reviewLikePlus(vo);
				break;
			} else if(reviewEditNum == 5) {
				reviewLikeMinus(vo);
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	//1->1. 게시글 작성 선택시
	public void writeReview() {
		
	}
	
	//1->2. 게시글 수정 선택시
	public void editReview(LodgingReviewVo vo) {
		System.out.println("===== 게시글 수정 =====");
		Scanner scanner = new Scanner(System.in);
		
		//membervo 받아와서 pwd 검사? => no 그냥 받아오기
		int memberWriter = Integer.valueOf(MemberMain.LoginMember.getNo());
		
		System.out.print("수정할 title : ");
		String title = scanner.nextLine();
		System.out.print("수정할 content : ");
		String content = scanner.nextLine();
		
		
		LodgingReviewVo lodgingReviewVoEdit = new LodgingReviewVo();
		lodgingReviewVoEdit.setNo(vo.getNo());
		lodgingReviewVoEdit.setTitle(title);
		lodgingReviewVoEdit.setContent(content);
		lodgingReviewVoEdit.setLodgingNo(vo.getLodgingNo());
		lodgingReviewVoEdit.setWriter(memberWriter);
		lodgingReviewVoEdit.setReviewLike(vo.getReviewLike());
		
		int result = new LodgingReviewService().editReview(lodgingReviewVoEdit);
		if(result == 1 /* && memberWriter == lodgingReviewVoDelete.getWriter() */) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
		
	}
	
	//1->3. 게시글 삭제 선택시
	public void deleteReview(LodgingReviewVo vo) {
		System.out.println("===== 게시글 삭제 =====");
		LodgingReviewVo lodgingReviewVoDelete = vo;
		int result = 0;

		//membervo 받아와서 pwd 검사? => no 그냥 받아오기
		int memberWriter = Integer.valueOf(MemberMain.LoginMember.getNo());
		
		result = new LodgingReviewService().deleteReview(lodgingReviewVoDelete);
//		if(memberWriter != lodgingReviewVoDelete.getWriter()) {
//			System.out.println("삭제 불가능");
//			return;
//		}
		
		if(result == 1/* && memberWriter == lodgingReviewVoDelete.getWriter()*/) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}
	
	//1->4. 좋아요 누르기
	public void reviewLikePlus(LodgingReviewVo vo) {
		//membervo 받아와서 pwd 검사? => no 그냥 받아오기
		int memberWriter = Integer.valueOf(MemberMain.LoginMember.getNo());
		int result = 0;
		
		result = new LodgingReviewService().reviewLikePlus(vo);
		
		if(result == 1/* && memberWriter == lodgingReviewVoDelete.getWriter() */) {
			System.out.println("좋아요 누르기 성공");
		} else {
			System.out.println("좋아요 누르기 실패");
		}
	}
	//1->5. 좋아요 취소하기
	public void reviewLikeMinus(LodgingReviewVo vo) {
		//membervo 받아와서 pwd 검사? => no 그냥 받아오기
		int memberWriter = Integer.valueOf(MemberMain.LoginMember.getNo());
		int result = 0;
		
		result = new LodgingReviewService().reviewLikeMinus(vo);
		
		if(result == 1/* && memberWriter == lodgingReviewVoDelete.getWriter() */) {
			System.out.println("좋아요 취소 성공");
		} else {
			System.out.println("좋아요 취소 실패");
		}
	}
}
