package trip.se.qna;

import java.sql.Timestamp;

public class QnaVo {

	public QnaVo() {
		
	}
	
	public QnaVo(String no, String writer, String title, String content, String nick, Timestamp date, String deleteYN,
			Timestamp editDate) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.nick = nick;
		this.date = date;
		this.deleteYN = deleteYN;
		this.editDate = editDate;
	}
	private String no;
	private String writer;
	private String title;
	private String content;
	private String nick;
	private Timestamp date;
	private String deleteYN;
	private Timestamp editDate;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public Timestamp getEditDate() {
		return editDate;
	}

	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}

	@Override
	public String toString() {
		return "QnaVo [no=" + no + ", writer=" + writer + ", title=" + title + ", content=" + content + ", nick=" + nick
				+ ", date=" + date + ", deleteYN=" + deleteYN + ", editDate=" + editDate + "]";
	}
	
	
	
}
