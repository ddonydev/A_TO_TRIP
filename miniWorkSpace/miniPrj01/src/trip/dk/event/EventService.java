package trip.dk.event;

public class EventService {
	package trip.dk.event;

	import java.sql.Connection;
	import java.util.List;

	import trip.dk.menu.Menu;

	import static trip.dk.common.JDBCTemplate.*;


	public class EventService {
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
		public EventVo showDetailByNo(int num) {
			
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
		
		
		// 선택한 게임에 따라 설명 및 게임 진행
			public void eventGame(int gameNo) {
			
			int rpsResult =0;
			int upDownResult =0;
			
			// 참여 할 게임 물어보기.
			// 출력문 입력받기.
			
			
			
			if(gameNo == 1) {
				// 가위바위보 게임 
				System.out.println("가위바위보게임을 5번 진행 후, 승리한 횟수에 따라 쿠폰을 차등 지급합니다!");
				System.out.println(" == 3회 이상 : 10만원 할인 쿠폰 코드 증정! == ");
				System.out.println(" == 2회 이상 :  5만원 할인 쿠폰 코드 증정! == ");
				System.out.println(" == 1회 이상 :  5천원 할인 쿠폰 코드 증정! == ");
				
			rpsResult = new EventGame().rpsGame();
				
			}
			
			if(gameNo == 2) {
				// UP&DOWN 게임
				//게임 안내
				System.out.println("UP&DOWN 게임 진행 후, 맞춘 횟수가 적을 수록 쿠폰을 차등 지급합니다!");
				System.out.println(" == 3회 이하 : 10만원 할인 쿠폰 코드 증정! == ");
				System.out.println(" == 5회 이하 :  5만원 할인 쿠폰 코드 증정! == ");
				System.out.println(" == 7회 이하 :  5천원 할인 쿠폰 코드 증정! == ");
				
			upDownResult =new EventGame().upDownGame();
			}
			
				}
			

	}


}
