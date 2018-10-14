package com.pbg.tpvbackend.dto.navigation;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BadgeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6794632494003787771L;

	@Getter @Setter
	private String title;
	@Getter @Setter
	private String bg;
	@Getter @Setter
	private String fg;
	
}
