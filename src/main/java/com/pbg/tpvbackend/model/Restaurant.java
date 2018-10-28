package com.pbg.tpvbackend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	private List<User> workers = new ArrayList<User>();

	public Restaurant() {
		super();
	}
	
}
