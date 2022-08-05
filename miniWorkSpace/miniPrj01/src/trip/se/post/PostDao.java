package trip.se.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static trip.min.common.JDBCTemplate.*;

public class PostDao {
 
	// 게시글 작성
	public int write(PostVo vo, Connection conn) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			// 데이터 삽입 SQL
			String sql = "INSERT INTO TRAVEL_COMM (NO, WRITER, TITLE, CONT) VALUES (SEQ_C_POST_NO.NEXTVAL, ?, ?, ?)";
			
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
	public List<PostVo> showList(Connection conn) throws Exception {		// 커넥션 가져오기
		
		String sql = "SELECT T.NO, T.WRITER, T.TITLE, T.CONT, T.C_DATE, T.DELETE_YN, T.C_LIKE, T.EDIT_DATE, T.VIEW_COUNT, M.NICK FROM TRAVEL_COMM T JOIN MEMBER M ON T.WRITER = M.NO WHERE DELETE_YN = 'N' ORDER BY C_DATE DESC";
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<PostVo> postVoList = new ArrayList<PostVo>();
		
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
				String like = rs.getString("C_LIKE");
				String viewcount = rs.getString("VIEW_COUNT");
				Timestamp date = rs.getTimestamp("C_DATE");
				String nick = rs.getString("NICK");
			
				// 번호, 제목, 작성자, 좋아요 개수, 조회수, 작성일자

				PostVo vo = new PostVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setLike(like);
				vo.setViewCount(viewcount);
				vo.setDate(date);
				vo.setNick(nick);
				
				postVoList.add(vo);
				
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return postVoList;
		
	}//showList
	
	// 게시글 상세 조회
	public PostVo showPostDetail(Connection conn, String num) throws Exception {
		
		String sql = "SELECT T.NO, T.WRITER, T.TITLE, T.CONT, T.C_DATE, T.DELETE_YN, T.C_LIKE, T.EDIT_DATE, T.VIEW_COUNT, M.NICK FROM TRAVEL_COMM T JOIN MEMBER M ON T.WRITER = M.NO WHERE T.NO = ? ORDER BY C_DATE DESC ";
		String sqlCnt = "UPDATE TRAVEL_COMM SET VIEW_COUNT = VIEW_COUNT + 1 WHERE NO = ?";
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCnt = null;
		ResultSet rs = null;
		PostVo vo = null;
		
		try {
			// SQL 객체에 담기 및 쿼리 완성하기
			pstmt = conn.prepareStatement(sql);
			pstmtCnt = conn.prepareStatement(sqlCnt);
			pstmt.setString(1, num);
			pstmtCnt.setString(1, num);
			
			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();
			pstmtCnt.executeUpdate();
			
			// ResultSEt -> 자바 객체 
			if(rs.next()) {
				String no = rs.getString("NO");
				String writer = rs.getString("WRITER");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONT");
				Timestamp date = rs.getTimestamp("C_DATE");
				String deleteYN = rs.getString("DELETE_YN");
				String like = rs.getString("C_LIKE");
				Timestamp editDate = rs.getTimestamp("EDIT_DATE");
				String viewcount = rs.getString("VIEW_COUNT");
				String nick = rs.getString("NICK");
				
				vo = new PostVo();
				vo.setNo(no);
				vo.setWriter(writer);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setDate(editDate);
				vo.setDeleteYN(deleteYN);
				vo.setLike(like);
				vo.setEditDate(editDate);
				vo.setViewCount(viewcount);
				vo.setNick(nick);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			close(pstmt);
			close(rs);
			close(pstmtCnt);
		}
		return vo;
	}//showPostDetail
	
	// 게시글 수정
	public int editPost(PostVo vo, Connection conn) throws Exception {
		
		// SQL 준비
		String sql = "UPDATE TRAVEL_COMM SET TITLE = ?, CONT = ?, EDIT_DATE = SYSDATE WHERE NO = ?";
		
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
	
	// 게시글 삭제
	public int deletePost(String postNo, Connection conn) throws Exception {
		
		String sql = "UPDATE TRAVEL_COMM SET DELETE_YN = 'Y' WHERE NO = ?";
		
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
	
	// 좋아요
	public int likePost(String num, Connection conn) throws Exception {
		
		String sql = "UPDATE TRAVEL_COMM SET C_LIKE = C_LIKE + 1 WHERE NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			result = pstmt.executeUpdate();
		
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}//likePost
	
	
}// class
