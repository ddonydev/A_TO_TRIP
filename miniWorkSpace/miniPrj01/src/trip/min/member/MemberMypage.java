package trip.min.member;

import java.awt.im.InputContext;

import trip.hyewon.lodging.LodgingController;
import trip.min.main.MemberMain;
import trip.min.util.InputUtil;
import trip.dk.coupon.CouponControll;

public class MemberMypage {

	/*
	 * 내 정보 수정하기 -> 회원탈퇴, 비밀번호, 닉네임, 이메일, 전화번호, 이름
	 * 숙소 예약 내역 확인 -> 전체 취소, 날짜, 조식 여부 변경
	 * 숙소 찜 목록 조회
	 * 내가 쓴 게시물 확인
	 *  - 게시물 상세 페이지 연결
	 * 내가 쓴 댓글 확인
	 * 내가 쓴 리뷰 확인
	 * 가지고 있는 쿠폰 확인(발급쿠폰)
	 */
	public void myPageMenu() {
		System.out.println("===== 마이페이지 입니다 =====");
		System.out.println("1. 내 정보 수정하기");
		System.out.println("2. 숙소 예약 내역 확인");
		System.out.println("3. 숙소 찜 목록 조회");
		System.out.println("4. 내가 쓴 게시물 확인");
		System.out.println("5. 내가 쓴 댓글 확인");
		System.out.println("6. 내가 쓴 리뷰 확인");
		System.out.println("7. 현재 보유 쿠폰");
		System.out.println("8. 나가기");
		
		while(true) {
			String input = InputUtil.sc.nextLine();
			switch(input) {
			case "1":
				System.out.println("내정보 수정하기");
				editMember();
				break;
			case "2":
				System.out.println("숙소 예약 내역 확인");
				new LodgingController().showMyReservation(MemberMain.LoginMember.getNo) 
				break;
			case "3":
				System.out.println("숙소 찜 목록 조회");
				checkWishList();
				break;
			case "4":
				System.out.println("내가 쓴 게시물 확인");
				checkcommunity();
				break;
			case "5":
				System.out.println("내가 쓴 댓글 확인");
				checkmycomment();
				break;
			case "6":
				System.out.println("내가 쓴 리뷰 확인");
				checkmyreview();
				break;
			case "7":
				System.out.println("현재 보유 쿠폰");
				keepcoupon();
				break;
			case "8":
				System.out.println("이전 페이지로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
		
	}
	
	public void editMember() {
		//내 정보 수정하기(sql = update)
		
	}
	
	public void checkReservation() {
		//예약 확인
		
	}
	
	public void checkWishList() {
		//찜 목록 조회
		
	}
	
	public void checkcommunity() {
		//게시물 확인 - 게시물 상세페이지 연결
		
	}
	
	public void checkmycomment() {
		//내가 쓴 댓글 확인
		
	}
	
	public void checkmyreview() {
		//내가 쓴 리뷰 확인
		
	}
	
	public void keepcoupon() {
		//현재 보유 쿠폰
		new CouponController().showCoupon(); 
	}
	
	
	
	
	
	
	
	
	
}//class
