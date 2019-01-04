package com.pbg.tpvbackend.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.pbg.tpvbackend.model.security.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
public class Restaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2309584542137543187L;

	@Getter
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Getter @Setter @NonNull
	@Column(length = 32, unique = false)
	private String name;
	
	@Getter @Setter @NonNull
	@Column(length = 64)
	private String address;
	
	@Getter @Setter @NonNull
	@ManyToOne(optional = false)
	private RestaurantChain chainRestaurant;

	@Getter @Setter
	@ManyToMany
	@JoinTable(
		name = "restaurant_workers",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "id")})
	private Set<User> workers = new HashSet<User>();
	
	@Getter @Setter
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	private Set<Zone> zones = new HashSet<Zone>();
	
	public Restaurant() {
		super();
	}
	
}
