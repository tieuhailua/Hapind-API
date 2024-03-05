package aptech.dating.DTO;

public class UserSignUpDTO {
    private Integer id;
    private String email;
    private String password;

    public UserSignUpDTO(String email, String password) {
	super();
	this.email = email;
	this.password = password;
    }

    public UserSignUpDTO() {
	super();
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
