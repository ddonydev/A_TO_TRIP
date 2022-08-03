package trip.kms.lodgingReviewLike;

public class LodgingReviewLikeVo {

	//Field
	private int no;
	private int lodgingReviewNo;
	private int memberNo;
	private char cancelYn;
	
	//constructor
	public LodgingReviewLikeVo() {
		
	}
	public LodgingReviewLikeVo(int no, int lodgingReviewNo, int memberNo, char cancelYn) {
		this.no = no;
		this.lodgingReviewNo = lodgingReviewNo;
		this.memberNo = memberNo;
		this.cancelYn = cancelYn;
	}
	
	//getter, setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getLodgingReviewNo() {
		return lodgingReviewNo;
	}
	public void setLodgingReviewNo(int lodgingReviewNo) {
		this.lodgingReviewNo = lodgingReviewNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public char getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(char cancelYn) {
		this.cancelYn = cancelYn;
	}
	
	//toString
	@Override
	public String toString() {
		return "LodgingReviewLikeVo [ no=" + no + ", lodgingReviewNo=" + lodgingReviewNo + ", memberNo=" + memberNo
				+ ", cancelYn=" + cancelYn + " ]";
	}
}
