package trip.kms.lodgingReviewLike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import trip.min.common.JDBCTemplate;

public class LodgingReviewLikeDao {

	public LodgingReviewLikeVo showEditReview(Connection conn, int reviewNum) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LodgingReviewLikeVo lodgingReviewLikeVo = null;
		
		try {
			String query = " select L.no, L.lodging_review_no, L.memeber_no "
					+ " from lodging_review_like L "
					+ " join lodging_review R on L.lodging_review_no = R.no "
					+ " join member M on L.memeber_no = M.no "
					+ " where L.lodging_review_no = " + reviewNum;
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("L.no");
				int lodging_no = rs.getInt("L.lodging_review_no");
				int member_no = rs.getInt("L.memeber_no");
				
				lodgingReviewLikeVo = new LodgingReviewLikeVo();
				lodgingReviewLikeVo.setNo(no);
				lodgingReviewLikeVo.setLodgingReviewNo(lodging_no);
				lodgingReviewLikeVo.setMemberNo(member_no);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		return lodgingReviewLikeVo;
	}
	
}
