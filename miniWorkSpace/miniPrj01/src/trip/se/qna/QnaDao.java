package trip.se.qna;

import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import trip.se.post.PostVo;


public class QnaDao {

	// 게시글 작성
	public int qnaWrite(QnaVo vo, Connection conn) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			// 데이터 삽입 SQL
			String sql = "INSERT INTO QNA (NO, WRITER, TITLE, CONT) VALUES (SEQ_Q_POST_NO.NEXTVAL, ?, ?, ?)";
			
			// SQL 객체에 담기(물음표 완성)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}//write
	
	// 게시글 조회
	public List<QnaVo> qnaShowList(Connection conn) throws Exception {		// 커넥션 가져오기
		
		String sql = "SELECT Q.NO, Q.WRITER, Q.TITLE, Q.CONT, Q.Q_DATE, Q.DELETE_YN, Q.EDIT_DATE, M.NICK FROM QNA Q JOIN MEMBER M ON Q.WRITER = M.NO WHERE DELETE_YN = 'N' ORDER BY Q_DATE DESC";
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<QnaVo> qnaVoList = new ArrayList<QnaVo>();
		
		try {
			// SQL 담을 객체 준비 및 SQL 완성
			pstmt = conn.prepareStatement(sql);
			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();		
			
			// 커서 내려서, 칼럼별로 데이터 읽어오기, 객체로 만들기
			while(rs.next()) {
				String no = rs.getString("NO");
				String title = rs.getString("TITLE");
				String writer = rs.getString("writer");
				Timestamp date = rs.getTimestamp("Q_DATE");
				String nick = rs.getString("NICK");
			
				// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자

				QnaVo vo = new QnaVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setDate(date);
				vo.setNick(nick);
				
				qnaVoList.add(vo);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return qnaVoList;
		
	}// showList
	
	// 게시글 상세 조회
	public QnaVo showQnaDetail(Connection conn, String num) throws Exception {
		
		String sql = "SELECT Q.NO, Q.WRITER, Q.TITLE, Q.CONT, Q.Q_DATE, Q.DELETE_YN, Q.EDIT_DATE, M.NICK FROM QNA Q JOIN MEMBER M ON Q.WRITER = M.NO WHERE Q.NO = ? ORDER BY Q_DATE DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaVo vo = null;
		
		try {
			// SQL 객체에 담기 및 쿼리 완성하기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			// ResultSEt -> 자바 객체 
			if(rs.next()) {
				String no = rs.getString("NO");
				String writer = rs.getString("WRITER");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONT");
				Timestamp date = rs.getTimestamp("Q_DATE");
				String deleteYN = rs.getString("DELETE_YN");
				Timestamp editDate = rs.getTimestamp("EDIT_DATE");
				String nick = rs.getString("NICK");
				
				vo = new QnaVo();
				vo.setNo(no);
				vo.setWriter(writer);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setDate(editDate);
				vo.setDeleteYN(deleteYN);
				vo.setEditDate(editDate);
				vo.setNick(nick);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			close(pstmt);
			close(rs);
		}
		return vo;
	}
	
	// 게시글 수정
	public int editQna(QnaVo vo, Connection conn) throws Exception {
		
		// SQL 준비
		String sql = "UPDATE QNA SET TITLE = ?, CONT = ?, EDIT_DATE = SYSDATE WHERE NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
		
	}// editPost
	
	public int deleteQna(String postNo, Connection conn) throws Exception {
		
		String sql = "UPDATE QNA SET DELETE_YN = 'Y' WHERE NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}//deletePost
	
	
}
