package aptech.dating.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	@NotEmpty(message = "Username can't be blank")
    @Size(min = 6, max = 50, message = "Username must be between 6 and 50 characters")
  private String username;

	@NotEmpty(message = "Password can't be blank")
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
