package com.pbg.tpvbackend.dto.order;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.pbg.tpvbackend.dto.zone.ZoneDto;
import com.pbg.tpvbackend.model.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7934304522600782507L;

	@Getter @Setter private Integer id;
	
	@Getter @Setter private Date createdAt;
	
	@Getter @Setter private Date closedAt;
	
	@Getter @Setter private OrderStatus status;
	
	@Getter @Setter private ZoneDto zone;
	
	@Getter @Setter private Set<OrderLineDto> orderLines;
	
}
