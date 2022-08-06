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
				System.out.println("로그인 성공 !");
				System.out.println("=============\n");
				MemberMain.LoginMember = vo;
				MemberMain.LoginMember.setQuitYn("N");
			}else {
				//로그인 실패
				System.out.println("로그인 실패 !");
				System.out.println("=============\n");
			}
		} catch (Exception e) {
			//로그인 실패
			System.out.println("[에러] 로그인 실패 !\n\n");
			e.printStackTrace();
		}
	}//login


	public void join() {
		System.out.println("===== 회원가입 =====");
		boolean isIdDuplicate = true;
        String id = null;
        while(isIdDuplicate) {
            System.out.print("아이디 : ");
            id = InputUtil.sc.nextLine();

            isIdDuplicate = checkIdDuplicate(id);
            if(isIdDuplicate) {
                System.out.println("아이디 중복입니다.");
            }
        }
		System.out.print("비밀번호  : ");
		String pwd = InputUtil.sc.nextLine();
		
		boolean reconfirmPwd = true;
		String pwd2 = null;
        while(reconfirmPwd) {
            System.out.print("비밀번호 재확인 : ");
            pwd2 = InputUtil.sc.nextLine();
            reconfirmPwd = reconfirmPwd(pwd, pwd2);
            if(reconfirmPwd) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
            }
        }
		System.out.print("이메일  : ");
		String email = InputUtil.sc.nextLine();
		System.out.print("이름  : ");
		String name = InputUtil.sc.nextLine();
		System.out.print("생년월일  : ");
		String birth = InputUtil.sc.nextLine();
		System.out.print("핸드폰번호  : ");
		String phone = InputUtil.sc.nextLine();
		
		boolean isContainManager = false;
        String nick = null;
        while(!isContainManager) {
            System.out.print("닉네임 : ");
            nick = InputUtil.sc.nextLine();

            isContainManager = checkNick(nick);
            if(!isContainManager) {
                System.out.println("닉네임에는 '관리자'가 포함될 수 없습니다!");
            }
        }

		
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
			System.out.println("====================");
			System.out.println("회원가입이 되었습니다.\n ");
		}else {
			System.out.println("[ERROR:" + result + "] 회원가입 실패하셨습니다.");
		}

		
	}//join
	
	//아이디 중복확인
    public boolean checkIdDuplicate(String id) {
        boolean result = new MemberService().checkIdDuplicate(id);
        return result;
    }
	
	//닉네임 체크
    public boolean checkNick(String nick) {
        boolean result = new MemberService().checkNick(nick);
        return result;
    }
  //비밀번호 재확인
    public boolean reconfirmPwd(String pwd, String pwd2) {
        boolean result = new MemberService().reconfirmPwd(pwd, pwd2);
        return result;
    }

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
				System.out.println("=============================\n");
			}else {
				System.out.println("생년월일 정보를 정확히 입력해주세요.");
			}
		} catch (Exception e) {
			System.out.println("[ERROR] :: 에러");
			e.printStackTrace();
		}
		
	}//findIdPwdview
	
	public boolean signOutMember() {
		boolean isSignOut = false;
		System.out.println("===== 회원 탈퇴 =====");
		System.out.println("!!!정말 회원 탈퇴 하시겠습니까!!!");
		System.out.print("1. 회원탈퇴 여부 'Y / N' : ");
		String quitYn = InputUtil.sc.nextLine();
		System.out.print("2. 마지막으로 아이디를 입력해주세요 : ");
		String id = InputUtil.sc.nextLine();
		MemberMain.LoginMember.setQuitYn(quitYn);
		MemberVo vo = new MemberVo();
		vo.setQuitYn(quitYn);
		vo.setId(id);
		
		int result = new MemberService().signOutMember(vo);
		
		if(result == 1) {
			if(quitYn.equals("Y")) {
				//Y누르면 탈퇴
				isSignOut = true;
				System.out.println("\n=======================================");
				System.out.println("회원 탈퇴 요청이 완료되었습니다.");
				System.out.println("이시간 이후로 로그아웃 후 재 로그인이 불가능합니다.");
				System.out.println("탈퇴를 번복하고 싶으시면 다시 마이페이지로 돌아가서");
				System.out.println("회원 탈퇴 메뉴에서 N 을 입력해 주세요.");
				System.out.println("========================================\n");
				return isSignOut;
			}else if(quitYn.equals("N")) {
				//탈퇴 취소
				System.out.println("\n============================");
				System.out.println("회원 탈퇴 요청이 취소되었습니다.");
				System.out.println("============================\n");
			}
			
		}else {
			System.out.println("[ERROR :" + result + "] : 회원정보 수정 에러");
		}
		return isSignOut;
	}//editMember

}//class
