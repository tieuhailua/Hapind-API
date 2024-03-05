package aptech.dating.DTO;

import java.util.Date;

public class UserRegisterDTO {
	private Date lastLogin;
	private int purpose;
	private int status;
	private String email;
	private String phone;
	private String password;
	private String fullname;
	private Date dob;
	private String gender;
	private String finding;
	private int distance;
	private String address;
	private Date createTime;
	
	public UserRegisterDTO() {
		super();
	}
	
	public UserRegisterDTO(Date lastLogin, int purpose, int status, String email, String phone, String password,
			String fullname, Date dob, String gender, String finding, int distance, String address, Date createTime) {
		super();
		this.lastLogin = lastLogin;
		this.purpose = purpose;
		this.status = status;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.fullname = fullname;
		this.dob = dob;
		this.gender = gender;
		this.finding = finding;
		this.distance = distance;
		this.address = address;
		this.createTime = createTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public int getPurpose() {
		return purpose;
	}
	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFinding() {
		return finding;
	}
	public void setFinding(String finding) {
		this.finding = finding;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
