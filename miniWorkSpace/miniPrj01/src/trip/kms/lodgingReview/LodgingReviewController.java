package trip.kms.lodgingReview;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import trip.kms.lodgingReviewLike.LodgingReviewLikeController;
import trip.kms.lodgingReviewLike.LodgingReviewLikeVo;
import trip.min.member.MemberVo;

public class LodgingReviewController {

	//review 보여주기
	public void showReview() {
		List<LodgingReviewVo> lodgingReviewVoList = new LodgingReviewService().showList();
		
		System.out.println("====== 숙소 리뷰 =====");
		for(int i = 0 ; i < lodgingReviewVoList.size() ; i++) {
			//get tuple
			LodgingReviewVo tmp = lodgingReviewVoList.get(i);
			/*
			int no = tmp.getNo(); 
			int writer = tmp.getWriter(); 
			int lodgingNo = tmp.getLodgingNo(); 
			String title = tmp.getTitle(); 
			String content = tmp.getContent(); 
			Timestamp reviewDate = tmp.getReviewDate(); 
			Timestamp editDate = tmp.getEditDate(); 
			int reviewLike = tmp.getReviewLike();
			*/
			
			//print tuple
			System.out.println(tmp);
			
		}
		
		LodgingReviewVo vo = new LodgingReviewVo();
		while(true) {
			System.out.println("==== 리뷰 번호 선택 ====");
			Scanner scanner = new Scanner(System.in);
			System.out.print("조회할 리뷰 번호  :");
			String inputNumStr = scanner.nextLine();
			int reviewNum = Integer.valueOf(inputNumStr);
			
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
					new LodgingReviewLikeController().showReviewLike(vo);
					break;
				} else {
					System.out.println("존재하지 않는 리뷰 번호입니다.");
				}
			}
		}
		
	}
	
	//lodgingreviewlikecontroller에서 게시글 수정 선택시
	public void editReview(LodgingReviewVo vo) {
		System.out.println("===== 게시글 수정 =====");
		Scanner scanner = new Scanner(System.in);
		
		//membervo 받아와서 pwd 검사?
		
		System.out.print("수정할 title : ");
		String title = scanner.nextLine();
		System.out.print("수정할 content : ");
		String content = scanner.nextLine();
		
		LodgingReviewVo lodgingReviewVoEdit = new LodgingReviewVo();
		lodgingReviewVoEdit.setTitle(title);
		lodgingReviewVoEdit.setContent(content);
		lodgingReviewVoEdit.setLodgingNo(vo.getNo());
		lodgingReviewVoEdit.setWriter(vo.getWriter());
		
		int result = new LodgingReviewService().editReview(lodgingReviewVoEdit);
		if(result == 1) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
		
		
	}
	//lodgingreviewlikecontroller에서 게시글 삭제 선택시
	public void deleteReview() {
		System.out.println("===== 게시글 삭제 =====");
		LodgingReviewVo lodgingReviewVoDelete = new LodgingReviewVo();
//		MemberVo memberVo = new MemberVo();
		
		new LodgingReviewService().editReview(lodgingReviewVoDelete);
	}
}
