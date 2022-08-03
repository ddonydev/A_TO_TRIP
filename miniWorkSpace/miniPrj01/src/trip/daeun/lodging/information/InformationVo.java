package trip.daeun.lodging.information;

public class InformationVo {

	// DB 테이블 칼럼들 필드로 작성
	
	// 숙소이름, 방, 1박 가격, 조식여부, 위치 출력
	
	public InformationVo() {
		
	}
	
	
	public InformationVo(int no, String name, String room, int price, String breakfast, String address) {
		this.no = no;
		this.name = name;
		this.room = room;
		this.price = price;
		this.breakfast = breakfast;
		this.address = address;
	}


	private int no;
	private String name;
	private String room;
	private int price;
	private String breakfast;
	private String address;
	
	
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getBreakfast() {
		return breakfast;
	}


	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	
	@Override
	public String toString() {
		return "InformationVo [no=" + no + ", name=" + name + ", room=" + room + ", price=" + price + ", breakfast="
				+ breakfast + ", address=" + address + "]";
	}
	
	
	
	
	
	
	
}

	
	