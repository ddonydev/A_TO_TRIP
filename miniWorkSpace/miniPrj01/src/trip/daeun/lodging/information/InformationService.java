package trip.daeun.lodging.information;


import java.sql.Connection;
import java.util.List;

import trip.min.common.JDBCTemplate;



public class InformationService {
	
	/*
	 * 숙소 상세조회
	 * 
	 * 
	 * */
	
	public List<InformationVo> showDetailByNo(int num) {

		Connection conn = null;
		List<InformationVo> informationVoList = null;

		try {

			conn = JDBCTemplate.getConnection();
			informationVoList = new InformationDao().showDetailByNo(conn, num);

		} catch (Exception e) {
			System.out.println("[ERROR] 숙소 조회 중 예외 발생 !!!");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}

		return informationVoList;
	}


	
}
