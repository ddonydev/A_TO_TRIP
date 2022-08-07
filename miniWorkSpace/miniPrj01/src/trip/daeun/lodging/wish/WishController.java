package trip.daeun.lodging.wish;

import java.awt.Color;
import java.sql.Connection;
import java.util.List;

import javax.swing.JLabel;

import trip.hyewon.lodging.LodgingVo;
import trip.min.main.MemberMain;
import trip.min.util.InputUtil;

public class WishController {
	
    public static final String black    = "\u001B[30m" ;
    public static final String red      = "\u001B[31m" ;
    public static final String green    = "\u001B[32m" ;
    public static final String yellow   = "\u001B[33m" ;
    public static final String blue     = "\u001B[34m" ;
    public static final String purple   = "\u001B[35m" ;
    public static final String cyan     = "\u001B[36m" ;
    public static final String white     = "\u001B[37m" ;

    public static final String exit     = "\u001B[0m" ;


	public void wish() {
		
		
		//찜하기 체크
	
		WishVo vo = new WishVo();
		String lodgingNo = vo.getLodgingNo();
		String memberNo = vo.getMemberNo();
		
		while(true) {
			System.out.println("찜하시겠습니까? Y/N");
			System.out.print("입력 : ");
			String num = InputUtil.sc.nextLine();

			if(num.equalsIgnoreCase("Y")) {
				vo.setMemberNo(MemberMain.LoginMember.getNo());
				vo.setLodgingNo(vo.getLodgingNo());
				vo.setCancelYn("Y");
				System.out.println("찜 되었습니다.");
				
				return;
				
			}else {
				vo.setCancelYn("N");
				
				return;
			}
		}
	}
	
	
	public void wishCancel() {
		
		
		WishVo vo = new WishVo();
		String lodgingNo = vo.getLodgingNo();
		String memberNo = vo.getMemberNo();
		
		while(true){
			
		System.out.println("찜을 해제하시겠습니까? Y/N");
		System.out.print("입력 : ");
		String num = InputUtil.sc.nextLine();

		if(num.equalsIgnoreCase("Y")) {
			vo.setMemberNo(MemberMain.LoginMember.getNo());
			vo.setLodgingNo(vo.getLodgingNo());
			vo.setCancelYn("N");
			System.out.println("찜 해제되었습니다");
			return;
		}else {
			vo.setCancelYn("Y");
			return;
		}
		
		}
	}
	
	
	public void showZzimList() {

		
		
		List<LodgingVo> lodgingVoList = new WishService().showZzimList();
		
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		System.out.println();
		System.out.println();
	
		System.out.println(".         　。　　　　•　    　ﾟ　　      。");
		System.out.println(" 　'    .　　　.　　　  　　.　　　　　。　　");
		System.out.println(" •.        𝑴𝒚 𝒁𝒛𝒊𝒎 𝑳𝒊𝒔𝒕            。　.      ");
		System.out.println("　 　　。　　　　　　ﾟ　　　.　　　　　    .");
		System.out.println(",　　　　.　 .　　     .               。");
	

		
		for(int i = 0; i < lodgingVoList.size(); ++i) {
			LodgingVo temp = lodgingVoList.get(i);
			
			String no = temp.getNo();
			String name = temp.getName();
			String address = temp.getAddress();
			String phone = temp.getPhone();
			
			System.out.println();
			System.out.println("   [" + no + "]");
			System.out.println();
			System.out.println("   ღ 𝑳𝒐𝒅𝒈𝒊𝒏𝒈 ღ  " + name);
			System.out.println();
			System.out.println("   ღ 𝑨𝒅𝒅𝒓𝒆𝒔𝒔 ღ  " + address);
			System.out.println();
			System.out.println("   ღ 𝑷𝒉𝒐𝒏𝒆 ღ  " + phone);
			System.out.println();
			System.out.println(".         　。　　　　•　       . 　ﾟ　　      。");
			System.out.println("　 　　。　　　　　　ﾟ　　　.　　　　　    .");
			
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	
		
		
	}
	
	
	
}
