package trip.hyewon.lodging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trip.hyewon.util.InputUtil;

public class LodgingController {

	//숙소 검색
	public void searchLodging() {
		System.out.println("\n----- 숙소 검색 -----");
	
		String location = null;
		while(true) {
			System.out.println("지역을 선택해주세요");
			System.out.println("1. 수도권");
			System.out.println("2. 강원도");
			System.out.println("3. 경상도");
			System.out.println("4. 충청도");
			System.out.println("5. 전라도");
			System.out.println("6. 제주도");
			
			System.out.print("입력 : ");
			location = InputUtil.sc.nextLine();
			
			if(checkLocationNum(location)) {
				break;
			}else {
				System.out.println("\n잘못된 입력입니다.\n");
			}
		}
		
		String startDate = null;
		while(true) {
			System.out.println("\n입실날짜를 입력해주세요(yy/mm/dd)");
			System.out.print("입력 : ");
			startDate = InputUtil.sc.nextLine();
			
			if(checkDate(startDate)) {
				break;
			}else {
				System.out.println("\n잘못된 입력입니다.\n");
			}
		}
		
		String endDate = null;
		while(true) {
			System.out.println("\n퇴실날짜를 입력해주세요(yy/mm/dd)");
			System.out.print("입력 : ");
			endDate = InputUtil.sc.nextLine();
			
			if(checkDate(endDate) && checkEndDate(startDate, endDate)) {
				break;
			}else {
				System.out.println("\n잘못된 입력입니다.\n");
			}
		}
		
		String headCount = null;
		while(true) {
			try {
				System.out.println("\n인원 수를 입력해주세요(최대 6명)");
				System.out.print("입력 : ");
				headCount = InputUtil.sc.nextLine();
				if(checkMaxPeople(headCount)) {
					break;
				}else {
					System.out.println("\n잘못된 입력입니다.\n");
				}
				
			}catch(Exception e) {
				System.out.println("\n숫자만 입력하세요!\n");
			}
			
		}
		//숙소불러오는코드인데 이 객체로 작업하는게 편해서 이걸로씀 예약아님~ 헷갈리지말자~!
		LodgingReservationVo vo = new LodgingReservationVo();
		
		vo.setLocationCode(location);
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		vo.setPeople(headCount);
		
		List<LodgingVo> lodgingVoList = new LodgingService().showLodgingList(vo);
		System.out.println("\n----- 숙소 목록 -----");
		List noList = new ArrayList();
		for(int i=0; i<lodgingVoList.size(); i++) {
			LodgingVo temp = lodgingVoList.get(i);
			
			String no = temp.getNo();
			String name = temp.getName();
			String address = temp.getAddress();
			
			System.out.println(no + " | " + name + " | " + address);
			
			noList.add(no);
		}
		
		boolean isNum = false;
		String input = "";
		while(!isNum) {
			System.out.print("숙소 선택(숫자) : ");
			input = InputUtil.sc.nextLine();
			for(int i=0; i<noList.size(); i++) {
				String no = (String) noList.get(i);
				if(input.equals(no)) {
					isNum = true;
					break;
				}
			}
		}
		
		reserveRoom(Integer.parseInt(input), vo);
		
	}
	
	public boolean checkLocationNum(String location) {
		return new LodgingService().checkLocationNum(location);
	}
	
	public boolean checkDate(String date) {
		return new LodgingService().checkDate(date);
	}
	
	public boolean checkEndDate(String startDate, String endDate) {
		return new LodgingService().checkEndDate(startDate, endDate);
	}
	
	public boolean checkMaxPeople(String headCount) {
		return new LodgingService().checkMaxPeople(headCount);
	}
	
	//추천 숙소 조회
	public void showRecommendLodging() {
		List<LodgingVo> lodgingVoList = new LodgingService().showRecommendLodgingList();
		System.out.println("\n----- 추천 숙소 목록 -----");
		for(int i=0; i<lodgingVoList.size(); i++) {
			LodgingVo temp = lodgingVoList.get(i);
			
			String no = temp.getNo();
			String name = temp.getName();
			String address = temp.getAddress();
			
			System.out.println(no + " | " + name + " | " + address);
		}
	}

	//인기 숙소 조회
	public void showPopularLodging() {
		List<LodgingVo> lodgingVoList = new LodgingService().showPopularodgingList();
		System.out.println("\n----- 인기 숙소 목록 -----");
		for(int i=0; i<lodgingVoList.size(); i++) {
			LodgingVo temp = lodgingVoList.get(i);
			
			String no = temp.getNo();
			String name = temp.getName();
			String address = temp.getAddress();
			
			System.out.println(no + " | " + name + " | " + address);
		}
	}
	
