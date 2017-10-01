package com.pbg.tpvBackEnd.model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.pbg.tpvBackEnd.model.Restaurant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4570970229599254928L;

	@Getter
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Getter @Setter @NonNull
	@Column(length = 16, unique = true)
	private String username;

	@Getter @Setter @NonNull
	@Column(length = 128)
	private String password;

	@Getter @Setter @NonNull
	@Column(length = 16)
	private String firstname;

	@Getter @Setter @NonNull
	@Column(length = 32)
	private String lastname;

	@Getter @Setter @NonNull
	@Column(length = 32)
	private String email;
	
	@ManyToMany
	private List<Restaurant> worksIn = new ArrayList<Restaurant>();

	@ManyToMany
	private Set<Role> roles = new HashSet<Role>();
	
	public User() {
		super();
	}

	@Override
	public String toString() {
		return firstname + ", " + lastname;
	}

}
