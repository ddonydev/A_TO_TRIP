package trip.kms.lodgingReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import trip.min.common.JDBCTemplate;
import trip.min.main.MemberMain;

public class LodgingReviewDao {

	public List<LodgingReviewVo> showList(Connection conn) throws Exception {
		//connection, sql
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LodgingReviewVo> lodgingReviewVoList = new ArrayList<LodgingReviewVo>();
		
		String query = " select R.no, R.writer, R.lodging_no, R.title, R.cont, R.review_like, R.review_date "
				+ " from lodging_review R "
				+ " join lodging_information I on R.lodging_no = I.no "
				+ " where R.review_delete = 'N' ";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//none default field
				int no = rs.getInt("no");
				int writer = rs.getInt("writer"); //int형으로 table에서 writer 가져오는게 맞나?
				int lodgingNo = rs.getInt("lodging_no");
				String title = rs.getString("title");
				String content = rs.getString("cont");
				int reviewLike = rs.getInt("review_like");
				Timestamp reviewDate = rs.getTimestamp("review_date");
				
				//add list
				LodgingReviewVo lodgingReviewVo = new LodgingReviewVo();
				lodgingReviewVo.setNo(no);
				lodgingReviewVo.setWriter(writer);
				lodgingReviewVo.setLodgingNo(lodgingNo);
				lodgingReviewVo.setTitle(title);
				lodgingReviewVo.setContent(content);
				lodgingReviewVo.setReviewLike(reviewLike);
				lodgingReviewVo.setReviewDate(reviewDate);
				
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
	
	public int writeReview(Connection conn, LodgingReviewVo vo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		LodgingReviewVo lodgingReviewVo = new LodgingReviewVo();
		
		String query = " insert into lodging_review(no, writer, lodging_no, title, cont) "
				+ " values(SEQ_REVIEW_NO.nextval, ?, ?, ?, ?) ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getWriter());
			pstmt.setInt(2, vo.getLodgingNo());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollBack(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int editReview(Connection conn, LodgingReviewVo lodgingReviewVoEdit) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LodgingReviewVo lodgingReviewVo = lodgingReviewVoEdit;
		int memberWriter = Integer.valueOf(MemberMain.LoginMember.getNo());
		
		
		String query = " update lodging_review "
				+ " set "
				+ " title = ?, cont = ? "
				+ " where no = ? ";
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, lodgingReviewVo.getTitle());
		pstmt.setString(2, lodgingReviewVo.getContent());
		pstmt.setInt(3, lodgingReviewVo.getNo());
		
		int result = pstmt.executeUpdate();
		
		if(result == 1 && memberWriter == lodgingReviewVo.getWriter()) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollBack(conn);
		}
		
		return result;
		
	}
	
	public int deleteReview(Connection conn, LodgingReviewVo lodgingReviewVoDelete) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LodgingReviewVo lodgingReviewVo = lodgingReviewVoDelete;
		int result = 0;
		int memberWriter = Integer.valueOf(MemberMain.LoginMember.getNo());
		
		String query = " update lodging_review "
						+ " set review_delete = 'Y' "
						+ " where no = ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, lodgingReviewVo.getNo());
			
			result = pstmt.executeUpdate();
			
			if(result == 1 && memberWriter == lodgingReviewVo.getWriter()) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollBack(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int reviewLikePlus(Connection conn, LodgingReviewVo vo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String noStr = Integer.toString(vo.getNo());
		
		String query = " update lodging_review "
						+ " set "
						+ " review_like = review_like + 1 " 
						+ " where no = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noStr);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int reviewLikeMinus(Connection conn, LodgingReviewVo vo) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String noStr = Integer.toString(vo.getNo());
		
		String query = " update lodging_review "
				+ " set "
				+ " review_like = review_like - 1 " 
				+ " where no = ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, noStr);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public List<LodgingReviewVo> showMyList(Connection conn) throws Exception {
		//connection, sql
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LodgingReviewVo> lodgingReviewVoList = new ArrayList<LodgingReviewVo>();
		
		String query = " select R.no, R.writer, R.lodging_no, R.title, R.cont, R.review_like, R.review_date "
				+ " from lodging_review R "
				+ " join lodging_information I on R.lodging_no = I.no "
				+ " where R.review_delete = 'N' and writer = " + MemberMain.LoginMember.getNo();
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//none default field
				int no = rs.getInt("no");
				int writer = rs.getInt("writer"); 
				int lodgingNo = rs.getInt("lodging_no");
				String title = rs.getString("title");
				String content = rs.getString("cont");
				int reviewLike = rs.getInt("review_like");
				Timestamp reviewDate = rs.getTimestamp("review_date");
				
				//add list
				LodgingReviewVo lodgingReviewVo = new LodgingReviewVo();
				lodgingReviewVo.setNo(no);
				lodgingReviewVo.setWriter(writer);
				lodgingReviewVo.setLodgingNo(lodgingNo);
				lodgingReviewVo.setTitle(title);
				lodgingReviewVo.setContent(content);
				lodgingReviewVo.setReviewLike(reviewLike);
				lodgingReviewVo.setReviewDate(reviewDate);
				
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
	
}
