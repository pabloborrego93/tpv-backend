package com.pbg.tpvbackend.model.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.pbg.tpvbackend.model.RestaurantChain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PRODUCT_TYPE")
@NoArgsConstructor
@ToString
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3231565756506668280L;

	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@Getter private Integer id;
	
	@Column(length = 32)
	@Getter @Setter private String name;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Getter @Setter private String image;
	
	@Getter @Setter private Boolean catalogable;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@Getter @Setter private Set<ProductFamily> families = new HashSet<ProductFamily>();
	
	@Getter @Setter @NonNull
	@ManyToOne(optional = false)
	private RestaurantChain chainProduct;

}
