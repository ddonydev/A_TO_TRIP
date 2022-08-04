package trip.se.post;

import java.sql.Timestamp;

public class PostVo {

	// 게시글 번호, 게시글 제목, 게시글 내용, 작성자(회원번호), 작성날짜, 삭제여부, 수정시간, 좋아요 개수, 조회수
	
	public PostVo() {
		
	}
	
	public PostVo(String no, String title, String content, String writer, String nick, Timestamp date, String deleteYN,
			String like, Timestamp editDate, String viewCount) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.nick = nick;
		this.date = date;
		this.deleteYN = deleteYN;
		this.like = like;
		this.editDate = editDate;
		this.viewCount = viewCount;
	}

	private String no;
	private String title;
	private String content;
	private String writer;
	private String nick;
	private Timestamp date;
	private String deleteYN;
	private String like;
	private Timestamp editDate;
	private String viewCount;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public Timestamp getEditDate() {
		return editDate;
	}
	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", nick="
				+ nick + ", date=" + date + ", deleteYN=" + deleteYN + ", like=" + like + ", editDate=" + editDate
				+ ", viewCount=" + viewCount + "]";
	}
	
}