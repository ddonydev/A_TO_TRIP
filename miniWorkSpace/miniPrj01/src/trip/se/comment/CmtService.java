package trip.se.comment;

import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import trip.se.post.PostDao;
import trip.se.post.PostVo;



public class CmtService {
	
	// 댓글 작성
	public int write(CmtVo vo) {
		
		if(vo.getCmt().length() < 1) {
			return -1;
		}
		
		int result = 0;
		
		try {
			Connection conn = getConnection();
			result = new CmtDao().write(vo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}// write
	
	// 댓글 목록
	public List<CmtVo> showList(String postNo) {
		
		Connection conn = null;
		List<CmtVo> cmtVoList =null;
		
		try {
			conn = getConnection();
			cmtVoList = new CmtDao().showList(postNo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return cmtVoList;
		
	}// showList

	// 댓글 조회
	public CmtVo showCmtDetail(String num) {
		
		Connection conn = null;
		CmtVo vo = null;
		
		try { 
			conn = getConnection();
			vo = new CmtDao().showCmtDetail(conn, num);
			commit(conn);
			
		} catch (Exception e) {
			System.out.println("[ERROR!!!]");
			e.printStackTrace();
		}
		return vo;
		
	}// showCmtDetail
	
	// 댓글 수정
	public int editCmt(CmtVo vo) {
		
		if(vo.getCmt().length() < 1) {
			// 제목이 비어있음
			return -1;
		}
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new CmtDao().editCmt(vo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
			
		} catch (Exception e) {
			rollBack(conn);
		}finally {
			close(conn);
		}
		
		return result;
		
	}//editCmt
	
	// 댓글 삭제
	public int deleteCmt(String cmtNo) {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new CmtDao().deleteCmt(cmtNo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
			
		} catch (Exception e) {
			rollBack(conn);
		}finally {
			close(conn);
		}
		
		return result;
		
	}// deleteCmt

}// class
