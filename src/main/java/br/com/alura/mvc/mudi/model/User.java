package br.com.alura.mvc.mudi.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="users")
@Entity
public class User {
	@Id
	private String username;
	private String password;
	private Boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	List<Pedido> pedidos;
	

    @ElementCollection
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username"))
    private Set<Authority> authorities = new HashSet<>();
    
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
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enable) {
		this.enabled = enable;
	}
	
	public Set<Authority> getAuthorities() {
		return authorities;
	}
	public void addAuthority(Authority authority ) {
		this.authorities.add(authority);
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", pedidos=" + pedidos
				+ "]";
	}
	
}
