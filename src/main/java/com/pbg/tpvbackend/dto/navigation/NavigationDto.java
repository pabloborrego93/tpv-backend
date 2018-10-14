package com.pbg.tpvbackend.dto.navigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NavigationDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6059964225759709107L;

	@Getter @Setter
	private String id;
	@Getter @Setter
	private String title;
	@Getter @Setter
	private String type;
	@Getter @Setter
	private String icon;
	@Getter @Setter
	private String url;
	@Getter @Setter
	private BadgeDto badge = new BadgeDto();
	@Getter @Setter
	private List<ChildrenDto> children = new ArrayList<ChildrenDto>();
	
}
