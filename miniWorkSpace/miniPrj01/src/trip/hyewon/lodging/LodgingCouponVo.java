package trip.hyewon.lodging;

import java.sql.Timestamp;

public class LodgingCouponVo {
	
	public LodgingCouponVo() {
		
	}
	
	public LodgingCouponVo(int couponIssuedNo, int couponInfoNo, String usedYn, Timestamp usedDate, int eventNo,
			String discount, String couponCode) {
		super();
		this.couponIssuedNo = couponIssuedNo;
		this.couponInfoNo = couponInfoNo;
		this.usedYn = usedYn;
		this.usedDate = usedDate;
		this.eventNo = eventNo;
		this.discount = discount;
		this.couponCode = couponCode;
	}
	private int couponIssuedNo;
	private int couponInfoNo;
	private String usedYn;
	private Timestamp usedDate;
	private int eventNo;
	private String discount;
	private String couponCode;
	public int getCouponIssuedNo() {
		return couponIssuedNo;
	}
	public void setCouponIssuedNo(int couponIssuedNo) {
		this.couponIssuedNo = couponIssuedNo;
	}
	public int getCouponInfoNo() {
		return couponInfoNo;
	}
	public void setCouponInfoNo(int couponInfoNo) {
		this.couponInfoNo = couponInfoNo;
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
	public int getEventNo() {
		return eventNo;
	}
	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	@Override
	public String toString() {
		return "LodgingCouponVo [couponIssuedNo=" + couponIssuedNo + ", couponInfoNo=" + couponInfoNo + ", usedYn="
				+ usedYn + ", usedDate=" + usedDate + ", eventNo=" + eventNo + ", discount=" + discount
				+ ", couponCode=" + couponCode + "]";
	}
	
	

}
