package trip.hyewon.lodging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trip.daeun.lodging.information.InformationController;
import trip.daeun.lodging.wish.WishController;
import trip.kms.lodgingReview.LodgingReviewController;
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
				break;//잘됨
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
				
				List priceList = new ArrayList();
				
				Map priceMap = new HashMap();
				
				for(int i=0; i<lodgingVoList.size(); i++) {
					LodgingVo temp = lodgingVoList.get(i);
					
					String no = temp.getNo();
					String name = temp.getName();
					String address = temp.getAddress();
					int minPrice = getMinPrice(Integer.parseInt(no));
					
					System.out.println("숙소번호:"+no+" | 리뷰:" + getReviewCnt(Integer.parseInt(no)) + "개 | 찜:" + getZzimCnt(Integer.parseInt(no)) + "개" + " | " + name + " | " + address + " | 1박가격:" + minPrice +"원부터");
					
					noList.add(no);
					priceMap.put(minPrice, i);
					priceList.add(minPrice);
				}
				
				
				
				boolean isNum = false;
				boolean isSelect = false;
				String lodgingNoInput = "";
				while(!isNum) {
					System.out.println("숙소를 선택해주세요[나가기:Q | 낮은가격순:L | 높은가격순:H ]");
					System.out.print("입력 : ");
					lodgingNoInput = InputUtil.sc.nextLine();
					for(int i=0; i<noList.size(); i++) {
						String no = (String) noList.get(i);
						if(lodgingNoInput.equals(no) || lodgingNoInput.equals("Q") || lodgingNoInput.equals("L") || lodgingNoInput.equals("H")) {
							isNum = true;
							if(lodgingNoInput.equals(no) || lodgingNoInput.equals("Q")) {
								isSelect = true;
							}
							break;
						}
					}
				}
				
				if(lodgingNoInput.equals("Q")) {
					break;//잘됨
				}
				
				
				while(!isSelect) {
					switch(lodgingNoInput) {
					case "L" :
						priceList.sort(Comparator.naturalOrder());
						for(int i=0; i<priceList.size(); i++) {
							int price = (int)priceList.get(i);
							int index = (int)priceMap.get(price);
							LodgingVo temp = lodgingVoList.get(index);
							
							String no = temp.getNo();
							String name = temp.getName();
							String address = temp.getAddress();
							int minPrice = getMinPrice(Integer.parseInt(no));
							
							System.out.println("숙소번호:"+no+" | 리뷰:" + getReviewCnt(Integer.parseInt(no)) + "개 | 찜:" + getZzimCnt(Integer.parseInt(no)) + "개" + " | " + name + " | " + address + " | 1박가격:" + minPrice +"원부터");
							
							
						}
						
						isNum = false;
						lodgingNoInput = "";
						while(!isNum) {
							System.out.println("숙소를 선택해주세요[나가기:Q | 낮은가격순:L | 높은가격순:H ]");
							System.out.print("입력 : ");
							lodgingNoInput = InputUtil.sc.nextLine();
							for(int i=0; i<noList.size(); i++) {
								String no = (String) noList.get(i);
								if(lodgingNoInput.equals(no) || lodgingNoInput.equals("Q") || lodgingNoInput.equals("L")|| lodgingNoInput.equals("H")) {
									isNum = true;
									if(lodgingNoInput.equals(no) || lodgingNoInput.equals("Q")) {
										isSelect = true;
									}
									break;
								}
							}
						}
						break;
					case "H" :
						priceList.sort(Comparator.reverseOrder());
						for(int i=0; i<priceList.size(); i++) {
							int price = (int)priceList.get(i);
							int index = (int)priceMap.get(price);
							LodgingVo temp = lodgingVoList.get(index);
							
							String no = temp.getNo();
							String name = temp.getName();
							String address = temp.getAddress();
							int minPrice = getMinPrice(Integer.parseInt(no));
							
							System.out.println("숙소번호:"+no+" | 리뷰:" + getReviewCnt(Integer.parseInt(no)) + "개 | 찜:" + getZzimCnt(Integer.parseInt(no)) + "개" + " | " + name + " | " + address + " | 1박가격:" + minPrice +"원부터");
							
							
						}
						
						isNum = false;
						lodgingNoInput = "";
						while(!isNum) {
							System.out.println("숙소를 선택해주세요[나가기:Q | 낮은가격순:L | 높은가격순:H ]");
							System.out.print("입력 : ");
							lodgingNoInput = InputUtil.sc.nextLine();
							for(int i=0; i<noList.size(); i++) {
								String no = (String) noList.get(i);
								if(lodgingNoInput.equals(no) || lodgingNoInput.equals("Q") || lodgingNoInput.equals("L")|| lodgingNoInput.equals("H")) {
									isNum = true;
									if(lodgingNoInput.equals(no) || lodgingNoInput.equals("Q")) {
										isSelect = true;
									}
									break;
								}
							}
						}
						break;
					default :
						
						
						
					}
				}
				
				if(lodgingNoInput.equals("Q")) {
					break;//잘됨
				}
				
				new InformationController().showInformation(Integer.parseInt(lodgingNoInput));
				//showLodgingInformation(Integer.parseInt(lodgingNoInput));
				
				//menu.showMenu();
				
				boolean isWish = new DaeunMenu().showDetailMenu(MemberMain.LoginMember.getNo(), lodgingNoInput); //리턴 boolean값 찜했었으면 true, 찜안했으면 false
				
				//isWish가 false면 무조건 찜하기가 떠야되고
				//isWish가 true이면서 찜취소여부가 Y일때 찜하기가 떠야되고
				//isWish가 true이면서 찜취소여부가 N일때 찜하기 취소가 떠야된다.
				
				
				if(isWish == true) {
					//찜했던 경험은 있지만 찜취소를했는지 안했는지 판단 먼저 해야한다.
					String zzimCancel = checkZzimCancel(lodgingNoInput);
					
					//찜취소를 했었다면 찜하기할때 N으로 [업데이트] 해야한다.
					if(zzimCancel.equals("Y")) {
						System.out.println("1. 예약하기");
						System.out.println("2. 찜하기");
						System.out.println("3. 숙소리뷰보기");
						System.out.println("4. 숙소리스트로 돌아가기");
						System.out.print("입력 : ");
						String deinput1 = InputUtil.sc.nextLine();
						switch(deinput1) {
						case "1" :
							System.out.println("예약 페이지로 넘어갑니다.");
							reserveRoom(Integer.parseInt(lodgingNoInput), vo);	//숙소예약하기
							break;
						case "2" : //찜하기 업데이트메소드
							//new WishController().wishCancel();
							updateZzimCancelN(lodgingNoInput);
							break;
						case "3" : //리뷰보기
							new LodgingReviewController().showLodgingReview(Integer.parseInt(lodgingNoInput));
							break;
							
						}
						if(deinput1.equals("4")) {
							continue;//잘됨
						}
					}
					
					//찜하기만 했었다면 찜취소할때 Y로 [업데이트]
					if(zzimCancel.equals("N")) {
						System.out.println("1. 예약하기");
						System.out.println("2. 찜취소하기(이미 찜한 숙소입니다)");
						System.out.println("3. 숙소리뷰보기");
						System.out.println("4. 숙소리스트로 돌아가기");
						System.out.print("입력 : ");
						String deinput1 = InputUtil.sc.nextLine();
						
						switch(deinput1) {
						case "1" :
							System.out.println("예약 페이지로 넘어갑니다.");
							reserveRoom(Integer.parseInt(lodgingNoInput), vo);	//숙소예약하기
							break;
						case "2" : //찜취소 업데이트 메소드
							//new WishController().wishCancel();
							updateZzimCancelY(lodgingNoInput);
							break;
						case "3" : //리뷰보기
							new LodgingReviewController().showLodgingReview(Integer.parseInt(lodgingNoInput));
							break;
							
						}
						if(deinput1.equals("4")) {
							continue;//잘됨
						}
					}

				}
				
				//회원번호 숙소번호로 찜테이블에서 조회가 안된거면 찜하기 [INSERT]부터 해야한다.
				if(isWish == false) {
					System.out.println("1. 예약하기");
					System.out.println("2. 찜하기");
					System.out.println("3. 숙소리뷰보기");
					System.out.println("4. 숙소리스트로 돌아가기");
					System.out.print("입력 : ");
					String deinput2 = InputUtil.sc.nextLine();
					
					switch(deinput2) {
					case "1" :
						System.out.println("예약 페이지로 넘어갑니다.");
						reserveRoom(Integer.parseInt(lodgingNoInput), vo);	//숙소예약하기
						break;
					case "2" : //찜하기 insert 메소드
						//new WishController().wish();
						zzimInsert(lodgingNoInput);
						break;
					case "3" : //리뷰보기
						new LodgingReviewController().showLodgingReview(Integer.parseInt(lodgingNoInput));
						break;
					}
					if(deinput2.equals("4")) {
						continue;//잘됨
					}
					
				}			
				
			}//숙소리스트 while문
		}
		
		
		
	}//숙소검색 메소드 끝
	
	
	
	public String checkZzimCancel(String lodgingNo) {
		String zzimCancel = new LodgingService().checkZzimCancel(lodgingNo);
		return zzimCancel;
	}
	
	public void zzimInsert(String lodgingNo) {
		int result = new LodgingService().zzimInsert(lodgingNo);
		if(result == 1) {
			System.out.println("찜하기가 완료되었습니다.");
		}else {
			System.out.println("찜하기 INSERT 중 에러발생!");
		}
	}
	
	public void updateZzimCancelY(String lodgingNo) {
		int result = new LodgingService().updateZzimCancelY(lodgingNo);
		if(result == 1) {
			System.out.println("찜취소가 완료되었습니다.");
		}else {
			System.out.println("찜취소 업데이트 중 에러발생!");
		}
	}
	
	public void updateZzimCancelN(String lodgingNo) {
		int result = new LodgingService().updateZzimCancelN(lodgingNo);
		if(result == 1) {
			System.out.println("찜하기가 완료되었습니다.");
		}else {
			System.out.println("찜하기 업데이트 중 에러발생!");
		}
	}
	
	//지역선택 맞는값인지 확인
	public boolean checkLocationNum(String location) {
		return new LodgingService().checkLocationNum(location);
	}
	
	//날짜가 올바른 값인지 확인
	public boolean checkDate(String date) {
		return new LodgingService().checkDate(date);
	}
	
	//퇴실날짜가 올바른 값인지 확인
	public boolean checkEndDate(String startDate, String endDate) {
		return new LodgingService().checkEndDate(startDate, endDate);
	}
	
	//인원수가 올바른 값인지 확인
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
			int reviewCnt = temp.getReviewCnt();
			
			System.out.println(no + " | 리뷰:" + reviewCnt + "개 | " + name + " | " + address);
		}
		System.out.println();
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
			int zzimCnt = temp.getZzimCnt();
			
			System.out.println(no + " | 찜 " + zzimCnt + "개 | " + name + " | " + address);
		}
		System.out.println();
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
			
			System.out.println("방번호: " + no + " | " + roomType + " | " + "1박가격:"+price + " | " + "조식여부:"+breakfastYn + " | " + "최대인원수:"+people + " | 1개남음");
			
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
		
		rv.setCouponYn("N"); //최종 쿠폰사용여부 디폴트N
		rv.setCouponIssuedNo("0"); //최종 발급쿠폰번호 디폴트0
		
		int discount = 0;
		if(cv != null) {
			String couponNo = Integer.toString(cv.getCouponInfoNo());
			rv.setCouponIssuedNo(Integer.toString(cv.getCouponIssuedNo()));
			//쿠폰번호에 따른 할인가격
			discount = lodgingDiscount(couponNo);
			rv.setCouponYn("Y"); //최종 쿠폰사용여부
		}

		//총가격이 0원 이하면 선불, 후불 선택안하고 pay=쿠폰사용 넣기
		//바로 예약 내역 보여주기
		long totalPrice = 0;
		
		if(choiceBreakfastYn.equals("Y")) {
			totalPrice = (userPrice*diffDays)*(-1)+35000-discount;
			
			if(totalPrice<0) {
				totalPrice = 0;
			}
			rv.setPayment(Long.toString(totalPrice));
			rv.setBreakfastYn("Y"); //최종 조식선택여부
		}else {
			totalPrice = (userPrice*diffDays)*(-1)-discount;
			
			if(totalPrice<0) {
				totalPrice = 0;
			}
			rv.setPayment(Long.toString(totalPrice));
			rv.setBreakfastYn("N"); //최종 조식선택여부
		}
		
		String pay = "";
		String payMoment = "";
		if(totalPrice>0) {
			System.out.println("\n선불, 후불을 선택해주세요.");
			System.out.println("1. 선불");
			System.out.println("2. 후불");
			System.out.println("3. 나가기");
			System.out.print("입력 : ");
			payMoment = InputUtil.sc.nextLine();
			if(payMoment.equals("1")) {
				pay = selectPay();
			}
			if(payMoment.equals("3")) {
				return;
			}
			
		}else {
			pay = "쿠폰사용";
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
		System.out.println("총 가격 : " + totalPrice);
		

		if(payMoment.equals("1")&&(totalPrice>0)) {
			System.out.println("결제수단 : " + pay + "(결제완료)");
			rv.setPayWay(pay);
			rv.setPayYn("Y"); //최종 결제여부
		}else if(payMoment.equals("2")&&(totalPrice>0)){
			System.out.println("\n결제는 " +vo.getStartDate() +"에 만나서 결제해주세요!");
			rv.setPayYn("N"); //최종 결제여부
		}else {
			System.out.println("결제수단 : 쿠폰사용");
			rv.setPayYn("Y");
			rv.setPayWay("쿠폰사용");
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
		

	}//예약끝
	
	
	
	//마이페이지에서 예약내역 보여주기(여기서 num은 회원번호임!)
	public void showMyReservation(String num) {
		System.out.println("\n----- 나의 예약 내역 -----\n");
		
		List<LodgingReservationVo> voList = new LodgingService().showMyReserVation(num);
		List noList = new ArrayList();
		List cancelYnList = new ArrayList();
		List breakfastYnList = new ArrayList();
		for(int i=0; i<voList.size(); i++) {
			LodgingReservationVo vo = voList.get(i);
			String no = vo.getNo();
			String reserveDate = vo.getReserveDate();
			String lodgingName = vo.getLodgingName();
			String address = vo.getAddress();
			String phone = vo.getLodgingPhone();
			String startDate = vo.getStartDate();
			String endDate = vo.getEndDate();
			String people = vo.getPeople();
			String breakfastYn = vo.getBreakfastYn();
			String payment = vo.getPayment();
			String payYn = vo.getPayYn();
			String cancelYn = vo.getCancelYn();
			String roomType = vo.getRoomType();
			
			noList.add(no);
			cancelYnList.add(cancelYn);
			breakfastYnList.add(breakfastYn);
			
			System.out.println("[예약번호 : " + no + " ]");
			System.out.println("예약날짜 : " + reserveDate);
			System.out.println("숙소이름 : " + lodgingName);
			System.out.println("숙소주소 : " + address);
			System.out.println("숙소전화번호 : " + phone);
			System.out.println("입실날짜 : " + startDate.substring(0,10));
			System.out.println("퇴실날짜 : " + endDate.substring(0,10));
			System.out.println("방유형 : " + roomType);
			System.out.println("인원수 : " + people + "명");
			System.out.println("조식여부 : " + breakfastYn);
			System.out.println("총금액 : " + payment + "원");
			System.out.println("결제여부 : " + payYn);
			System.out.println("예약취소여부 " + cancelYn);
			System.out.println();
		}
		
		String input = "";
		while(!input.equals("1")&&!input.equals("2")&&!input.equals("3")) {
			System.out.println("1. 예약 취소하기");
			System.out.println("2. 예약 변경하기");
			System.out.println("3. 리뷰 작성하기");
			System.out.println("4. 나가기");
			System.out.print("입력 : ");
			input = InputUtil.sc.nextLine();
			
			switch(input) {
			case "1" : //예약취소
				String cancelNo = cancelReservation(noList, cancelYnList);
				updateCancelY(cancelNo);
				break;
			case "2" : //예약변경
				String updateNo = updateReservation(noList, cancelYnList);
				updateMenu(noList, updateNo, breakfastYnList);
				break;
			case "3" : //리뷰작성
				String reviewNo = reviewReservation(noList, cancelYnList); //예약번호
				int lodgingNo = selectLodgingNo(reviewNo); //예약번호로 숙소번호 셀렉트해옴
				new LodgingReviewController().writeReview(lodgingNo);
				break;
			case "4" :
				return;
			default:
				System.out.println("잘못된 입력입니다!");
			}
		}
		
		
	}//예약내역 끝
	
	//예약 번호 리스트 중에서 && 예약취소여부가 N인것만 수정가능함! 예약번호 리턴하는 메소드
	public String updateReservation(List noList, List cancelYnList) {
		System.out.println("\n예약변경할 예약번호를 입력해주세요");
		boolean isNum = false;
		String input = "";
		while(!isNum) {
			System.out.print("입력 : ");
			input = InputUtil.sc.nextLine();
			
			for(int i=0; i<noList.size(); i++) {
				if(input.equals(noList.get(i))&& cancelYnList.get(i).equals("N")) {
					isNum = true;
					break;
				}
			}
		}
		return input;
	}
	
	//예약번호로 숙소번호 select하는 메소드
	public int selectLodgingNo(String updateNo) {
		int lodgingNo = new LodgingService().selectLodgingNo(updateNo);
		return lodgingNo;
	}
	
	public void updateMenu(List noList, String updateNo, List breakfastYnList) {
		int a = 0;
		//사용자가 선택한 조식여부가 Y인지N인지 메뉴 보여주기 전에 판단하기
		for(int i=0; i<noList.size(); i++) {
			if(noList.get(i).equals(updateNo)) {
				a = i;
				break;
			}
		}
		
		String userBreakfastYn = (String)breakfastYnList.get(a);
		
		String number = "";
		while(!number.equals("1")&&!number.equals("2")&&!number.equals("3")) {
			System.out.println("1. 조식선택 변경하기");
			System.out.println("2. 날짜 변경하기");
			System.out.println("3. 나가기");
			System.out.print("입력 : ");
			number = InputUtil.sc.nextLine();
			
			switch(number) {
			case "1" : //조식변경
				updateBreakfastYn(userBreakfastYn, updateNo);
				break;
			case "2" : //날짜변경
				String startDate = null;
				while(true) {
					System.out.println("\n변경할 입실날짜를 입력해주세요(yy/mm/dd)");
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
					System.out.println("\n변경할 퇴실날짜를 입력해주세요(yy/mm/dd)");
					System.out.print("입력 : ");
					endDate = InputUtil.sc.nextLine();
					
					if(checkDate(endDate) && checkEndDate(startDate, endDate)) {
						break;
					}else {
						System.out.println("\n잘못된 입력입니다.\n");
					}
				}
				
				int num = selectRoomNo(updateNo);
				boolean isEmpty = checkEmpty(num, startDate, endDate);
				if(isEmpty==false) {
					System.out.println("\n해당날짜는 예약완료되었습니다. 다른 날짜를 선택해주세요");
					return;
				}
				if(isEmpty) {
					System.out.println("\n변경한 날짜로 예약변경 진행 중...");
					//해당 예약번호에 입실,퇴실날짜 업데이트
					updateStartEndDate(updateNo, startDate, endDate);
				}
				break;
			case "3" :
				return;
			}
			
		}
	}
	
	private void updateStartEndDate(String updateNo, String startDate, String endDate) {
		int result = new LodgingService().updateStartEndDate(updateNo, startDate, endDate);
		if(result == 1) {
			System.out.println("예약 날짜 변경이 완료되었습니다.\n");
		}else {
			System.out.println("예약 날짜 변경 중 에러발생!");
		}
		
	}

	public void updateBreakfastYn(String userBreakfastYn, String updateNo) {
		if(userBreakfastYn.equals("Y")) { //사용자가 조식을 선택했다면 취소하는 경우밖에 없음!
			System.out.println("\n조식을 취소하시겠습니까?");
			
			System.out.println("1. 예");
			System.out.println("2. 아니오");
			String input = checkWeirdNum();
			if(input.equals("1")) {
				//조식취소코드
				cancelBf(updateNo);
			}
			if(input.equals("2")) {
				return;
			}
			
		}
		
		//사용자가 조식선택을 안했다면 숙소에 조식이 있는데 안한건지,애초에 숙소에 조식이 없는건지 모르니까 숙소조식여부를 알아와야함
		//숙소조식여부가 N이라면 숙소에 조식이 없습니다. 출력
		//숙소조식여부가 Y라면 조식을 추가하시겠습니까? 출력하고 예약내역에 업데이트(예약내역에 업데이트 잘되는지 꼼꼼히 봐야겠음!!!)
		if(userBreakfastYn.equals("N")) {  
			boolean isBf = checkBfYn(updateNo);
			if(isBf) {
				System.out.println("\n조식을 추가하시겠습니까?");
				
				System.out.println("1. 예");
				System.out.println("2. 아니오");
				String input = checkWeirdNum();
				if(input.equals("1")) {
					//조식추가코드
					addBf(updateNo);
				}
				if(input.equals("2")) {
					return;
				}
			}
			if(isBf==false) {
				System.out.println("\n숙소에 조식이 없으므로 조식변경이 불가합니다!");
				return;
			}
		}
	}
	
	public boolean checkBfYn(String updateNo) {
		boolean isBf = new LodgingService().checkBfYn(updateNo);
		if(isBf) {
			return true;
		}
		return false;
	}
	
	public String checkWeirdNum() {
		String input = "";
		while(true) {
			System.out.print("입력 : ");
			input = InputUtil.sc.nextLine();
			if(input.equals("1")||input.equals("2")) {
				break;
			}
		}
		return input;
	}
	
	//조식 취소하면 총금액에서 -35000원, 조식선택여부 N으로 업데이트
	public void cancelBf(String updateNo) {
		int result = new LodgingService().cancelBf(updateNo);
		if(result == 1) {
			System.out.println("조식 취소가 완료되었습니다.");
			System.out.println("등록된 계좌로 35000원 환불되었습니다.\n");
		}else {
			System.out.println("조식 취소 중 에러발생!");
		}
	}
	
	public void addBf(String updateNo) {
		int result = new LodgingService().addBf(updateNo);
		if(result == 1) {
			System.out.println("조식 추가가 완료되었습니다.");
			System.out.println("등록된 계좌로 35000원 추가 결제되었습니다.\n");
		}else {
			System.out.println("조식 추가 중 에러발생!");
		}
	}
	
	//예약번호로 방번호 셀렉트해오기
	public int selectRoomNo(String updateNo) {
		int num = new LodgingService().selectRoomNo(updateNo);
		return num;
	}
	
	//예약테이블에 해당 방번호에 사용자가 입력한 입실퇴실 날짜가 겹치는지 확인-> 안겹치면 true리턴
	public boolean checkEmpty(int roomNo, String startDate, String endDate) {
		boolean isEmpty = new LodgingService().checkEmpty(roomNo, startDate, endDate);
		return isEmpty;
		
		
	}
	
	
	//예약 번호 리스트 중에서 && 예약취소여부가 N인것만 예약 취소해야됨! 예약번호 리턴하는 메소드
	public String cancelReservation(List noList, List cancelYnList) {
		System.out.println("예약취소할 예약번호를 입력해주세요");
		boolean isNum = false;
		String input = "";
		while(!isNum) {
			System.out.print("입력 : ");
			input = InputUtil.sc.nextLine();
			
			for(int i=0; i<noList.size(); i++) {
				if(input.equals(noList.get(i))&& cancelYnList.get(i).equals("N")) {
					isNum = true;
					break;
				}
			}
		}
		return input;
		
	}
	
	//예약 번호 리스트 중에서 && 예약취소여부가 N인것만 리뷰작성 가능함!! 예약번호 리턴하는 메소드
	public String reviewReservation(List noList, List cancelYnList) {
		System.out.println("리뷰 작성할 예약번호를 입력해주세요");
		boolean isNum = false;
		String input = "";
		while(!isNum) {
			System.out.print("입력 : ");
			input = InputUtil.sc.nextLine();
			
			for(int i=0; i<noList.size(); i++) {
				if(input.equals(noList.get(i))&& cancelYnList.get(i).equals("N")) {
					isNum = true;
					break;
				}
			}
		}
		return input;
		
	}
	
	//예약 취소 Y로 예약테이블에 업데이트하기
	public void updateCancelY(String input) {
		int result = new LodgingService().updateCancelY(input);
		if(result == 1) {
			System.out.println("\n예약취소가 완료되었습니다.\n");
		}else {
			System.out.println("예약취소 중 에러발생!");
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
			
			System.out.println("쿠폰번호: " + couponInfoNo + " | " + couponCode + " | " + discount);
			
			couponInfoNoList.add(couponInfoNo);
			couponIssuedNoList.add(couponIssuedNo);
		}
		
		boolean isNum = false;
		String input = "";
		int index = 0;
		while(!isNum) {
			System.out.println("쿠폰사용은 숫자를 입력해주세요(건너뛰려면 Y입력)");
			System.out.print("입력 : ");
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
		case "4" :
			discount = 5000;
			break;	
		case "5" :
			discount = 50000;
			break;	
		case "6" :
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
	
	//관리자 페이지에서 숙소예약현황조회 (LODGING_RESERVATION 테이블에서 전체 SELECT해서 가져오기)
	public void selectTotalReservation() {

		System.out.println("\n----- 숙소 예약 현황 조회 -----\n");
		
		List<LodgingReservationVo> voList = new LodgingService().selectTotalReservation();

		System.out.println("예약번호 | 예약날짜 | 회원아이디 | 회원이름 | 숙소이름 | 숙소주소 | 숙소전화번호 | 숙소조식여부 | 방유형 | 인원수 | 입실날짜 | 퇴실날짜 | 조식선택여부 | 총금액 | 결제여부 | 예약취소여부");
		
		for(int i=0; i<voList.size(); i++) {
			LodgingReservationVo vo = voList.get(i);
			String no = vo.getNo();
			String reserveDate = vo.getReserveDate();
			String memberId = vo.getMemberId();
			String memberName = vo.getMemberName();
			String lodgingName = vo.getLodgingName();
			String address = vo.getAddress();
			String phone = vo.getLodgingPhone();
			String lodgingBfYn = vo.getLodgingBfYn();
			String roomType = vo.getRoomType();
			String people = vo.getPeople();
			String startDate = vo.getStartDate();
			String endDate = vo.getEndDate();
			String breakfastYn = vo.getBreakfastYn();
			String payment = vo.getPayment();
			String payYn = vo.getPayYn();
			String cancelYn = vo.getCancelYn();
			
			System.out.println(no + " | " + reserveDate+ " | " + memberId+ " | " + memberName+ " | " + lodgingName+ " | " + address+ " | " + phone+ " | " + lodgingBfYn+ " | " + roomType+ " | " + people+ "명 | " +startDate.substring(0,10)+ " | " + endDate.substring(0,10)+ " | " + breakfastYn+ " | " + payment+ "원 | " + payYn+ " | "+ cancelYn);

		}
		System.out.println();
	}
	
	public void showLodgingInformation(int num) {
		
		List<LodgingVo> voList = new LodgingService().showLodgingInformation(num);
		
		System.out.println("-----------------------");
		System.out.println("----- 숙소 상세 정보 -----");
		System.out.println("-----------------------");
		
		LodgingVo vo1 = voList.get(0);
		System.out.println("리뷰 : " + getReviewCnt(num) + "개 | 찜 : " + getZzimCnt(num) + "개");
		System.out.println("숙소 이름 : " + vo1.getName());
		System.out.println("숙소 주소 : " + vo1.getAddress());
		System.out.println("전화번호 : " + vo1.getPhone());
		System.out.println("조식여부 : " + vo1.getBreakfastYn());
		System.out.println();
		for(int i=0; i<voList.size(); i++) {
			LodgingVo vo = voList.get(i);
			
			String roomType = vo.getRoomType();
			int maxPeople = vo.getMaxPeople();
			String price = vo.getPrice();		
			int maxPeople2 = vo.getMaxPeople();
			
			System.out.println("방 타입 : " + vo.getRoomType() + " | 1박가격 : " + vo.getPrice() + "원 | 최대인원수 : " + maxPeople2 + "명");

		}

		System.out.println("-----------------------");
		System.out.println("-----------------------");
		System.out.println("맞으시면 다음으로 넘어가주세요.");	
		
	}
	
	//숙소번호를 파라미터로 숙소의 리뷰 개수 리턴
	public int getReviewCnt(int num) {
		int reviewCnt = new LodgingService().getReviewCnt(num);
		return reviewCnt;
	}
	
	//숙소번호를 파라미터로 숙소의 찜개수 리턴
	public int getZzimCnt(int num) {
		int ZzimCntCnt = new LodgingService().getZzimCnt(num);
		return ZzimCntCnt;
	}
	
	//숙소번호를 파라미터로 숙소의 최저가격 리턴
	public int getMinPrice(int num) {
		int minPrice = new LodgingService().getMinPrice(num);
		return minPrice;
	}

}
