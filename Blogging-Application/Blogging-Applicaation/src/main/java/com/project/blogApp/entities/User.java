package com.project.blogApp.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User implements UserDetails{

		@Id
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		private long user_id;
		@Column(name="name",nullable = false, length = 100)
		private String user_name;
		@Column(name="email", nullable = false)
		private String email;
		@Column(name="password", nullable = false)
		private String user_password;
		private String about;

		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private List<Post> post;
		
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
		@JoinTable(name = "user_role",
				joinColumns =  @JoinColumn(name = "user", referencedColumnName = "user_id"),
				inverseJoinColumns = @JoinColumn(name = " role", referencedColumnName = "id"))
		private Set<Role>  roles = new HashSet<>();

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			List<SimpleGrantedAuthority> simpleGrantedAuthorities = 	this.getRoles().stream().map(
					role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

			return simpleGrantedAuthorities;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return getUser_password();
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

	
}
