package post;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import common.JDBCTemplate.*;

public class PostService {

	public int write(PostVo vo) {
		
		if(vo.getTitle().length() < 1) {
			return -1;
		}
		
		if(vo.getContent().length() < 1) {
			return -2;
		}
		
		int result = 0;
		
		try {
			Connection conn = getConnection();
			result = new PostDao().write(vo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 게시판 조회
	public List<PostVo> showList() {
		
		Connection conn = null;
		List<PostVo> postVoList =null;
		
		try {
			conn = getConnection();
			postVoList = new PostDao().showList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return postVoList;
		
	}
	
	public PostVo showPostDetail(int num) {
		
		Connection conn = null;
		PostVo vo = null;
		
		try {
			conn = getConnection();
			vo = new PostDao().showPostDetail(conn, num);
			
		} catch (Exception e) {
			System.out.println("[ERROR!!!]");
			e.printStackTrace();
		}
		
		return vo;
		
	}
	
	
}
