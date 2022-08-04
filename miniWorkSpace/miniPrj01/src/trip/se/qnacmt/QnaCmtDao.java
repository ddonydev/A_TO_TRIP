package trip.se.qnacmt;

import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QnaCmtDao {

	// 댓글 작성
	public int write(QnaCmtVo vo, Connection conn) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			// 데이터 삽입 SQL
			String sql = "INSERT INTO QNA_CMT (CMT_NO, Q_POST_NO, WRITER, CMT) VALUES(SEQ_C_CMT_NO.NEXTVAL, ?, ?, ?)";
			
			// SQL 객체에 담기(물음표 완성)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getQnaNo());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getCmt());
			
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}//write
	
	// 댓글 조회
	public List<QnaCmtVo> showList(String postNo, Connection conn) throws Exception {		// 커넥션 가져오기
		String sql = "SELECT C.CMT_NO, C.Q_POST_NO, C.WRITER, C.CMT, C.CMT_DATE, M.NICK FROM QNA_CMT C JOIN MEMBER M ON C.WRITER = M.NO WHERE DELETE_YN = 'N' AND Q_POST_NO = ? ORDER BY CMT_DATE DESC";
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<QnaCmtVo> qcmtVoList = new ArrayList<QnaCmtVo>();

		try {
			// SQL 담을 객체 준비 및 SQL 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postNo);
			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();	
			// 커서 내려서, 칼럼별로 데이터 읽어오기, 객체로 만들기
			while(rs.next()) {
				String cmtNo = rs.getString("CMT_NO");
				String qnaNo = rs.getString("Q_POST_NO");
				String writer = rs.getString("WRITER");
				String cmt = rs.getString("CMT");
				Timestamp date = rs.getTimestamp("CMT_DATE");
				String nick = rs.getString("NICK");
			
				// 번호,게시글 번호 작성자, 작성일자
				QnaCmtVo vo = new QnaCmtVo();
				vo.setCmtNo(cmtNo);
				vo.setQnaNo(qnaNo);
				vo.setWriter(writer);
				vo.setCmt(cmt);
				vo.setDate(date);
				vo.setNick(nick);
				
				qcmtVoList.add(vo);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return qcmtVoList;
		
	}// showList
	
	// 댓글 조회
	public QnaCmtVo showCmtDetail(Connection conn, int num) throws Exception {
		
		String sql = "SELECT C.CMT_NO, C.WRITER, C.CMT, C.CMT_DATE, C.DELETE_YN, C.EDIT_DATE, M.NICK FROM QNA_CMT C JOIN MEMBER M ON C.WRITER = M.NO WHERE C.CMT_NO = ? ORDER BY CMT_DATE DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaCmtVo vo = null;
		
		try {
			// SQL 객체에 담기 및 쿼리 완성하기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			// ResultSEt -> 자바 객체 
			if(rs.next()) {
				String cmtNo = rs.getString("CMT_NO");
				String writer = rs.getString("WRITER");
				String cmt = rs.getString("CMT");
				Timestamp date = rs.getTimestamp("CMT_DATE");
				String deleteYN = rs.getString("DELETE_YN");
				Timestamp editDate = rs.getTimestamp("EDIT_DATE");
				String nick = rs.getString("NICK");
				
				vo = new QnaCmtVo();
				vo.setCmtNo(cmtNo);
				vo.setWriter(writer);
				vo.setCmt(cmt);
				vo.setDate(editDate);
				vo.setEditdate(editDate);
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
	}//showCmtDetail
	
	// 댓글 수정
	public int editCmt(QnaCmtVo vo, Connection conn) throws Exception {
		
		// SQL 준비
		String sql = "UPDATE QNA_CMT SET CMT = ?, EDIT_DATE = SYSDATE WHERE CMT_NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCmt());
			pstmt.setString(2, vo.getCmtNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
		
	}// editCmt
	
	// 댓글 삭제
	public int deleteCmt(int cmtNo, Connection conn) throws Exception {
		
		String sql = "UPDATE QNA_CMT SET DELETE_YN = 'Y' WHERE CMT_NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmtNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}//deleteCmt
}
