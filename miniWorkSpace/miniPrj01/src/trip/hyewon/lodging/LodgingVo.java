package trip.hyewon.lodging;

public class LodgingVo {
	
	public LodgingVo() {
		
	}

	public LodgingVo(String no, String lodgingCode, String name, String phone, String address, String lodgingLike,
			String breakfastYn, String locationCode, int zzimCnt, int reviewCnt, String roomType, String price,
			int maxPeople) {
		super();
		this.no = no;
		this.lodgingCode = lodgingCode;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.lodgingLike = lodgingLike;
		this.breakfastYn = breakfastYn;
		this.locationCode = locationCode;
		this.zzimCnt = zzimCnt;
		this.reviewCnt = reviewCnt;
		this.roomType = roomType;
		this.price = price;
		this.maxPeople = maxPeople;
	}

	private String no;
	private String lodgingCode;
	private String name; //숙소이름
	private String phone; //숙소전화번호
	private String address; //숙소주소
	private String lodgingLike;
	private String breakfastYn; //숙소조식여부
	private String locationCode;
	private int zzimCnt; //찜개수
	private int reviewCnt; //리뷰개수
	private String roomType;//방유형
	private String price;//1박가격
	private int maxPeople;//최대인원수
	
	
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}
	public int getReviewCnt() {
		return reviewCnt;
	}
	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}
	public int getZzimCnt() {
		return zzimCnt;
	}
	public void setZzimCnt(int zzimCnt) {
		this.zzimCnt = zzimCnt;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getLodgingCode() {
		return lodgingCode;
	}
	public void setLodgingCode(String lodgingCode) {
		this.lodgingCode = lodgingCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLodgingLike() {
		return lodgingLike;
	}
	public void setLodgingLike(String lodgingLike) {
		this.lodgingLike = lodgingLike;
	}
	public String getBreakfastYn() {
		return breakfastYn;
	}
	public void setBreakfastYn(String breakfastYn) {
		this.breakfastYn = breakfastYn;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	@Override
	public String toString() {
		return "LodgingVo [no=" + no + ", lodgingCode=" + lodgingCode + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + ", lodgingLike=" + lodgingLike + ", breakfastYn=" + breakfastYn
				+ ", locationCode=" + locationCode + ", zzimCnt=" + zzimCnt + ", reviewCnt=" + reviewCnt + ", roomType="
				+ roomType + ", price=" + price + ", maxPeople=" + maxPeople + "]";
	}
}
