package trip.daeun.lodging.information;

import java.util.List;

import trip.daeun.lodging.menu.DaeunMenu;
import trip.hyewon.lodging.LodgingVo;

public class InformationController {

	/*
	 * 
	 * 숙소 리스트에서 입력 	받기
	 * 숙소이름, 방, 1박 가격, 조식 여부, 위치 조회
		1. 예약하기
		2. 찜하기 → 찜 완료되었습니다 출력 
			찜취소 → 찜 취소되었습니다 출력
		3. 숙소 리뷰보기 
		4. 목록으로 돌아가기
	 * */
	
	//방유형, 1박가격, 조식여부, 최대인원수 리스트로 변경
	
	public void showInformation(int num) {

		List<InformationVo> informationVoList = new InformationService().showDetailByNo(num);
		
		
		System.out.println();
		System.out.println();
		System.out.println(".         　。　　　　•　    　ﾟ　　      。");
		System.out.println(" 　'    .　　　.　　　  　　.　　　　　。　　");
		System.out.println(" •.        𝑳𝒐𝒅𝒈𝒊𝒏𝒈 𝑰𝒏𝒇𝒐𝒓𝒎𝒂𝒕𝒊𝒐𝒏        。　.      ");
		System.out.println("　 　　。　　　　　　ﾟ　　　.　　　　　    .");
		System.out.println(",　　　　.　 .　　     .               。");
		
		
		
		for(int i = 0; i < informationVoList.size(); ++i) {
			InformationVo temp = informationVoList.get(i);
		
			int no = temp.getNo();
			String name = temp.getName();
			String room = temp.getRoom();
			int price = temp.getPrice();
			String address = temp.getAddress();
			String breakfast = temp.getBreakfast();
			String maxPeople = temp.getMaxPeople();
			
			System.out.println();
			System.out.println("   ღ 𝑳𝒐𝒅𝒈𝒊𝒏𝒈 ღ  " + name);
			System.out.println("   ღ 𝑨𝒅𝒅𝒓𝒆𝒔𝒔 ღ  " + address);
			System.out.println("   ღ 𝑹𝒐𝒐𝒎 𝑻𝒚𝒑𝒆 ღ  " + room);
			System.out.println("   ღ 𝑷𝒓𝒊𝒄𝒆 ღ  " + price + "원");
			System.out.println("   ღ 𝑩𝒓𝒆𝒂𝒌𝒇𝒂𝒔𝒕 ღ  " + breakfast);
			System.out.println("   ღ 𝑴𝒂𝒙 𝑷𝒆𝒐𝒑𝒍𝒆 ღ  " + maxPeople + "명");
			System.out.println();
			System.out.println(".         　。　　　　•　       . 　ﾟ　　      。");
			System.out.println("　 　　。　　　　　　ﾟ　　　.　　　　　    .");
			
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
		System.out.println();
		System.out.println("ღ 예약하기를 입력하면 방 타입을 고르실 수 있습니다.");	
		System.out.println();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	//숙소 번호 받기
	//숙소 번호 받으면 ? -> 해당 숙소 정보 상세 조회
//	InformationVo vo = new InformationService().showDetailByNo(num);
//	
//	//실행 결과(게시글 객체) 화면에 보여주기


		//DB에서 숙소 불러오기
		//오늘 한 거 토대로 적용해보기
		
	}
	
	
	
	
}
