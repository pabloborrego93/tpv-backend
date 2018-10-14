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
public class ChildrenDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6244408617743124265L;
	
	@Getter @Setter
	private String id;
	@Getter @Setter
	private String title;
	@Getter @Setter
	private String type;
	@Getter @Setter
	private String url;
	
}
