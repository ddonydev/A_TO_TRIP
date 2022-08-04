package trip.hyewon.lodging;

public class LodgingReservationVo {
	
	public LodgingReservationVo() {
		
	}

	


	public LodgingReservationVo(String no, String memberNo, String lodgingNo, String roomNo, String reserveDate,
			String people, String startDate, String endDate, String payYn, String payment, String breakfastYn,
			String cancelYn, String locationCode, String lodgingName, String address, String couponIssuedNo,
			String couponYn, String payWay, String lodgingPhone) {
		super();
		this.no = no;
		this.memberNo = memberNo;
		this.lodgingNo = lodgingNo;
		this.roomNo = roomNo;
		this.reserveDate = reserveDate;
		this.people = people;
		this.startDate = startDate;
		this.endDate = endDate;
		this.payYn = payYn;
		this.payment = payment;
		this.breakfastYn = breakfastYn;
		this.cancelYn = cancelYn;
		this.locationCode = locationCode;
		this.lodgingName = lodgingName;
		this.address = address;
		this.couponIssuedNo = couponIssuedNo;
		this.couponYn = couponYn;
		this.payWay = payWay;
		this.lodgingPhone = lodgingPhone;
	}




	private String no;			//예약번호
	private String memberNo;	//회원번호
	private String lodgingNo;	//숙소번호
	private String roomNo;		//방번호
	private String reserveDate;	//예약날짜
	private String people;		//인원수
	private String startDate;	//입실날짜
	private String endDate;		//퇴실날짜
	private String payYn;		//결제여부
	private String payment;		//총금액
	private String breakfastYn;	//조식선택여부
	private String cancelYn;	//예약취소여부
	private String locationCode;//지역코드
	private String lodgingName;	//숙소이름
	private String address;		//숙소주소
	private String couponIssuedNo; //발급쿠폰번호
	private String couponYn; 	//쿠폰사용여부
	private String payWay;		//결제수단
	private String lodgingPhone;//숙소전화번호
	
	
	
	
	public String getLodgingPhone() {
		return lodgingPhone;
	}

	public void setLodgingPhone(String lodgingPhone) {
		this.lodgingPhone = lodgingPhone;
	}

	public String getCouponIssuedNo() {
		return couponIssuedNo;
	}
	public void setCouponIssuedNo(String couponIssuedNo) {
		this.couponIssuedNo = couponIssuedNo;
	}
	public String getCouponYn() {
		return couponYn;
	}
	public void setCouponYn(String couponYn) {
		this.couponYn = couponYn;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getLodgingNo() {
		return lodgingNo;
	}
	public void setLodgingNo(String lodgingNo) {
		this.lodgingNo = lodgingNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPayYn() {
		return payYn;
	}
	public void setPayYn(String payYn) {
		this.payYn = payYn;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getBreakfastYn() {
		return breakfastYn;
	}
	public void setBreakfastYn(String breakfastYn) {
		this.breakfastYn = breakfastYn;
	}
	public String getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
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

	@Override
	public String toString() {
		return "LodgingReservationVo [no=" + no + ", memberNo=" + memberNo + ", lodgingNo=" + lodgingNo + ", roomNo="
				+ roomNo + ", reserveDate=" + reserveDate + ", people=" + people + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", payYn=" + payYn + ", payment=" + payment + ", breakfastYn=" + breakfastYn
				+ ", cancelYn=" + cancelYn + ", locationCode=" + locationCode + ", lodgingName=" + lodgingName
				+ ", address=" + address + ", couponIssuedNo=" + couponIssuedNo + ", couponYn=" + couponYn + ", payWay="
				+ payWay + ", lodgingPhone=" + lodgingPhone + "]";
	}

	


}
