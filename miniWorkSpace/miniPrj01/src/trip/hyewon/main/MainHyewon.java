package trip.hyewon.main;

import trip.hyewon.lodging.LodgingController;
import trip.hyewon.util.InputUtil;

public class MainHyewon {

	public static void main(String[] args) {
		System.out.println("===== A TO TRIP =====");
		
		System.out.println("1. 숙소 검색");
		System.out.println("2. 추천 숙소 조회");
		System.out.println("3. 인기 숙소 조회");
		

		
		System.out.print("입력 : ");
		String input = InputUtil.sc.nextLine();
		LodgingController lc = new LodgingController();
		
		switch(input) {
		case "1" :
			lc.searchLodging(); //숙소검색
			break;
		case "2" :
			lc.showRecommendLodging(); //리뷰순 숙소조회(일단10개)
			break;
		case "3" :
			lc.showPopularLodging(); //좋아요순 숙소조회(일단10개)
			break;
		default :
			System.out.println("잘못된 입력입니다!");
		}

	}

}
