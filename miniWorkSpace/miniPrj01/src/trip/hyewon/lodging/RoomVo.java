package trip.hyewon.lodging;

public class RoomVo {
	
	public RoomVo() {
		
	}
	

	public RoomVo(int no, String roomType, int maxPeople, int price, String breakfastYn, String lodgingName,
			String address, String phone) {
		super();
		this.no = no;
		this.roomType = roomType;
		this.maxPeople = maxPeople;
		this.price = price;
		this.breakfastYn = breakfastYn;
		this.lodgingName = lodgingName;
		this.address = address;
		this.phone = phone;
	}



	private int no;
	private String roomType;
	private int maxPeople;
	private int price;
	private String breakfastYn;
	private String lodgingName;
	private String address;
	private String phone;
	
	
	
	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getLodgingName() {
		return lodgingName;
	}

	public void setLodgingName(String lodgingName) {
		this.lodgingName = lodgingName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBreakfastYn() {
		return breakfastYn;
	}
	public void setBreakfastYn(String breakfastYn) {
		this.breakfastYn = breakfastYn;
	}


	@Override
	public String toString() {
		return "RoomVo [no=" + no + ", roomType=" + roomType + ", maxPeople=" + maxPeople + ", price=" + price
				+ ", breakfastYn=" + breakfastYn + ", lodgingName=" + lodgingName + ", address=" + address + ", phone="
				+ phone + "]";
	}



	

	
	
	
	
}
