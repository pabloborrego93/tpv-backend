package com.pbg.tpvbackend.model.product;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(ProductType.Values.SIMPLE)
public class ProductSimple extends Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3744067681104885037L;
	
}
