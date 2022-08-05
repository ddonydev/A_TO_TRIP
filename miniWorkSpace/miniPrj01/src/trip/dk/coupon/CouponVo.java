package trip.dk.coupon;

import java.sql.Timestamp;

public class CouponVo {
	
	
	
	public CouponVo() {
	}
	
	
	public CouponVo(String couponIssuedNo, String couponInfoNo, String memberNo, String usedYn, Timestamp usedDate,
			String eventNo, String discountRate, String couponCode) {
		this.couponIssuedNo = couponIssuedNo;
		this.couponInfoNo = couponInfoNo;
		this.memberNo = memberNo;
		this.usedYn = usedYn;
		this.usedDate = usedDate;
		this.eventNo = eventNo;
		this.discountRate = discountRate;
		this.couponCode = couponCode;
	}
	
	
	private String couponIssuedNo;
	private String couponInfoNo;
	private String memberNo;
	private String usedYn;
	private Timestamp usedDate;
	private String eventNo;
	private String discountRate;
	private String couponCode;
	
	
	public String getCouponIssuedNo() {
		return couponIssuedNo;
	}
	public void setCouponIssuedNo(String couponIssuedNo) {
		this.couponIssuedNo = couponIssuedNo;
	}
	public String getCouponInfoNo() {
		return couponInfoNo;
	}
	public void setCouponInfoNo(String couponInfoNo) {
		this.couponInfoNo = couponInfoNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getUsedYn() {
		return usedYn;
	}
	public void setUsedYn(String usedYn) {
		this.usedYn = usedYn;
	}
	public Timestamp getUsedDate() {
		return usedDate;
	}
	public void setUsedDate(Timestamp usedDate) {
		this.usedDate = usedDate;
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
		return "CouponVo [couponIssuedNo=" + couponIssuedNo + ", couponInfoNo=" + couponInfoNo + ", memberNo="
				+ memberNo + ", usedYn=" + usedYn + ", usedDate=" + usedDate + ", eventNo=" + eventNo
				+ ", discountRate=" + discountRate + ", couponCode=" + couponCode + "]";
	}
	
	
	

}
