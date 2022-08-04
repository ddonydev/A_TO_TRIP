package trip.kms.lodgingReview;

import java.sql.Timestamp;

public class LodgingReviewVo {

	//field
	private int no;
	private int writer;
	private int lodgingNo;
	private String title;
	private String content;
	private Timestamp reviewDate;
	private char reviewDelete;
	private Timestamp editDate;
	private int reviewLike;
	
	
	//constructor
	public LodgingReviewVo() {
		
	}
	public LodgingReviewVo(int no, int writer, int lodgingNo, String title, String content, Timestamp reviewDate,
			char reviewDelete, Timestamp editDate, int reviewLike) {
		this.no = no;
		this.writer = writer;
		this.lodgingNo = lodgingNo;
		this.title = title;
		this.content = content;
		this.reviewDate = reviewDate;
		this.reviewDelete = reviewDelete;
		this.editDate = editDate;
		this.reviewLike = reviewLike;
	}
	
	//getter, setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public int getLodgingNo() {
		return lodgingNo;
	}
	public void setLodgingNo(int lodgingNo) {
		this.lodgingNo = lodgingNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Timestamp reviewDate) {
		this.reviewDate = reviewDate;
	}
	public char getReviewDelete() {
		return reviewDelete;
	}
	public void setReviewDelete(char reviewDelete) {
		this.reviewDelete = reviewDelete;
	}
	public Timestamp getEditDate() {
		return editDate;
	}
	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}
	public int getReviewLike() {
		return reviewLike;
	}
	public void setReviewLike(int reviewLike) {
		this.reviewLike = reviewLike;
	}
	
	//toString
	@Override
	public String toString() {
		return "LodgingReviewVo [ no=" + no + ", writer=" + writer + ", lodgingNo=" + lodgingNo + ", title=" + title
				+ ", content=" + content + ", reviewDate=" + reviewDate + ", reviewDelete=" + reviewDelete
				+ ", editDate=" + editDate + ", reviewLike=" + reviewLike + " ]";
	}
}
