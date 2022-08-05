package trip.daeun.lodging.wish;

import java.sql.Connection;
import java.util.List;

import trip.hyewon.lodging.LodgingVo;
import trip.min.common.JDBCTemplate;

public class WishService {

	public void wish(WishVo vo) {
		
		
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = new WishDao().wish(vo, conn);

			if (result == 1) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollBack(conn);
			}
		} catch (Exception e) {
			// 롤백해야 하는 상황
			JDBCTemplate.rollBack(conn);
		} finally {
			JDBCTemplate.close(conn);
		}

	}

	public boolean selectWish(LodgingVo vo) {

		Connection conn = null;
		boolean isWish = false;
		try {
			conn = JDBCTemplate.getConnection();
			isWish = new WishDao().selectWish(vo, conn);
		}catch(Exception e){
			
		}finally {
			JDBCTemplate.close(conn);
		}
		return isWish;
	}
	
	
	public List<LodgingVo> showZzimList() {
		
		Connection conn = null;
		List<LodgingVo> lodgingVoList = null;
		
		try {
			conn = JDBCTemplate.getConnection();
			lodgingVoList  = new WishDao().showZzimList(conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		
		
		return lodgingVoList;
	}
	
	
	

}