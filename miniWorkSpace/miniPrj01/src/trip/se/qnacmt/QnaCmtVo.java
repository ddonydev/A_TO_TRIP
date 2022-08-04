package trip.se.qnacmt;

import java.sql.Timestamp;

public class QnaCmtVo {
	
	public QnaCmtVo() {
		
	}
	
	public QnaCmtVo(String cmtNo, String qnaNo, String writer, String nick, String cmt, Timestamp date,
			Timestamp editdate) {
		super();
		this.cmtNo = cmtNo;
		this.qnaNo = qnaNo;
		this.writer = writer;
		this.nick = nick;
		this.cmt = cmt;
		this.date = date;
		this.editdate = editdate;
	}
	
	private String cmtNo;
	private String qnaNo;
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

	public String getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
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
		return "QnaCmtVo [cmtNo=" + cmtNo + ", qnaNo=" + qnaNo + ", writer=" + writer + ", nick=" + nick + ", cmt="
				+ cmt + ", date=" + date + ", editdate=" + editdate + "]";
	}

	
}
