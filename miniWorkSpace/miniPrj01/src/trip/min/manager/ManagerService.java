package trip.min.manager;

import java.sql.Connection;

import trip.hyewon.lodging.LodgingVo;
import trip.min.common.JDBCTemplate;
import trip.min.manager.ManagerDao;

public class ManagerService {

	
	public int joinLodging(LodgingVo vo) {
		
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = new ManagerDao().InsertLodging(vo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTemplate.rollBack(conn);
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
		
	}//join
	
	public int editLodging(LodgingVo vo) {
		
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = new ManagerDao().UpdateLodging(vo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTemplate.rollBack(conn);
		}finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
		
	}
	
	
}
