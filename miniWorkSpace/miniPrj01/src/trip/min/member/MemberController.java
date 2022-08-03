package trip.min.member;

import java.sql.Date;

import trip.min.main.MemberMain;
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
				System.out.println("로그인 성공 !\n\n");
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

}//class
