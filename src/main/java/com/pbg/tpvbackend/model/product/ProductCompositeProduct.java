package com.pbg.tpvbackend.model.product;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity
@ToString
public class ProductCompositeProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 627265556822041045L;

	@EmbeddedId
	@Getter @Setter private ProductCompositeProductPK id;
	
	@MapsId("productCompositeId")
    @JoinColumn(name="product_composite_id")
	@ManyToOne
	@Getter @Setter private ProductComposite productComposite;
	
	@MapsId("productId")
    @JoinColumn(name="product_id")
	@ManyToOne
	@Getter @Setter private Product product;
	@Getter @Setter private Integer amount;
	
	public ProductCompositeProduct() {
		super();
		this.id = new ProductCompositeProductPK();
	}

	public ProductCompositeProduct(ProductComposite productComposite, Product product,
			Integer amount) {
		super();
		this.id = new ProductCompositeProductPK(productComposite.getId(), product.getId());
		this.productComposite = productComposite;
		this.product = product;
		this.amount = amount;
	}

}
