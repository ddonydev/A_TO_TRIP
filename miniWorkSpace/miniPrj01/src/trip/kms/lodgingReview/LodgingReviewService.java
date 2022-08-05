package trip.kms.lodgingReview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import trip.min.common.JDBCTemplate;
import trip.min.main.MemberMain;

public class LodgingReviewService {

	public List<LodgingReviewVo> showList() {
		Connection conn = null;
		List<LodgingReviewVo> lodgingReviewVoList = null;
		
//		if(MemberMain.LoginMember == null) {
//			System.out.println("로그인을 먼저 진행해주세요.");
//			return lodgingReviewVoList; //다음 내용 진행 막기(빈껍대기)
//		}
		
		try {
			//connect db
			conn = JDBCTemplate.getConnection();
			
			lodgingReviewVoList = new LodgingReviewDao().showList(conn);
			JDBCTemplate.commit(conn);
			
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return lodgingReviewVoList;
	}
	
	public int writeReview(LodgingReviewVo vo) {
		if(vo.getTitle().length() < 1) 
			return -1;
		if(vo.getContent().length() < 1) 
			return -2;
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingReviewDao().writeReview(conn, vo);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollBack(conn);
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int editReview(LodgingReviewVo lodgingReviewVoEdit) {
		Connection conn = null;
		int result = 0;
		int memberWriter = Integer.valueOf(MemberMain.LoginMember.getNo());
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingReviewDao().editReview(conn, lodgingReviewVoEdit);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollBack(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
	}
	
	public int deleteReview(LodgingReviewVo lodgingReviewVoDelete) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingReviewDao().deleteReview(conn, lodgingReviewVoDelete);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
	
		return result;
	}

	public int reviewLikePlus(LodgingReviewVo vo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingReviewDao().reviewLikePlus(conn, vo);
			
			if(result == 1) {
//				vo.setReviewLike(vo.getReviewLike() + 1);
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollBack(conn);
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}

	public int reviewLikeMinus(LodgingReviewVo vo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new LodgingReviewDao().reviewLikeMinus(conn, vo);
			
			if(result == 1) {
//				vo.setReviewLike(vo.getReviewLike() - 1);
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollBack(conn);
			}
			
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public List<LodgingReviewVo> showMyList() {
		Connection conn = null;
		List<LodgingReviewVo> lodgingReviewVoList = null;
		
		try {
			//connect db
			conn = JDBCTemplate.getConnection();
			
			lodgingReviewVoList = new LodgingReviewDao().showMyList(conn);
			JDBCTemplate.commit(conn);
			
		} catch (Exception e) {
			JDBCTemplate.rollBack(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return lodgingReviewVoList;
	}
	
}
