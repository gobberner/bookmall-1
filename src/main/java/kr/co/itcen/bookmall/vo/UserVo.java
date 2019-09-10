package kr.co.itcen.bookmall.vo;

public class UserVo {
	private long no;
	private String name;
	private String contact;
	private String email;
	private String passwd;

	public UserVo(String name, String contact, String email, String passwd) {
		this(0L, name, contact, email, passwd);
	}
	
	public UserVo(long no, String name, String contact, String email, String passwd) {
		this.no = no;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.passwd = passwd;
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", contact=" + contact + ", email=" + email + ", passwd="
				+ passwd + "]";
	}
	
	
}
