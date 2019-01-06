package com.pbg.tpvbackend.model.order;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.pbg.tpvbackend.model.product.Product;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "orderlines")
public class OrderLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2394408194092400146L;

	@EmbeddedId
	@Getter @Setter private OrderLinePK id;
	
	@Getter @Setter private Integer amount;

	@MapsId("orderId")
    @JoinColumn(name="order_id")
	@ManyToOne
	@Getter @Setter private Order orderObject;
	
	@MapsId("productId")
    @JoinColumn(name="product_id")
	@ManyToOne
	@Getter @Setter private Product product;
	
	@Getter @Setter private Double total;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter private OrderLineStatus status;
	
	public OrderLine() {
		super();
	}

	public OrderLine(Order orderObject, Product product, Integer amount) {
		super();
		this.orderObject = orderObject;
		this.product = product;
		this.id = new OrderLinePK(orderObject.getId(), product.getId());
		this.amount = amount;
		calculateTotal();
	}
	
	public OrderLine(Order orderObject, Product product) {
		super();
		this.orderObject = orderObject;
		this.product = product;
		this.id = new OrderLinePK(orderObject.getId(), product.getId());
		calculateTotal();
	}

	public void calculateTotal() {
		this.total = (product.getPrice() * (1 + product.getIva().getIVA()) ) * amount;
	}

}
