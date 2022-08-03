package trip.dk.event;

public class EventGame {
	package trip.dk.event;

	import java.util.Random;

	import trip.dk.main.Main;
	import trip.dk.util.InputUtil;

	public class EventGame {
		
		//게임 1 가위바위보 
		public int rpsGame() {
			System.out.println();
			
			
			int userCnt= 0;
			int user = 0;
			int com = 0;
			
		
				for(int i = 0;i<5;i++) {
					System.out.println();
					System.out.println("가위 바위 보![ 가위(1)| 바위(2) | 보(3) ] ");
					
					user = InputUtil.getInt();
					com=(int)(Math.random()*3)+1;
					
					if(user<4&&user>0) {
						switch(user-com){
						case 2:
							case-1 : System.out.println("[닉네임]님이 졌습니다.");
							break;
						case 1:
							case-2 : System.out.println("[닉네임]님이 이겼습니다.");
							userCnt++;
							break;
						case 0:
							System.out.println("비겼습니다.");
							break;
						default : 
							System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
							i--;
						 
						}
						
					}else {
						System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
						i--;
						
					}
					
			
				}	
			
			
			System.out.println("[닉네임]님 승리한 횟수 : " +userCnt);
			
			return userCnt;

			
			
		}
		
		
		
		//게임 2 숫자 맞추기
		public int upDownGame() {
			System.out.println();
			
			int rd = (int)(Math.random()*50 + 1);
		    int count=0;
		    while(true) {
		    	System.out.print("1~50 숫자 입력:");
		        int num =InputUtil.getInt();
		        if(num<rd) {
		            System.out.println("UP");
		        }
		        else if(num>rd) {
		            System.out.println("DOWN");
		        }
		        else {count++;
		            break;}
		        count++;
		    }
		    System.out.println("정답입니다. " +count + "회 만에 맞췄습니다." );
		    return count;
		}
		
	}


}
