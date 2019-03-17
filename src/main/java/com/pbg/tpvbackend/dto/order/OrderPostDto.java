package com.pbg.tpvbackend.dto.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pbg.tpvbackend.dto.kitchen.KitchenProductPostDto;

import lombok.Getter;
import lombok.Setter;

public class OrderPostDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5848950095782972097L;

	@Getter @Setter private Integer id;
	@Getter @Setter private Integer amount;
	@Getter @Setter private String comment;
	@Getter @Setter private List<KitchenProductPostDto> kitchenProductPostDto = new ArrayList<>();
	
}
