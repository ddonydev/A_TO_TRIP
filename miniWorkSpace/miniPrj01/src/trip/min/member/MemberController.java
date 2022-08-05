package trip.min.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import trip.min.common.JDBCTemplate;
import trip.min.main.MemberMain;
import trip.min.manager.ManagerService;
import trip.min.util.InputUtil;

public class MemberController {

	public void login() {
			
		if(MemberMain.LoginMember != null) {
			//로그인 O
			System.out.println("이미 로그인 되어있습니다.");
			return;
		}
		
		System.out.println("===== 로그인 =====");
		System.out.print("아이디 : ");
		String id = InputUtil.sc.nextLine();
		System.out.print("패스워드 : ");
		String pwd = InputUtil.sc.nextLine();
		
		try {
			MemberVo vo = new MemberDao().login(id, pwd);
			if(vo != null) {
				//로그인 성공
				System.out.println("로그인 성공 !\n");
				MemberMain.LoginMember = vo;
			}else {
				//로그인 실패
				System.out.println("로그인 실패 !\n\n");
			}
		} catch (Exception e) {
			//로그인 실패
			System.out.println("[에러] 로그인 실패 !\n\n");
			e.printStackTrace();
		}
	}//login


	public void join() {
		System.out.println("===== 회원가입 =====");
		System.out.print("아이디  : ");
		String id = InputUtil.sc.nextLine();
		System.out.print("비밀번호  : ");
		String pwd = InputUtil.sc.nextLine();
		System.out.print("비밀번호 확인  : ");
		String pwd2 = InputUtil.sc.nextLine();
		System.out.print("이메일  : ");
		String email = InputUtil.sc.nextLine();
		System.out.print("이름  : ");
		String name = InputUtil.sc.nextLine();
		System.out.print("생년월일  : ");
		String birth = InputUtil.sc.nextLine();
		System.out.print("핸드폰번호  : ");
		String phone = InputUtil.sc.nextLine();
		System.out.print("닉네임  : ");
		String nick = InputUtil.sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setPwd2(pwd2);
		vo.setEmail(email);
		vo.setName(name);
		vo.setBirth(birth);
		vo.setPhone(phone);
		vo.setNick(nick);
		
		int result = new MemberService().join(vo);
	
		//실행 결과에 따른 응답
		if(result == 1) {
			System.out.println("회원가입이 되었습니다. ");
		}else {
			System.out.println("[ERROR:" + result + "] 회원가입 실패하셨습니다.");
		}

		
	}//join

	public void findIdPwdView () {
		
		System.out.println("===== 아이디 비밀번호 찾기 =====");
		System.out.println("\n생년월일을 아래에 입력해주세요");
		System.out.print("yymmdd 방식으로 입력해주세요 : ");
		String birth = InputUtil.sc.nextLine();
		
		try {
			MemberVo vo = new MemberDao().findIdPwd(birth);
			
			if(vo != null) {
				System.out.println('"'+ vo.getName()+ '"' +" 님의 아이디 및 비밀번호 입니다.");
				System.out.println("아이디 : " + vo.getId());
				System.out.println("비밀번호 : " + vo.getPwd());
			}else {
				System.out.println("생년월일 정보를 정확히 입력해주세요.");
			}
		} catch (Exception e) {
			System.out.println("[ERROR] :: 에러");
			e.printStackTrace();
		}
		
	}//findIdPwdview
	
	public void signOutMember() {
		System.out.println("===== 회원 탈퇴 =====");
		System.out.println("!!!정말 회원 탈퇴 하시겠습니까!!!");
		System.out.print("1. 회원탈퇴 여부 'Y / N' : ");
		String quitYn = InputUtil.sc.nextLine();
		System.out.print("2. 마지막으로 아이디를 입력해주세요 : ");
		String id = InputUtil.sc.nextLine();
	
		MemberVo vo = new MemberVo();
		vo.setEmail(quitYn);
		vo.setName(id);
		
		int result = new MemberService().signOutMember(vo);
		
		if(result == 1) {
			System.out.println("회원 탈퇴가 완료되었습니다.");
		}else {
			System.out.println("[ERROR :" + result + "] : 회원정보 수정 에러");
		}
	}//editMember

}//class
