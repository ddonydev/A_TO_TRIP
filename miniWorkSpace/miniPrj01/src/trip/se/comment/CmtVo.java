package trip.se.comment;

import java.sql.Timestamp;

public class CmtVo {

	public CmtVo() {
		
	}
	
	public CmtVo(String cmtNo, String postNo, String writer, String nick, String cmt, Timestamp date, Timestamp editdate) {
		super();
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.writer = writer;
		this.nick = nick;
		this.cmt = cmt;
		this.date = date;
		this.editdate = editdate;
	}
	
	private String cmtNo;
	private String postNo;
	private String writer;
	private String nick;
	private String cmt;
	private Timestamp date;
	private Timestamp editdate;
	
	public String getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(String cmtNo) {
		this.cmtNo = cmtNo;
	}
	public String getPostNo() {
		return postNo;
	}
	public void setPostNo(String postNo) {
		this.postNo = postNo;
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
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Timestamp getEditdate() {
		return editdate;
	}
	public void setEditdate(Timestamp editdate) {
		this.editdate = editdate;
	}
	@Override
	public String toString() {
		return "CmtVo [cmtNo=" + cmtNo + ", postNo=" + postNo + ", writer=" + writer + ", nick=" + nick + ", cmt=" + cmt
				+ ", date=" + date + ", editdate=" + editdate + "]";
	}
	
	
	
}
