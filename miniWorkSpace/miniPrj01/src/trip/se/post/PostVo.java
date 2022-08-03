package trip.se.post;

import java.sql.Timestamp;

public class PostVo {

	// 게시글 번호, 게시글 제목, 게시글 내용, 작성자(회원번호), 작성날짜, 삭제여부, 수정시간, 좋아요 개수, 조회수
	
	public PostVo() {
		
	}
	
	public PostVo(int no, String title, String content, int writer, Timestamp date, String deleteYN, int like,
			Timestamp editDate, int viewCount) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.date = date;
		this.deleteYN = deleteYN;
		this.like = like;
		this.editDate = editDate;
		this.viewCount = viewCount;
	}
	
	private int no;
	private String title;
	private String content;
	private int writer;
	private Timestamp date;
	private String deleteYN;
	private int like;
	private Timestamp editDate;
	private int viewCount;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public Timestamp getEditDate() {
		return editDate;
	}
	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", date="
				+ date + ", deleteYN=" + deleteYN + ", like=" + like + ", editDate=" + editDate + ", viewCount="
				+ viewCount + "]";
	}
	
	
	
}
