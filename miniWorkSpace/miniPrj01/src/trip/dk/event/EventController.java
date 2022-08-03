package trip.dk.event;

import java.util.List;



import trip.dk.menu.Menu;

public class EventController {
	

	public void showEventList() {
		List<EventVo> eventVoList = new EventService().showList();
		System.out.println( " ----- 현재 진행중인 이벤트 ----- ");
		for(int i = 0; i<eventVoList.size();++i) {
			
			EventVo ev = eventVoList.get(i);
			int no = ev.getNo();
			String startDate = ev.getStartDate();
			String endDate = ev.getEndDate();
			String title = ev.getTitle(no);
			
			System.out.println( no + title+ " | 이벤트 기간 : "+ startDate +" ~ "+ endDate );
			
			}
		
		int num = new Menu().showDetailByNo();
		if(num==0) {
			System.out.println("메인 메뉴로 돌아갑니다.");
			return;
		}
		
		EventVo ev = new EventService().showDetailByNo(num);
		System.out.print("::      게임을 시작 하겠습니다      ::\n         "+ev.getTitle(num));
		System.out.println();
		new EventService().eventGame(num);
		

		
	}//showEventList

	



}
