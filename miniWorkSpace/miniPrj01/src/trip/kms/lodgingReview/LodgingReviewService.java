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
		
		if(MemberMain.LoginMember == null) {
			System.out.println("로그인을 먼저 진행해주세요.");
			return lodgingReviewVoList; //다음 내용 진행 막기(빈껍대기)
		}
		
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
	
	public int editReview(LodgingReviewVo lodgingReviewVoEdit) {
		Connection conn = null;
		int result = 0;
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
	
	public void deleteReview(LodgingReviewVo lodgingReviewVoDelete) {
		Connection conn = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			new LodgingReviewDao().deleteReview(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
	}

}
