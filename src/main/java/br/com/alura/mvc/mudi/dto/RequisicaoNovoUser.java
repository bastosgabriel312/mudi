package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.mvc.mudi.model.Authority;
import br.com.alura.mvc.mudi.model.User;

public class RequisicaoNovoUser {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private String enabled;

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
		this.password = new BCryptPasswordEncoder().encode(password);;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public User toUser() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEnabled(true);
		user.addAuthority(new Authority("USER"));
		return user;
	}

}
