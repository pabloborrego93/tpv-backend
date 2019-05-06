package com.pbg.tpvbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@EqualsAndHashCode(of="id")
public class Printer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5960187636688623752L;

	@Getter
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Getter @Setter private String name;
	
	@Getter @Setter private String printerid;
	
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	@Getter @Setter private Boolean defaultPrinter;
	
	@ManyToOne
	@Getter @Setter private Restaurant restaurantOwner;
	
}
