package trip.dk.event;

import trip.min.main.MemberMenu;

public class EventGameController {
	public int rpsResult =0;
	public int upDownResult =0;

	public void eventGame() {
		// 참여 할 게임 물어보기.
		// 출력문 입력받기.
		
		String gameNo = new MemberMenu().eventGame();
		
		
		
		
		if(gameNo.equals("1")) {
			// 가위바위보 게임
			System.out.println("가위바위보게임을 5번 진행 후, 승리한 횟수에 따라 쿠폰을 차등 지급합니다!");
			System.out.println(" == 3회 이상 : 10만원 할인 쿠폰 코드 증정! == ");
			System.out.println(" == 2회 이상 :  5만원 할인 쿠폰 코드 증정! == ");
			System.out.println(" == 1회 이상 :  5천원 할인 쿠폰 코드 증정! == ");

		rpsResult = new EventGame().rpsGame();

		}
		if(gameNo.equals("2")) {
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
