package member;

public class MemberVo {
	private String id;
	private String pw;
	private String gender;
	private String job;
	private String reason;
	private String mailyn;
	private String hobby;
	private String regdate;
	
	
	public MemberVo() {}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMailyn() {
		return mailyn;
	}

	public void setMailyn(String mailyn) {
		this.mailyn = mailyn;
	}
	

	public String getHobby() {
		return hobby;
	}



	public void setHobby(String hobby) {
		this.hobby = hobby;
	}



	public String getRegdate() {
		return regdate;
	}



	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}



	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", pw=" + pw + ", gender=" + gender + ", job=" + job + ", reason=" + reason
				+ ", mailyn=" + mailyn + "]";
	}
	
	
	
}
