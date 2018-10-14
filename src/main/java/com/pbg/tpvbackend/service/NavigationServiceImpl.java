package com.pbg.tpvbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dto.navigation.ChildrenDto;
import com.pbg.tpvbackend.dto.navigation.NavigationDto;
import com.pbg.tpvbackend.dto.navigation.NavigationType;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.RoleName;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.security.UserDataService;
import com.pbg.tpvbackend.utils.AppConstants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NavigationServiceImpl implements NavigationService {
	
	private static final Logger logger = LogManager.getLogger(NavigationServiceImpl.class);
	
	private UserDataService userDataService;
	private UserService userService;
	
	@Override
	public List<NavigationDto> getNavigation() throws UserNotFoundException {
		List<NavigationDto> navigationList = new ArrayList<NavigationDto>();
		
		NavigationDto homeDto = NavigationDto
			.builder()
			.id("inicio")
			.title("Inicio")
			.type(NavigationType.ITEM.getValue())
			.icon("home")
			.url("/admin")
			.build();
		
		NavigationDto miPerfilDto = NavigationDto
				.builder()
				.id("miperfil")
				.title("Mi Perfil")
				.type(NavigationType.ITEM.getValue())
				.icon("home")
				.url("/admin/miperfil")
				.build();
		
		navigationList.add(homeDto);
		navigationList.add(miPerfilDto);
		
		if(userDataService.hasRole(RoleName.ROLE_RESTAURANT_CHAIN_ADMIN)) {
			try {
				navigationList.add(getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN());
			} catch(UserWithoutRestaurantChain e) {
				logger.error(AppConstants.getERR_USER_WITHOUT_RESTAURANT_CHAIN());
			}
		}
		if(userDataService.hasRole(RoleName.ROLE_RESTAURANT_ADMIN)) {
			navigationList.add(getNavigation_ROLE_RESTAURANT_ADMIN());
		}
		
		return navigationList;
	}

	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN() throws UserNotFoundException, UserWithoutRestaurantChain {
		User user = userService.findByUsername();
		RestaurantChain restaurantChain = user.getRestaurantChain();
		if(restaurantChain != null) {
			List<ChildrenDto> children = new ArrayList<ChildrenDto>();
			
			ChildrenDto updateRestaurantChain = ChildrenDto
				.builder()
				.id("update")
				.title(restaurantChain.getName())
				.type(NavigationType.COLLAPSE.getValue())
				.url("/restaurantChain/update")
				.build();
			
			children.add(updateRestaurantChain);
			
			return NavigationDto
				.builder()
				.children(children)
				.build();
		} else 
			throw new UserWithoutRestaurantChain();
	}

	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_ADMIN() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
