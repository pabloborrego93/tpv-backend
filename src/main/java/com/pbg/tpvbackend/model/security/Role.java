package com.pbg.tpvbackend.model.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 165143835352267665L;

	@Getter
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Getter @Setter @NonNull
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private RoleName name;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users;
	
	public Role() {
		super();
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
