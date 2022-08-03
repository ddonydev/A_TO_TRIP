package trip.kms.lodgingReview;

import java.sql.Timestamp;
import java.util.List;

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
		
	}
}
