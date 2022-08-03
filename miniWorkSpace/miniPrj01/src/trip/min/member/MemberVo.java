package trip.min.member;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberVo {

	public MemberVo() {
		
	}
	
	public MemberVo(String no, String id, String pwd, String email, String name, String birth, String phone,
			String nick) {
		this.no = no;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.nick = nick;
	}
	private String no;
	private String id;
	private String id2;
	private String pwd;
	private String pwd2;
	private String email;
	private String name;
	private String birth;
	private String phone;
	private String nick;
	
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	@Override
	public String toString() {
		return "MemberVo [no=" + no + ", id=" + id + ", pwd=" + pwd + ", email=" + email + ", name=" + name + ", birth="
				+ birth + ", phone=" + phone + ", nick=" + nick + "]";
	}
	
	
	
	
}
