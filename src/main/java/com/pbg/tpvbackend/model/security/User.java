package com.pbg.tpvbackend.model.security;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pbg.tpvbackend.model.Restaurant;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedEntityGraph(
	name = "graph.User.roles", 
	attributeNodes = @NamedAttributeNode(value = "roles"))
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4570970229599254928L;

	@Getter
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Getter @Setter
	@Column(length = 16, unique = true, nullable = false)
	private String username;

	@Getter @Setter
	@Column(length = 128, nullable = false)
	private String password;

	@Getter @Setter
	@Column(length = 16)
	private String firstname;

	@Getter @Setter
	@Column(length = 32)
	private String lastname;

	@Getter @Setter
	@Column(length = 32)
	private String email;
	
	@Getter @Setter
    private Boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date lastPasswordResetDate;
	
	@Getter @Setter
	@ManyToMany
	private List<Restaurant> worksIn = new ArrayList<Restaurant>();

	@Getter @Setter
	@ManyToMany
	private List<Role> roles = new ArrayList<Role>();
	
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", this.firstname, this.lastname);
	}

}
