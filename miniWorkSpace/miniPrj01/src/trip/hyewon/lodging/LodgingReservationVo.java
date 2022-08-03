package trip.hyewon.lodging;

public class LodgingReservationVo {
	
	public LodgingReservationVo() {
		
	}
	
	

	public LodgingReservationVo(String no, String memberNo, String lodgingNo, String roomNo, String reserveDate,
			String people, String startDate, String endDate, String payYn, String payment, String breakfastYn,
			String cancelYn, String locationCode, String lodgingName, String address) {
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
	}



	private String no;
	private String memberNo;
	private String lodgingNo;
	private String roomNo;
	private String reserveDate;
	private String people;
	private String startDate;
	private String endDate;
	private String payYn;
	private String payment;
	private String breakfastYn;
	private String cancelYn;
	private String locationCode;
	private String lodgingName;
	private String address;
	
	
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
				+ ", address=" + address + "]";
	}

	
	
	
}
