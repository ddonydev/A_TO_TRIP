package trip.dk.coupon;

import java.sql.Timestamp;

public class CouponIssuedVo {
	
	
	
	public CouponIssuedVo() {
	}
	
	
	public CouponIssuedVo(String no, String couponInfoNo, String memberNo, String usedYn, Timestamp usedDate) {
		this.no = no;
		this.couponInfoNo = couponInfoNo;
		this.memberNo = memberNo;
		this.usedYn = usedYn;
		this.usedDate = usedDate;
	}

	private String no;
	private String couponInfoNo;
	private String memberNo;
	private String usedYn;
	private Timestamp usedDate;
	
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	
	@Override
	public String toString() {
		return "CouponIssuedVo [no=" + no + ", couponInfoNo=" + couponInfoNo + ", memberNo=" + memberNo + ", usedYn="
				+ usedYn + ", usedDate=" + usedDate + "]";
	}
	
	
	
	


}
