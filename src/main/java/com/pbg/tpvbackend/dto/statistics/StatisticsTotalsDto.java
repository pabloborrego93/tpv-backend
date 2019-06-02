package com.pbg.tpvbackend.dto.statistics;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class StatisticsTotalsDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6087062069798123649L;
	
	@Getter @Setter private String name;
	@Getter @Setter private Double value;

	public StatisticsTotalsDto() {
		super();
	}

	public StatisticsTotalsDto(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public StatisticsTotalsDto(String name, Long value) {
		super();
		this.name = name;
		this.value = value.doubleValue();
	}

}
