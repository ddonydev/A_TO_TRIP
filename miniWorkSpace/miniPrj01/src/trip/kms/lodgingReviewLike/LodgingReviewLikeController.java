package trip.kms.lodgingReviewLike;

import java.util.Scanner;

import trip.kms.lodgingReview.LodgingReviewController;
import trip.kms.lodgingReview.LodgingReviewVo;

public class LodgingReviewLikeController {

	//review 볼 숙소 선택 시 입장
	public void showReviewLike(LodgingReviewVo vo) {
		
		/*reviewNum 구현*/
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
				showEditReview(vo);
				break;
			} else if(reviewLikeNum.equals("2")) {
				System.out.println("이전페이지로 돌아갑니다.");
				new LodgingReviewController().showReview();
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	//1. 수정 선택 시 입장
	public void showEditReview(LodgingReviewVo vo) {
		Scanner scanner = new Scanner(System.in);
		LodgingReviewLikeVo lodgingReviewLikeVo = null;
		
		lodgingReviewLikeVo = new LodgingReviewLikeService().showEditReview(vo.getNo());
		
		while(true) {
			System.out.println("-----------------------");
			System.out.println("1. 글 수정"); 
			System.out.println("2. 글 삭제");
			System.out.println("3. 좋아요 누르기");
			System.out.println("4. 좋아요 취소하기");
			
			System.out.print("번호를 입력하세요 : ");
			String reviewEditNumStr = scanner.nextLine();
			int reviewEditNum = Integer.valueOf(reviewEditNumStr);
			
			if(reviewEditNum == 1) {
				//lodgingReviewController table에서 수정
				new LodgingReviewController().editReview(vo);
				break;
			} else if(reviewEditNum == 2) {
				//lodgingReviewController table에서 수정
				new LodgingReviewController().deleteReview();
				break;
			} else if(reviewEditNum == 3) {
				reviewLikePlus();
				break;
			} else if(reviewEditNum == 4) {
				reviewLikeMinus();
				break;
			} else {
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	//3. 좋아요 누르기
	public void reviewLikePlus() {
		
	}
	//4. 좋아요 취소하기
	public void reviewLikeMinus() {
		
	}
	
}
