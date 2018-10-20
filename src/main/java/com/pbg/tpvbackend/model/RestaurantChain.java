package com.pbg.tpvbackend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pbg.tpvbackend.model.security.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
@NamedEntityGraph(
		name = "graph.RestaurantChain.restaurants", 
		attributeNodes = @NamedAttributeNode(value = "restaurants"))
public class RestaurantChain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3467676524496133083L;
	
	@Getter
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Getter @Setter @NonNull
	@Column(length = 16, unique = true)
	private String name;
	
	@Getter @Setter
	@OneToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@Getter @Setter
	@OneToMany(mappedBy = "restaurantChain")
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	public RestaurantChain() {
		super();
	}

	@Override
	public String toString() {
		return "RestaurantChain name" + name + "]";
	}

}
