package trip.min.member;

import java.awt.im.InputContext;

import trip.hyewon.lodging.LodgingController;
import trip.kms.lodgingReview.LodgingReviewController;
import trip.min.main.MemberMain;
import trip.min.manager.ManagerController;
import trip.min.util.InputUtil;
import trip.se.mainPost.MainPost;
import trip.se.post.PostController;
import trip.se.qna.QnaController;
import trip.daeun.lodging.wish.WishController;
import trip.dk.coupon.CouponController;

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
		boolean isSignOut = false;
		while(!isSignOut) {
			System.out.println("===== 마이페이지 입니다 =====");
			System.out.println("1. 내 정보 수정하기");
			System.out.println("2. 숙소 예약 내역 확인");
			System.out.println("3. 숙소 찜 목록 조회");
			System.out.println("4. 내가 쓴 게시물 확인");
			System.out.println("5. 내가 쓴 문의글 확인");
			System.out.println("6. 내가 쓴 리뷰 확인");
			System.out.println("7. 현재 보유 쿠폰");
			System.out.println("8. 회원탈퇴");
			System.out.println("9. 나가기");
			System.out.println("=========================");
			System.out.print("입력 : ");
			String input = InputUtil.sc.nextLine();
			switch(input) {
			case "1":
				new ManagerController().editMemberCheck();
				break;
			case "2":
				System.out.println("숙소 예약 내역 확인");
				new LodgingController().showMyReservation(MemberMain.LoginMember.getNo()); 
				break;
			case "3":
				System.out.println("숙소 찜 목록 조회");
				new WishController().showZzimList();
				break;
			case "4":
				System.out.println("내가 쓴 게시물 확인");
				new MainPost().showMyPost();
				break;
			case "5":
				System.out.println("내가 쓴 문의글 확인");
				new MainPost().showMyQna();
				break;
			case "6":
				System.out.println("내가 쓴 리뷰 확인");
				new LodgingReviewController().showMyReview();
				break;
			case "7":
				System.out.println("현재 보유 쿠폰");
				new CouponController().showCoupon(); 
				break;
			case "8":
				System.out.println("회원탈퇴를 진행합니다.");
				isSignOut = new MemberController().signOutMember();
				return;
			case "9":
				System.out.println("이전 페이지로 돌아갑니다.");
				return;
			default :
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
		
	}

	
	public void checkcommunity() {
		//게시물 확인 - 게시물 상세페이지 연결
		new PostController().postView();
	}
	
	public void checkmycomment() {
		//내가 쓴 댓글 확인
		
	}
	
	public void checkmyreview() {
		//내가 쓴 리뷰 확인
		
	}

	
	
	
	
	
	
	
	
}//class
