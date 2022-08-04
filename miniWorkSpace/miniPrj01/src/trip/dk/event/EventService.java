package trip.dk.event;



import java.sql.Connection;
import java.util.List;

import trip.min.main.MemberMain;
import static trip.min.common.JDBCTemplate.*;


public class EventService {

	//디비에 인설트(DAO)
	//이벤트 조회
	public List<EventVo> showList() {
		Connection conn = null;
		List<EventVo> eventVoList = null;
		try {
			conn = getConnection();
			eventVoList =new EventDao().showEventList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}


		return eventVoList;
	}


	// 이벤트 상세 조회
	public EventVo showDetailByNo(String num) {

		Connection conn = null;
		EventVo ev = null;
		try {
			conn = getConnection();

			ev = new EventDao().showDetailByNo(conn,num);

		}catch (Exception e) {
			System.out.println("[ERROR] 게시글 상세조회 중 예외 발생 !!!");
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return ev;

}


	// 선택한 게임 조회 및 결과 넣기
		public void eventGame(String num) {

		int rpsResult =0;
		int upDownResult =0;

		EventParticipateVo epv = new EventParticipateVo();
		EventVo ev = new EventVo();

		// 참여 할 게임 물어보기.
		// 출력문 입력받기.
		if(num.equals("1")) {
			epv.setMemberNo(MemberMain.LoginMember.getNo());
			epv.setEventNo("1");
			epv.setEventYn("Y");
			// 가위바위보 게임
			System.out.println("가위바위보게임을 5번 진행 후, 승리한 횟수에 따라 쿠폰을 차등 지급합니다!");
			System.out.println(" == 3회 이상 : 10만원 할인 쿠폰 코드 증정! == ");
			System.out.println(" == 2회 이상 :  5만원 할인 쿠폰 코드 증정! == ");
			System.out.println(" == 1회 이상 :  5천원 할인 쿠폰 코드 증정! == ");

		rpsResult = new EventGame().rpsGame();



		}

		if(num.equals("2")) {
			epv.setMemberNo(MemberMain.LoginMember.getNo());
			epv.setEventNo("2");
			epv.setEventYn("Y");
			// UP&DOWN 게임
			//게임 안내
			System.out.println("UP&DOWN 게임 진행 후, 맞춘 횟수가 적을 수록 쿠폰을 차등 지급합니다!");
			System.out.println(" == 3회 이하 : 10만원 할인 쿠폰 코드 증정! == ");
			System.out.println(" == 5회 이하 :  5만원 할인 쿠폰 코드 증정! == ");
			System.out.println(" == 7회 이하 :  5천원 할인 쿠폰 코드 증정! == ");

		upDownResult =new EventGame().upDownGame();


		}
		while(true) {
			if(rpsResult>=3) {
				System.out.println("10만원 할인 쿠폰 당첨! 마이페이지를 확인해주세요.");break;

			}if(rpsResult>=2) {
				System.out.println("5만원 할인 쿠폰 당첨! 마이페이지를 확인해주세요.");break;

			}if(rpsResult>=1) {
				System.out.println("5천원 할인 쿠폰 당첨! 마이페이지를 확인해주세요.");break;
			}if(upDownResult<=3) {
				System.out.println("10만원 할인 쿠폰 당첨! 마이페이지를 확인해주세요.");break;

			}if(upDownResult<=5) {
				System.out.println("5만원 할인 쿠폰 당첨! 마이페이지를 확인해주세요.");break;

			}if(upDownResult<=7) {
				System.out.println("5천원 할인 쿠폰 당첨! 마이페이지를 확인해주세요.");break;
			}if(upDownResult>=8||rpsResult==0) {
				System.out.println(" 아쉽지만 다른 이벤트에 도전하세요!");break;
				
			}
			
		
			
		}
		


		// 참여 확인
		Connection conn = null;
		int result = 0;
		try {

			conn = getConnection();
			result = new EventDao().participateCheck(conn, epv);


			if (result == 1) {
				commit(conn);
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
			//롤백 해야함.
			e.printStackTrace();
			rollback(conn);
		}finally {
			close(conn);
		}




		}

}
