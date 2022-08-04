package trip.dk.coupon;

public class CouponInfoVo {
	
	
	
	public CouponInfoVo() {
	}
	
	public CouponInfoVo(String no, String eventNo, String discountRate, String couponCode) {
		this.no = no;
		this.eventNo = eventNo;
		this.discountRate = discountRate;
		this.couponCode = couponCode;
	}


	private String no;
	private String eventNo;
	private String discountRate;
	private String couponCode;
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getEventNo() {
		return eventNo;
	}
	public void setEventNo(String eventNo) {
		this.eventNo = eventNo;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
	
	@Override
	public String toString() {
		return "CouponInfoVo [no=" + no + ", eventNo=" + eventNo + ", discountRate=" + discountRate + ", couponCode="
				+ couponCode + "]";
	}
	
	
	
	
	

}
