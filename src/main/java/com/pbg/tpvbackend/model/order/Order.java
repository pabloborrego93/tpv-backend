package com.pbg.tpvbackend.model.order;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pbg.tpvbackend.model.Zone;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7221091527474882362L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Getter @Setter private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter @Setter private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter @Setter private Date closedAt;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter private OrderStatus status;
	
	@ManyToOne
	@Getter @Setter private Zone zone;
	
	@Getter @Setter private Double total;
	
	public Order() {
		super();
		this.status = OrderStatus.OPENED;
		this.createdAt = new Date();
	}
	
	@OneToMany(mappedBy = "orderObject", fetch = FetchType.EAGER)
	@Getter @Setter private Set<OrderLine> orderLines = new HashSet<OrderLine>();
	
}
