package trip.daeun.lodging.wish;

public class WishVo {

	public WishVo() {

	}
	
	
	public WishVo(String wishNo, String memberNo, String lodgingNo, String cancelYn) {
		this.wishNo = wishNo;
		this.memberNo = memberNo;
		this.lodgingNo = lodgingNo;
		this.cancelYn = cancelYn;
	}

	private String input;
	private String wishNo;	
	private String memberNo;
	private String lodgingNo;
	private String cancelYn;
	public String getWishNo() {
		return wishNo;
	}


	
	

	public String getInput() {
		return input;
	}


	public void setInput(String input) {
		this.input = input;
	}


	public void setWishNo(String wishNo) {
		this.wishNo = wishNo;
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



	public String getCancelYn() {
		return cancelYn;
	}



	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}



	@Override
	public String toString() {
		return "WishVo [wishNo=" + wishNo + ", memberNo=" + memberNo + ", lodgingNo=" + lodgingNo + ", cancelYn="
				+ cancelYn + "]";
	}
	

}
