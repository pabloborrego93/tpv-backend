package com.pbg.tpvbackend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Zone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1301769390169480851L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	@Getter @Setter private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter private ZoneType zoneType;
	
	@Getter @Setter private String description;
	
	@ManyToOne
	@Getter @Setter private Restaurant restaurant;

	@Override
	public String toString() {
		return getDescription();
	}
	
}
