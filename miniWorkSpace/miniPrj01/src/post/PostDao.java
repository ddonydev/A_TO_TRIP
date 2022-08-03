package post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static common.JDBCTemplate.*;

public class PostDao {
 
	public int write(PostVo vo, Connection conn) throws Exception {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			// 데이터 삽입 SQL
			String sql = "INSERT INTO TRAVEL_COMM (NO, WRITER, TITLE, CONT) VALUES (SEQ_C_POST_NO.NEXTVAL, ?, ?, ?)";
			
			// SQL 객체에 담기(물음표 완성)
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			result = pstmt.executeUpdate();
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public List<PostVo> showList(Connection conn) throws Exception {		// 커넥션 가져오기
		
		String sql = "SELECT T.NO, T.WRITER, T.TITLE, T.CONT, T.C_DATE, T.DELETE_YN, T.C_LIKE, T.EDIT_DATE, T.VIEW_COUNT, M.NICK FROM TRAVEL_COMM T JOIN MEMBER M ON T.WRITER = M.NO ORDER BY C_DATE DESC";
		
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
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				int writer = rs.getInt("writer");
				int like = rs.getInt("C_LIKE");
				int viewcount = rs.getInt("VIEW_COUNT");
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
				
				postVoList.add(vo);
				
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return postVoList;
		
	}
	
	public PostVo showPostDetail(Connection conn, int num) throws Exception {
		
		String sql = "SELECT T.NO, T.WRITER, T.TITLE, T.CONT, T.C_DATE, T.DELETE_YN, T.C_LIKE, T.EDIT_DATE, T.VIEW_COUNT, M.NICK FROM TRAVEL_COMM T JOIN MEMBER M ON T.WRITER = M.NO WHERE T.NO = ? ORDER BY C_DATE DESC ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostVo vo = null;
		
		try {
			// SQL 객체에 담기 및 쿼리 완성하기
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			// ResultSEt -> 자바 객체 
			if(rs.next()) {
				int no = rs.getInt("NO");
				int writer = rs.getInt("WRITER");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONT");
				Timestamp date = rs.getTimestamp("C_DATE");
				String deleteYN = rs.getString("DELETE_YN");
				int like = rs.getInt("C_LIKE");
				Timestamp editDate = rs.getTimestamp("EDIT_DATE");
				int viewcount = rs.getInt("VIEW_COUNT");
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
	
	
	
}// class
