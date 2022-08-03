package trip.min.member;

import java.sql.Connection;

import trip.min.common.JDBCTemplate;

public class MemberService {

	public int join(MemberVo vo) {
		//아이디 4글자 이상
		if(vo.getId().length() < 4) {
			//다음단계 진행하면 안됨. 실패라고 알려줘야함
			return -1;
		}
		
		//패스워드 4글자 이상
		if(vo.getPwd().length() < 4) {
			//이것도 실패
			return -2;
		}
		
		//패스워드 중복확인
		if(!vo.getPwd().equals(vo.getPwd2())) {
			return -3;
		}
		
		//아이디 중복확인
		if(vo.getId().equals(vo.getId2())) {
			return -4;
		}
		//위의 조건들 모두 통과하면? -> insert
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = JDBCTemplate.getConnection();
			result = new MemberDao().join(vo, conn);
			
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
	
	
}//class
