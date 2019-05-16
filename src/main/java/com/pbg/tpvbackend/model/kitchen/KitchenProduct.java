package com.pbg.tpvbackend.model.kitchen;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.model.Zone;
import com.pbg.tpvbackend.model.order.OrderLine;
import com.pbg.tpvbackend.model.product.Product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
public class KitchenProduct {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Getter private Integer id;
	
	@Getter @Setter private String comment;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter private KitchenProductStatus status;
	
	@ManyToOne
	@Getter @Setter private OrderLine orderLine;
	
	@ManyToOne
	@Getter @Setter private Zone zone;
	
	@ManyToOne
	@Getter @Setter private Product product;
	
	@ManyToOne
	@Getter @Setter private Restaurant restaurant;
	
}
