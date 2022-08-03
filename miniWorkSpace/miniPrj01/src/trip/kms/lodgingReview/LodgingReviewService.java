package trip.kms.lodgingReview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class LodgingReviewService {

	public List<LodgingReviewVo> showList() {
		
		Connection conn = null;
		List<LodgingReviewVo> lodgingReviewVoList = null;
		
		try {
			//connect db
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String id = "C##ATT";
			String pwd = "ATT";
			
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("Connection : " + conn);
			conn.setAutoCommit(false);
			
			lodgingReviewVoList = new LodgingReviewDao().showList(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return lodgingReviewVoList;
	}

}
