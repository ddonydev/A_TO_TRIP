package trip.min.manager;

import trip.hyewon.lodging.LodgingVo;
import trip.min.manager.ManagerService;
import trip.min.util.InputUtil;

public class ManagerController {

	public void joinLodging() {
		System.out.println("===== 숙소 등록 =====");
		System.out.println("--숙소 유형 코드--");
		System.out.println("1 : 호텔 2 : 펜션 3: 개인숙소");
		System.out.print("숙소유형코드 : ");
		String lodgingCode = InputUtil.sc.nextLine();
		System.out.print("숙소이름  : ");
		String lodgingName = InputUtil.sc.nextLine();
		System.out.print("숙소전화번호  : ");
		String lodgingPhone = InputUtil.sc.nextLine();
		System.out.print("숙소주소  : ");
		String lodgingAdd = InputUtil.sc.nextLine();
		System.out.print("숙소좋아요개수  : ");
		String lodgingLike = InputUtil.sc.nextLine();
		System.out.println("-- 지역 유형 코드 --");
		System.out.println("<<1: 수도권 2: 강원도 3: 경상도>>");
		System.out.println("<<4: 충청도 5: 전라도 6: 제주도>>");
		System.out.print("지역유형코드 : ");
		String locationCode = InputUtil.sc.nextLine();
		
		LodgingVo vo = new LodgingVo();
		vo.setLodgingCode(lodgingCode);
		vo.setName(lodgingName);
		vo.setPhone(lodgingPhone);
		vo.setAddress(lodgingAdd);
		vo.setLodgingLike(lodgingLike);
		vo.setLocationCode(locationCode);
		
		int result = new ManagerService().joinLodging(vo);
	
		//실행 결과에 따른 응답
		if(result == 1) {
			System.out.println("숙소 등록이 완료되었습니다. ");
		}else {
			System.out.println("[ERROR:" + result + "] 숙소 등록에 실패하셨습니다.");
		}
		
	}//joinLodging
	
	public void editLodging() {
		System.out.println("===== 숙소 수정 =====");
		System.out.println("!!!모든 항목 작성하여야 함!!!");
		System.out.println("-- 숙소 유형 코드 --");
		System.out.println("1 : 호텔 2 : 펜션 3: 개인숙소");
		System.out.print("1. 변경할 숙소 코드 : ");
		String lodgingCode = InputUtil.sc.nextLine();
		System.out.print("2. 변경할 숙소 이름 : ");
		String lodgingName = InputUtil.sc.nextLine();
		System.out.print("3. 변경할 숙소 연락처 : ");
		String lodgingPhone = InputUtil.sc.nextLine();
		System.out.print("4. 변경할 숙소 주소 : ");
		String lodgingAdd = InputUtil.sc.nextLine();
		System.out.print("5. 변경할 숙소 좋아요 갯수 : ");
		String lodginglike = InputUtil.sc.nextLine();
		System.out.print("6. 변경할 조식 'Y / N' : ");
		String lodgingBob = InputUtil.sc.nextLine();
		System.out.println("-- 지역 유형 코드 --");
		System.out.println("<<1: 수도권 2: 강원도 3: 경상도>>");
		System.out.println("<<4: 충청도 5: 전라도 6: 제주도>>");
		System.out.print("7. 변경할 지역유형코드: ");
		String locationCode = InputUtil.sc.nextLine();
		System.out.print("8. 변경할 숙소번호 : ");
		String lodgingNo = InputUtil.sc.nextLine();
	
		LodgingVo vo = new LodgingVo();
		vo.setLodgingCode(lodgingCode);
		vo.setName(lodgingName);
		vo.setPhone(lodgingPhone);
		vo.setAddress(lodgingAdd);
		vo.setLodgingLike(lodginglike);
		vo.setBreakfastYn(lodgingBob);
		vo.setLocationCode(locationCode);
		vo.setNo(lodgingNo);
		
		int result = new ManagerService().editLodging(vo);
		
		if(result == 1) {
			System.out.println("숙소 정보가 변경되었습니다.");
		}else {
			System.out.println("[ERROR :" + result + "] : 에러 에러");
		}
		
	}//editLodging
	
}
