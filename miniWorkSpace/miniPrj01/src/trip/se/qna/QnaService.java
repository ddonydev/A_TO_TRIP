package trip.se.qna;

import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import trip.se.post.PostDao;
import trip.se.post.PostVo;


public class QnaService {
	
	// 문의글 작성
	public int qnaWrite(QnaVo vo) {
		
		if(vo.getTitle().length() < 1) {
			return -1;
		}
		
		if(vo.getContent().length() < 1) {
			return -2;
		}
		
		int result = 0;
		
		try {
			Connection conn = getConnection();
			result = new QnaDao().qnaWrite(vo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}//qnaWrite

	// 문의글 조회
	public List<QnaVo> qnaShowList() {
		
		Connection conn = null;
		List<QnaVo> qnaVoList =null;
		
		try {
			conn = getConnection();
			qnaVoList = new QnaDao().qnaShowList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return qnaVoList;
		
	}// qnaShowList
	
	// 문의글 상세조회
	public QnaVo showQnaDetail(String num) {
		
		Connection conn = null;
		QnaVo vo = null;
		
		try { 
			conn = getConnection();
			vo = new QnaDao().showQnaDetail(conn, num);
			commit(conn);
			
		} catch (Exception e) {
			System.out.println("[ERROR!!!]");
			e.printStackTrace();
		}
		return vo;
		
	}// showQnaDetail
	
	// 문의글 수정
	public int editQna(QnaVo vo) {
		
		if(vo.getTitle().length() < 1) {
			// 제목이 비어있음
			return -1;
		}
		
		if(vo.getContent().length() < 1){
			// 내용이 비어있음
			return -2;
		}
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new QnaDao().editQna(vo, conn);
			
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
		
	}//editQna
	
	//문의글 삭제
	public int deleteQna(String postNo) {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new QnaDao().deleteQna(postNo, conn);
			
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
		
	}// deleteQna
	
	// 내 문의글 조회
	public List<QnaVo> showMyQna() {
		Connection conn = null;
		List<QnaVo> showMyQna =null;
		
		try {
			conn = getConnection();
			showMyQna = new QnaDao().showMyQna(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			close(conn);
		}
		
		return showMyQna;
		
	}//showMyQna
	
	// 내 문의글 상세조회
	public QnaVo showMyQnaDetail(String num) {
		
		Connection conn = null;
		QnaVo vo = null;
		
		try {
			conn = getConnection();
			vo = new QnaDao().showMyQnaDetail(conn, num);
			commit(conn);
			
		} catch (Exception e) {
			System.out.println("[ERROR!!!]");
			e.printStackTrace();
		}
		return vo;
		
	}//showMyQnaDetail
	
}// class
