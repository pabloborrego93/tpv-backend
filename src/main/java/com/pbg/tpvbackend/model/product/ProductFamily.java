package com.pbg.tpvbackend.model.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.pbg.tpvbackend.model.RestaurantChain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class ProductFamily implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4978599747978473141L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@Getter @Setter private Integer id;
	
	@Getter @Setter private String name;
	
	@Getter @Setter @NonNull
	@ManyToOne(optional = false)
	private RestaurantChain chainProductFamily;
	
}