	//특정 숙소 들어간 후 예약하는 메소드로 일단 만들었음 나중에 수정예정
	public void reserveRoom(int lodgingNo, LodgingReservationVo vo) {
		System.out.println("\n----- 숙소 예약 -----");
		//방 보여주기(예약된 방 빼고)
		
		vo.setLodgingNo(Integer.toString(lodgingNo));

		List<RoomVo> roomVoList = showRoom(vo);
		List roomNoList = new ArrayList();
		
		String breakfastYn = "";
		for(int i=0; i<roomVoList.size(); ++i) {
			
			RoomVo temp = roomVoList.get(i);
			
			int no = temp.getNo();
			String roomType = temp.getRoomType();
			int people = temp.getMaxPeople();
			int price = temp.getPrice();
			breakfastYn = temp.getBreakfastYn();
			
			roomNoList.add(no);
			
			System.out.println(no + " | " + roomType + " | " + "1박가격:"+price + " | " + "조식여부:"+breakfastYn + " | " + "최대인원수:"+people);
			
		}
		
		boolean isRoomNo = false;
		int flag = 0;
		String roomNo = "";
		while(!isRoomNo) {
			System.out.print("방 선택(숫자) : ");
			roomNo = InputUtil.sc.nextLine();
			for(int i=0; i<roomNoList.size(); i++) {
				int no = (int) roomNoList.get(i);
				if(roomNo.equals(Integer.toString(no))) {
					isRoomNo = true;
					flag = i;
					break;
				}
			}
		}
		
		int userPrice = roomVoList.get(flag).getPrice();
		String userRoomType = roomVoList.get(flag).getRoomType();
		
		String choiceBreakfastYn = "N";
		if(breakfastYn.equals("Y")) {
			System.out.println("\n조식을 선택해주세요(Y/N) [35000원]");
			System.out.print("입력 : ");
			choiceBreakfastYn = InputUtil.sc.nextLine();
		}
		
		
		
		//숙소 선택사항 보여주기 가격까지
		RoomVo roomVo = showChoice(vo);
		System.out.println("\n----- 숙소 선택사항 -----");
		System.out.println("입실날짜 : " + vo.getStartDate());
		System.out.println("퇴실날짜 : " + vo.getEndDate());
		System.out.println("이름 : " + roomVo.getLodgingName());
		System.out.println("주소 : " + roomVo.getAddress());
		System.out.println("전화번호 : " + roomVo.getPhone());
		System.out.println("방유형 :" + userRoomType);
		System.out.println("인원수 : " + vo.getPeople());
		System.out.println("조식선택 : " + choiceBreakfastYn);
		System.out.println("1박기준가격 : " + userPrice);
		
		//숙박 일수 구하고 총 가격 구하기
		String date1 = vo.getStartDate();
		String date2 = vo.getEndDate();
		long diffDays = 0;
		try {
			Date format1 = new SimpleDateFormat("yyyy/MM/dd").parse(date1);
			Date format2 = new SimpleDateFormat("yyyy/MM/dd").parse(date2);
			long diffSec = (format1.getTime() - format2.getTime()) / 1000; //초 차이
			long diffMin = (format1.getTime() - format2.getTime()) / 60000; //분 차이
			long diffHor = (format1.getTime() - format2.getTime()) / 3600000; //시 차이
			diffDays = diffSec / (24*60*60); //일자수 차이
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		if(choiceBreakfastYn.equals("Y")) {
			System.out.println("총 가격 : " + ((userPrice*diffDays)*(-1)+35000));
		}else {
			System.out.println("총 가격 : " + (userPrice*diffDays)*(-1));
		}
		
		System.out.println("\n선불, 후불을 선택해주세요.");
		System.out.println("1. 선불");
		System.out.println("2. 후불");
		System.out.println("3. 나가기");
		System.out.print("입력 : ");
		String payMoment = InputUtil.sc.nextLine();
		String pay = "";
		if(payMoment.equals("1")) {
			pay = selectPay();
		}
		if(payMoment.equals("3")) {
			return;
		}
		//쿠폰 물어보기
		
		//쿠폰 적용하기
		
		//예약 내역 보여주기
		System.out.println("\n----- 예약내역 -----");
		System.out.println("입실날짜 : " + vo.getStartDate());
		System.out.println("퇴실날짜 : " + vo.getEndDate());
		System.out.println("이름 : " + roomVo.getLodgingName());
		System.out.println("주소 : " + roomVo.getAddress());
		System.out.println("전화번호 : " + roomVo.getPhone());
		System.out.println("방유형 :" + userRoomType);
		System.out.println("인원수 : " + vo.getPeople());
		System.out.println("조식선택 : " + choiceBreakfastYn);
		System.out.println("1박기준가격 : " + userPrice);
		if(choiceBreakfastYn.equals("Y")) {
			System.out.println("총 가격 : " + ((userPrice*diffDays)*(-1)+35000));
		}else {
			System.out.println("총 가격 : " + (userPrice*diffDays)*(-1));
		}
		if(payMoment.equals("1")) {
			System.out.println("결제수단 : " + pay + "(결제완료)");
			
		}else {
			System.out.println("\n결제는 " +vo.getStartDate() +"에 만나서 결제해주세요!");
		}
		

	}
	
	public List<RoomVo> showRoom(LodgingReservationVo vo) {
		List<RoomVo> roomVoList = new LodgingService().showRoom(vo);
		return roomVoList;
	}
	//숙소 결제
	public void payLodging() {
		
		System.out.println("쿠폰을 사용하시겠습니까?(Y/N)");
		System.out.print("입력 : ");
		String couponYn = InputUtil.sc.nextLine();
		
		
		
		
	}
	//숙소 선택사항 보여주기(숙소이름, 방유형, 인원수, 입실날짜, 퇴실날짜, 조식여부, 총가격)
	//RoomVo 객체로 리턴하기
	public RoomVo showChoice(LodgingReservationVo vo) {
		RoomVo roomVo = new LodgingService().showChoice(vo);
		return roomVo;
	}
	
	public void useCoupon() {
		
	}
	
	public String selectPay() {
		System.out.println("\n----- 선불 결제수단 선택 ------");
		System.out.println("1. 카드");
		System.out.println("2. 카카오페이");
		System.out.println("3. 계좌이체");
		System.out.print("입력 : ");
		String pay = InputUtil.sc.nextLine();
		String result = "";
		switch(pay) {
		case "1" :
			result = "카드";
			break;
		
		case "2" :
			result = "카카오페이";
			break;
		
		case "3" :
			result = "계좌이체";
			break;
		
	}
		return result;
	}
	
	//예약 내역 보여주기
	public void showReserve() {
		
	}

}
