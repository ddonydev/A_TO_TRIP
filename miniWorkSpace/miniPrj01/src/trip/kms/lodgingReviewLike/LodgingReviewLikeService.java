package trip.kms.lodgingReviewLike;

import java.sql.Connection;

import trip.min.common.JDBCTemplate;

public class LodgingReviewLikeService {

	public LodgingReviewLikeVo showEditReview(int reviewNum) {
		
		Connection conn = null;
		LodgingReviewLikeVo lodgingReviewLikeVo = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			lodgingReviewLikeVo = new LodgingReviewLikeDao().showEditReview(conn, reviewNum);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lodgingReviewLikeVo;
	}
}
