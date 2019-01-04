package com.pbg.tpvbackend.model.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.RestaurantChain;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedEntityGraph(
	name = "graph.User.basicInfo", 
	attributeNodes = { 
		@NamedAttributeNode(value = "roles"),
		@NamedAttributeNode(value = "worksIn"), 
		@NamedAttributeNode(value = "chain") 
})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4570970229599254928L;

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Getter
	@Setter
	@Column(length = 16, unique = true, nullable = false)
	private String username;

	@Getter
	@Setter
	@Column(length = 128, nullable = false)
	private String password;

	@Getter
	@Setter
	@Column(length = 16)
	private String firstname;

	@Getter
	@Setter
	@Column(length = 32)
	private String lastname;

	@Getter
	@Setter
	@Column(length = 32)
	private String email;

	@Getter
	@Setter
	private Boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@Getter
	@Setter
	private Date lastPasswordResetDate;

	@Getter
	@Setter
	@ManyToMany(mappedBy = "workers", cascade = { CascadeType.MERGE })
	private List<Restaurant> worksIn;

	@Getter
	@Setter
	@ManyToMany
	private Set<Role> roles;

	@Getter
	@Setter
	@OneToOne(mappedBy = "owner")
	private RestaurantChain chainOwned;
	
	@Getter
	@Setter
	@ManyToOne
	private RestaurantChain chain;

	public User() {
		super();
		this.roles = new HashSet<Role>();
	}

	public User(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.enabled = user.getEnabled();
		this.lastPasswordResetDate = user.getLastPasswordResetDate();
		this.chain = user.getChain();
		this.chainOwned = user.getChainOwned();
		this.worksIn = user.getWorksIn();
		this.roles = user.getRoles();
	}

	@Override
	public String toString() {
		return String.format("%s, %s", this.firstname, this.lastname);
	}

}
