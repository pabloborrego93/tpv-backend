package com.pbg.tpvbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pbg.tpvbackend.dto.navigation.BadgeDto;
import com.pbg.tpvbackend.dto.navigation.ChildrenDto;
import com.pbg.tpvbackend.dto.navigation.NavigationDto;
import com.pbg.tpvbackend.dto.navigation.NavigationType;
import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.chain.ChainWithoutProductFamiliesException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurants;
import com.pbg.tpvbackend.model.RestaurantChain;
import com.pbg.tpvbackend.model.security.RoleName;
import com.pbg.tpvbackend.model.security.User;
import com.pbg.tpvbackend.service.NavigationService;
import com.pbg.tpvbackend.service.RestaurantChainService;
import com.pbg.tpvbackend.service.UserService;
import com.pbg.tpvbackend.service.security.UserDataService;
import com.pbg.tpvbackend.utils.AppConstants;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NavigationServiceImpl implements NavigationService {
	
	private static final Logger logger = LogManager.getLogger(NavigationServiceImpl.class);
	
	UserDataService userDataService;
	UserService userService;
	RestaurantChainService chainService;
	
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

		navigationList.add(homeDto);
		
		if(userDataService.hasRole(RoleName.ROLE_RESTAURANT_CHAIN_ADMIN)) {
			// Has CHAIN_ADMIN role
			try {
				// 1: Adding chain to navigation
				navigationList.add(getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN());
				try {
					// 1.1: Adding restaurants to navigation
					navigationList.add(getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS());
				} catch(UserWithoutRestaurants e) {
					// Catch 1.1: Dont have restaurants configured
					String errorMsg = String.format(AppConstants.getERR_USER_WITHOUT_RESTAURANTS(), userDataService.getUsername());
					logger.error(errorMsg);
					navigationList.add(getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS_NOT_CREATED());
				}
				try {
					// 1.2: Adding product families to navigation
					navigationList.add(getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCT_FAMILIES());
				} catch(ChainWithoutProductFamiliesException e) {
					// Catch 1.2: Dont have product families configured
					String errorMsg = String.format(AppConstants.getERR_USER_WITHOUT_PRODUCT_FAMILIES(), userDataService.getUsername());
					logger.error(errorMsg);
					navigationList.add(getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCT_FAMILIES_NOT_CREATED());
				}
			} catch(UserWithoutRestaurantChain e) {
				// Catch 1: Dont have chain configured
				String errorMsg = String.format(AppConstants.getERR_USER_WITHOUT_RESTAURANT_CHAIN(), userDataService.getUsername());
				logger.error(errorMsg);
				navigationList.add(getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_NOT_CREATED());
			}
			return navigationList;
		}
		if(userDataService.hasRole(RoleName.ROLE_RESTAURANT_ADMIN)) {
			navigationList.add(getNavigation_ROLE_RESTAURANT_ADMIN());
		}
		
		return navigationList;
	}

	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN() throws UserNotFoundException, UserWithoutRestaurantChain {
		User user = userService.findByUsername();
		RestaurantChain restaurantChain = user.getChain();
		if(restaurantChain != null) {
			return NavigationDto
				.builder()
				.id("chain")
				.title(restaurantChain.getName())
				.type(NavigationType.ITEM.getValue())
				.url("/admin/chain")
				.icon("home")
				.build();
		} else 
			throw new UserWithoutRestaurantChain();
	}

	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_NOT_CREATED() throws UserNotFoundException {
		return NavigationDto
			.builder()
			.id("restaurantChain-create")
			.title("Mi Cadena")
			.type(NavigationType.ITEM.getValue())
			.icon("announcement")
			.badge(
				BadgeDto
				.builder()
				.fg("red")
				.title("¡Nuevo!")
				.build()
			).url("/admin/chain")
			.build();
	}

	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS() throws UserNotFoundException, UserWithoutRestaurants {
		List<RestaurantDto> restaurants = chainService.findByUser().get().getRestaurants();
		Integer amountOfRestaurants = restaurants.size();
		if(restaurants.isEmpty()) {
			throw new UserWithoutRestaurants();
		}
		List<ChildrenDto> children = new ArrayList<ChildrenDto>();
		for(RestaurantDto restaurant: restaurants) {
			String url = String.format("/admin/restaurant/%s", restaurant.getName());
			ChildrenDto childrenDto = ChildrenDto
				.builder()
				.id(restaurant.getName().toLowerCase())
				.title(restaurant.getName())
				.type(NavigationType.ITEM.getValue())
				.url(url)
				.build();
			children.add(childrenDto);
		}
		NavigationDto navigationDto = NavigationDto
			.builder()
			.id("restaurant-create")
			.title("Restaurantes")
			.type(NavigationType.COLLAPSE.getValue())
			.icon("announcement")
			.badge(
				BadgeDto
				.builder()
				.fg("black")
				.bg("orange")
				.title(amountOfRestaurants.toString())
				.build()
			).url("/admin/chain")
			.children(children)
			.build();
		return navigationDto;
	}

	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_ADMIN_RESTAURANTS_NOT_CREATED()
			throws UserNotFoundException {
		return NavigationDto
				.builder()
				.id("restaurant-create")
				.title("Restaurantes")
				.type(NavigationType.ITEM.getValue())
				.icon("announcement")
				.badge(
					BadgeDto
					.builder()
					.fg("red")
					.title("¡Nuevo!")
					.build()
				).url("/admin/chain")
				.build();
	}

	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCT_FAMILIES() throws UserNotFoundException, ChainWithoutProductFamiliesException {
		User user = userService.findByUsername();
		RestaurantChain restaurantChain = user.getChain();
		if(restaurantChain.getFamilies().isEmpty()) {
			throw new ChainWithoutProductFamiliesException();
		}
		Integer amountOfFamilies = restaurantChain.getFamilies().size();
		return NavigationDto
				.builder()
				.id("productfamily-create")
				.title("Familia Productos")
				.type(NavigationType.ITEM.getValue())
				.icon("announcement")
				.badge(
					BadgeDto
					.builder()
					.fg("red")
					.bg("white")
					.title(amountOfFamilies.toString())
					.build()
				).url("/admin/product-families")
				.build();
	}
	
	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_CHAIN_PRODUCT_FAMILIES_NOT_CREATED()
			throws UserNotFoundException {
		return NavigationDto
				.builder()
				.id("productfamily-create")
				.title("Familia Productos")
				.type(NavigationType.ITEM.getValue())
				.icon("announcement")
				.badge(
					BadgeDto
					.builder()
					.fg("red")
					.title("¡Nuevo!")
					.build()
				).url("/admin/product-families")
				.build();
	}
	
	@Override
	public NavigationDto getNavigation_ROLE_RESTAURANT_ADMIN() throws UserNotFoundException {
		return null;
	}
	
}
