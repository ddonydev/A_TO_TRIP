package trip.hyewon.lodging;

public class LodgingVo {
	
	public LodgingVo() {
		
	}

	public LodgingVo(String no, String lodgingCode, String name, String phone, String address, String lodgingLike,
			String breakfastYn, String locationCode, int zzimCnt, int reviewCnt) {
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
	}

	private String no;
	private String lodgingCode;
	private String name;
	private String phone;
	private String address;
	private String lodgingLike;
	private String breakfastYn;
	private String locationCode;
	private int zzimCnt;
	private int reviewCnt;
	
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
				+ ", locationCode=" + locationCode + ", zzimCnt=" + zzimCnt + ", reviewCnt=" + reviewCnt + "]";
	}
	
}
