package com.pbg.tpvbackend.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pbg.tpvbackend.model.product.Product;
import com.pbg.tpvbackend.model.product.ProductFamily;
import com.pbg.tpvbackend.model.security.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
@NamedEntityGraphs({
	@NamedEntityGraph(name = "graph.RestaurantChain.allCollectionsFirstLevel", attributeNodes = {
		@NamedAttributeNode(value = "restaurants"),
		@NamedAttributeNode(value = "families"),
		@NamedAttributeNode(value = "products")
	})
})
@NoArgsConstructor
public class RestaurantChain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3467676524496133083L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Getter private Integer id;
	
	@Column(length = 16, unique = true)
	@Getter @Setter @NonNull private String name;
	
	@OneToOne
	@JoinColumn(name = "owner")
	@Getter @Setter private User owner;
	
	@OneToMany(mappedBy = "chainRestaurant")
	@Getter @Setter private List<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	@OneToMany(mappedBy = "chainProductFamily")
	@Getter @Setter private Set<ProductFamily> families = new HashSet<ProductFamily>();
	
	@OneToMany(mappedBy = "chainProduct")
	@Getter @Setter private Set<Product> products = new HashSet<Product>();
	
	@Override
	public String toString() {
		return "RestaurantChain name" + name + "]";
	}

}
