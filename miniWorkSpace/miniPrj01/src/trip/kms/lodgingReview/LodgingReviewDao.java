package trip.kms.lodgingReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import trip.min.common.JDBCTemplate;

public class LodgingReviewDao {

	public List<LodgingReviewVo> showList(Connection conn) throws Exception {
		//connection, sql
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = " select R.no, R.writer, R.lodging_no, R.title, R.cont "
				+ " from lodging_review R "
				+ " join lodging_information I on R.lodging_no = I.no "
				+ " where R.review_delete = 'N' ";
		
		pstmt = conn.prepareStatement(query);
		rs = pstmt.executeQuery();
		List<LodgingReviewVo> lodgingReviewVoList = new ArrayList<LodgingReviewVo>();
		
		try {
			while(rs.next()) {
				//none default field
				int no = rs.getInt("no");
				int writer = rs.getInt("writer"); //int형으로 table에서 writer 가져오는게 맞나?
				int lodgingNo = rs.getInt("lodging_no");
				String title = rs.getString("title");
				String content = rs.getString("cont");
				
				//add list
				LodgingReviewVo lodgingReviewVo = new LodgingReviewVo();
				lodgingReviewVo.setNo(no);
				lodgingReviewVo.setWriter(writer);
				lodgingReviewVo.setLodgingNo(lodgingNo);
				lodgingReviewVo.setTitle(title);
				lodgingReviewVo.setContent(content);
				
				lodgingReviewVoList.add(lodgingReviewVo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		
		return lodgingReviewVoList;
	}
	
	public int editReview(Connection conn, LodgingReviewVo lodgingReviewVoEdit) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LodgingReviewVo lodgingReviewVo = lodgingReviewVoEdit;
		
		
		String query = " update lodging_review "
				+ " set "
				+ " title = ?, cont = ? "
				+ " where no = ? ";
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, lodgingReviewVo.getTitle());
		pstmt.setString(2, lodgingReviewVo.getContent());
		pstmt.setInt(3, lodgingReviewVo.getNo());
		
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollBack(conn);
		}
		
		return result;
		
	}
	
	public void deleteReview(Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LodgingReviewVo lodgingReviewVo = null;
		
		String query = "";
		pstmt = conn.prepareStatement(query);
		
		int result = pstmt.executeUpdate();
		
	}
	
	
}
