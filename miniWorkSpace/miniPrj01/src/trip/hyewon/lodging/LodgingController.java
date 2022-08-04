package trip.hyewon.lodging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import trip.daeun.lodging.information.InformationController;
import trip.daeun.lodging.menu.DaeunMenu;
import trip.min.main.MemberMain;
import trip.min.util.InputUtil;


public class LodgingController {

	//숙소 검색
	public void searchLodging() {
		
		while(true) {
			
			
			String location = null;
			while(true) {
				System.out.println("\n----- 숙소 검색 -----");
				System.out.println("지역을 선택해주세요(나가기:Q)");
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
			
			if(location.equals("Q")) {
				break;
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
			
			
			while(true) {
				
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
					System.out.println("숙소를 선택해주세요(나가기:Q)");
					System.out.print("입력 : ");
					input = InputUtil.sc.nextLine();
					for(int i=0; i<noList.size(); i++) {
						String no = (String) noList.get(i);
						if(input.equals(no) || input.equals("Q")) {
							isNum = true;
							break;
						}
					}
				}
				
				if(input.equals("Q")) {
					break;
				}
				
				new InformationController().showInformation(Integer.parseInt(input));
				DaeunMenu menu = new DaeunMenu();	
				//menu.showMenu();
				
				int input2 = menu.showDetailMenu();
				
				switch(input2) {
				case 1:
					/*예약하기*/
					System.out.println("예약 페이지로 넘어갑니다.");
					reserveRoom(Integer.parseInt(input), vo);
					break;
					
				case 2:
					/*찜하기*/
					//new WishController().wish();
					break;
					
				case 3:
					/*찜하기 취소*/
					break;
					
				case 4:
					/*숙소 리뷰 보기*/
					System.out.println("숙소 리뷰 페이지로 넘어갑니다.");
					break;
					
				case 5:
					/*숙소 리스트로 돌아가기*/
					System.out.println("숙소 리스트로 돌아갑니다.");
					break;
					
					
				}
			}
		}
		
		
		
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
	
	//숙소 예약하기
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
		
		//사용자가 쿠폰이 있는지 없는지 체크하고 쿠폰이 있다면 쿠폰 보여주고 쿠폰번호 입력받기
		
		LodgingCouponVo cv = useCoupon();
		
		//예약테이블, 결제테이블에 insert 하기 위한 객체 생성
		LodgingReservationVo rv = new LodgingReservationVo();
		
		rv.setCouponYn("N");
		rv.setCouponIssuedNo("0"); //발급쿠폰번호 디폴트값 NULL로 바꾸고싶으면 바꾸면됨.
		
		int discount = 0;
		if(cv != null) {
			String couponNo = Integer.toString(cv.getCouponInfoNo());
			rv.setCouponIssuedNo(Integer.toString(cv.getCouponIssuedNo()));
			//쿠폰번호에 따른 할인가격
			discount = lodgingDiscount(couponNo);
			rv.setCouponYn("Y");
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
			System.out.println("총 가격 : " + ((userPrice*diffDays)*(-1)+35000-discount));
			rv.setPayment(Long.toString(((userPrice*diffDays)*(-1)+35000-discount)));
			rv.setBreakfastYn("Y");
		}else {
			System.out.println("총 가격 : " + ((userPrice*diffDays)*(-1)-discount));
			rv.setPayment(Long.toString(((userPrice*diffDays)*(-1)-discount)));
			rv.setBreakfastYn("N");
		}
		if(payMoment.equals("1")) {
			System.out.println("결제수단 : " + pay + "(결제완료)");
			rv.setPayWay(pay);
			rv.setPayYn("Y");
		}else{
			System.out.println("\n결제는 " +vo.getStartDate() +"에 만나서 결제해주세요!");
			rv.setPayYn("N");
		}
		
		
		//쿠폰 사용 시 쿠폰이슈 테이블에 사용여부'Y'로, 사용일시 SYSDATE로 업데이트하기
		if(discount > 0) {
			updateCouponIssued(cv.getCouponIssuedNo());
		}
		
		//최종 예약 정보 객체에 세팅하기	
		rv.setMemberNo(MemberMain.LoginMember.getNo());
		rv.setLodgingNo(Integer.toString(lodgingNo));
		rv.setRoomNo(roomNo);
		rv.setPeople(vo.getPeople());
		rv.setStartDate(vo.getStartDate());
		rv.setEndDate(vo.getEndDate());
		
		//예약완료 시 예약테이블에 INSERT하기
		insertReservation(rv);
		
		//선불 시 예약테이블에서 예약번호 select해서 값 받아오기
		int reservationNo = getReservationNo(rv);
		
		if(reservationNo != 0) {
			rv.setNo(Integer.toString(reservationNo));
			//선불 시 결제테이블에 위에서 받아온 예약번호로 INSERT하기
			insertPayment(rv);
		}
		

	}
	
	
	
	
	
	
	public List<RoomVo> showRoom(LodgingReservationVo vo) {
		List<RoomVo> roomVoList = new LodgingService().showRoom(vo);
		return roomVoList;
	}
	
	//숙소 선택사항 보여주기(숙소이름, 방유형, 인원수, 입실날짜, 퇴실날짜, 조식여부, 총가격)
	//RoomVo 객체로 리턴하기
	public RoomVo showChoice(LodgingReservationVo vo) {
		RoomVo roomVo = new LodgingService().showChoice(vo);
		return roomVo;
	}
	
	//회원이 쿠폰이 있는지 없는지 확인하는 메소드
	public List<LodgingCouponVo> checkCoupon() {
		int no = Integer.parseInt(MemberMain.LoginMember.getNo());		
		List<LodgingCouponVo> lodgingCouponVoList = new LodgingService().checkCoupon(no);
		return lodgingCouponVoList;
		
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
	
	//회원이 쿠폰이 있는경우에 쿠폰 목록 보여주고 쿠폰 번호 리턴하기
	public LodgingCouponVo useCoupon() {
		List<LodgingCouponVo> lodgingCouponVoList = checkCoupon();
		//쿠폰 없으면 리턴
		if(lodgingCouponVoList.size() == 0) {
			return null;
		}
		
		//쿠폰 있으면 목록 보여주기
		List couponInfoNoList = new ArrayList();
		List couponIssuedNoList = new ArrayList();
		System.out.println("\n----- 내 쿠폰 목록 -----");
		for(int i=0; i<lodgingCouponVoList.size(); i++) {
			LodgingCouponVo vo = lodgingCouponVoList.get(i);
			
			String couponInfoNo = Integer.toString(vo.getCouponInfoNo());
			String couponIssuedNo = Integer.toString(vo.getCouponIssuedNo());
			String couponCode = vo.getCouponCode();
			String discount = vo.getDiscount();
			
			System.out.println(couponInfoNo + " | " + couponCode + " | " + discount);
			
			couponInfoNoList.add(couponInfoNo);
			couponIssuedNoList.add(couponIssuedNo);
		}
		
		boolean isNum = false;
		String input = "";
		int index = 0;
		while(!isNum) {
			System.out.println("쿠폰사용은 숫자를 입력해주세요(건너뛰려면 Y입력)");
			input = InputUtil.sc.nextLine();
			for(int i=0; i<couponInfoNoList.size(); i++) {
				String no = (String) couponInfoNoList.get(i);
				if(input.equals(no) || input.equals("Y")) {
					isNum = true;
					index = i;
					break;
				}
			}
		}
		
		LodgingCouponVo vo2 = new LodgingCouponVo();
		
		if(!input.equals("Y")) {
			vo2.setCouponInfoNo(Integer.parseInt(input));
			vo2.setCouponIssuedNo(Integer.parseInt((String)couponIssuedNoList.get(index)));
		}else if(input.equals("Y")) {
			return null;
		}
		
		return vo2;
	}
	
	//쿠폰정보번호로 할인가격 받아오기
	public int lodgingDiscount(String couponNo) {
		int discount = 0;
		switch(couponNo) {
		case "1" :
			discount = 5000;
			break;
		case "2" :
			discount = 50000;
			break;
		case "3" :
			discount = 100000;
			break;	
		}
		return discount;
	}
	
	//쿠폰 사용 시 쿠폰이슈 테이블에 사용여부'Y'로, 사용일시 SYSDATE로 업데이트하기
	public void updateCouponIssued(int couponIssuedNo) {
		int result = new LodgingService().updateCouponIssued(couponIssuedNo);
		if(result == 1) {
			System.out.println("쿠폰이 성공적으로 적용되었습니다.");
		}else {
			System.out.println("쿠폰 적용 시 에러발생!");
		}
	}
	
	
	
	
	//예약완료 시 예약테이블에 INSERT하기
	public void insertReservation(LodgingReservationVo rv) {
		int result = new LodgingService().insertReservation(rv);
		if(result == 1) {
			System.out.println("예약이 완료되었습니다.");
		}else {
			System.out.println("예약 시 에러발생!");
		}
	}
	
	//결제테이블에 insert하기위해서 특정 예약번호 select하기
	public int getReservationNo(LodgingReservationVo rv) {
		int result = new LodgingService().getReservationNo(rv);
		return result;
	}
	
	//선불 시 결제테이블에 INSERT하기
	public void insertPayment(LodgingReservationVo rv) {
		int result = new LodgingService().insertPayment(rv);
		if(result == 1) {
			System.out.println("결제가 완료되었습니다.");
		}else {
			System.out.println("결제 시 에러발생!");
		}
	}

}
