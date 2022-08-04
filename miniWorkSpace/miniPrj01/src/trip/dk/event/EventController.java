package trip.dk.event;

import java.util.List;

import trip.min.main.MemberMain;
import trip.min.main.MemberMenu;
public class EventController {

//이벤트 조회, 이벤트 상세 조회 (게임 결과까지)
	String num = null;
	public void showEventList() {
		List<EventVo> eventVoList = new EventService().showList();
		System.out.println( " ----- 현재 진행중인 이벤트 ----- ");
		for (EventVo ev : eventVoList) {

			String no = ev.getNo();
			String startDate = ev.getStartDate();
			String endDate = ev.getEndDate();
			String title = ev.getTitle(no);



			System.out.println( no + title+ " | 이벤트 기간 : "+ startDate +" ~ "+ endDate );

			}

		num = new MemberMenu().showDetailByNo();
		EventParticipateVo epv = new EventParticipateVo();
		epv.setEventNo(num);

		if(num.equals("0")) {
			System.out.println("메인 메뉴로 돌아갑니다.");
			return;
		}
		EventVo ev = new EventService().showDetailByNo(num);
		
		String mNo = epv.getMemberNo(); 
		epv = new EventService().participateCheck(num);
		
		
		
		if(epv.getEventYn().equals("N")) {
			System.out.print("::      게임을 시작 하겠습니다      ::\n         "+ev.getTitle(num));
			System.out.println();
			new EventService().eventGame(num);
		}
		if(epv.getEventYn().equals("Y")) {
			
			System.out.println("이미 참여한 이벤트입니다!");
			System.out.println("처음 화면으로 돌아갑니다.");
			System.out.println();
		
			
			
		}
		
		

	}//showEventList





}
