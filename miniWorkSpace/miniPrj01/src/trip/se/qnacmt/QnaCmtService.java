package trip.se.qnacmt;

import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;


public class QnaCmtService {

	// 문의 댓글 작성
	public int write(QnaCmtVo vo) {
			
		if(vo.getCmt().length() < 1) {
			return -1;
		}
		
		int result = 0;
		
		try {
			Connection conn = getConnection();
			result = new QnaCmtDao().write(vo, conn);
			
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
	
	//문의 댓글 조회
	public List<QnaCmtVo> showList(String postNo) {
		
		Connection conn = null;
		List<QnaCmtVo> cmtVoList =null;
		
		try {
			conn = getConnection();
			cmtVoList = new QnaCmtDao().showList(postNo, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return cmtVoList;
		
	}// showList

	// 문의 댓글 조회
	public QnaCmtVo showCmtDetail(String num) {
		
		Connection conn = null;
		QnaCmtVo vo = null;
		
		try { 
			conn = getConnection();
			vo = new QnaCmtDao().showCmtDetail(conn, num);
			commit(conn);
			
		} catch (Exception e) {
			System.out.println("[ERROR!!!]");
			e.printStackTrace();
		}
		return vo;
		
	}// showCmtDetail
	
	// 댓글 수정
	public int editCmt(QnaCmtVo vo) {
		
		if(vo.getCmt().length() < 1) {
			// 제목이 비어있음
			return -1;
		}
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new QnaCmtDao().editCmt(vo, conn);
			
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
			result = new QnaCmtDao().deleteCmt(cmtNo, conn);
			
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
