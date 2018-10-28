package com.pbg.tpvbackend.model.product;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("Composite")
@NoArgsConstructor
@ToString
public class ProductComposite extends Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7221091527474882362L;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="product_composite",
	    joinColumns={ @JoinColumn(name="product_composite_id", referencedColumnName="id") },
	    inverseJoinColumns={ @JoinColumn(name="product_id", referencedColumnName="id") }
	)
	@Getter @Setter private Set<Product> products = new HashSet<Product>();
	
}
