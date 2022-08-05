package trip.min.manager;

import java.sql.Timestamp;
import java.util.List;

import trip.hyewon.lodging.LodgingVo;
import trip.min.manager.ManagerService;
import trip.min.member.MemberService;
import trip.min.member.MemberVo;
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
	
	public void showListMember() {
		
		List<MemberVo> memberVoList = new MemberService().showListMember();
		
		System.out.println("===== 회원 조회 =====");
		
		for (int i = 0; i < memberVoList.size(); i++) {
			//꺼내오기
			MemberVo temp = memberVoList.get(i);
			//변수에 담아주기
			String id = temp.getId();
			String email = temp.getEmail();
			String name = temp.getName();
			String birth = temp.getBirth();
			String phone = temp.getPhone();
			String nick = temp.getNick();
			Timestamp enrollDate = temp.getEnrollDate();
			String quitYn = temp.getQuitYn();
			//변수에 담은것 출력
			System.out.println(" "+ id + " | "+ email + " | " + name + " | " + birth);
			System.out.println(" " + phone + " | " + nick + " | " + quitYn);
			System.out.println(" " + enrollDate + " |");
		}
		
	}//showlistMember
	
	public void editMember() {
		System.out.println("===== 회원 정보 수정 =====");
		System.out.println("!!!모든 항목 작성하여야 함!!!");
		System.out.print("1. 변경할 이메일 : ");
		String email = InputUtil.sc.nextLine();
		System.out.print("2. 변경할 이름 : ");
		String name = InputUtil.sc.nextLine();
		System.out.print("3. 변경할 연락처 : ");
		String phone = InputUtil.sc.nextLine();
		System.out.print("4. 변경할 닉네임 : ");
		String nick = InputUtil.sc.nextLine();
		System.out.print("5. 회원탈퇴 여부 'Y / N' : ");
		String quitYn = InputUtil.sc.nextLine();
		System.out.print("6. 사용자 아이디 : ");
		String id = InputUtil.sc.nextLine();
	
		MemberVo vo = new MemberVo();
		vo.setEmail(email);
		vo.setName(name);
		vo.setPhone(phone);
		vo.setNick(nick);
		vo.setQuitYn(quitYn);
		vo.setId(id);
		
		int result = new ManagerService().editMember(vo);
		
		if(result == 1) {
			System.out.println("회원 정보가 변경되었습니다.");
		}else {
			System.out.println("[ERROR :" + result + "] : 회원정보 수정 에러");
		}
	}//editMember
	
}
