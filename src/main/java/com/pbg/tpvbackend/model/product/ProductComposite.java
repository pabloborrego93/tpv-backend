package com.pbg.tpvbackend.model.product;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue(ProductType.Values.COMPOSITE)
@NoArgsConstructor
@ToString
public class ProductComposite extends Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7221091527474882362L;

	@ElementCollection
	@MapKeyColumn(name = "product_id", nullable = true, length = 255)
	@Column(name = "amount", length = 100)
	@Getter @Setter private Map<Product, Integer> products = new HashMap<>();
	
}
