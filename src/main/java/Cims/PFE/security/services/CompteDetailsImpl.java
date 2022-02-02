package Cims.PFE.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.Compte;

public class CompteDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	//private String email;
	private Personnel personnel;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public CompteDetailsImpl(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		//this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	public CompteDetailsImpl(Long id, String username, String password,Personnel p,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		//this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.personnel=p;
	}

	public static CompteDetailsImpl build(Compte compte) {
		List<GrantedAuthority> authorities = compte.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new CompteDetailsImpl(
				compte.getId(), 
				compte.getUsername(), 
				//user.getEmail(),
				compte.getPassword(), 
				compte.getPersonnel(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	
	@Override
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	
	

	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CompteDetailsImpl user = (CompteDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
