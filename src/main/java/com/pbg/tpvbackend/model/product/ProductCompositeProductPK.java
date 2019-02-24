package com.pbg.tpvbackend.model.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProductCompositeProductPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8168170200958950482L;
	
	@Column(name = "product_composite_id")
	@Getter @Setter private Integer productCompositeId;
	
	@Column(name = "product_id")
	@Getter @Setter private Integer productId;
	
}
