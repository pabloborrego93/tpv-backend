package com.pbg.tpvbackend.controller;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.restaurant.RestaurantDto;
import com.pbg.tpvbackend.dto.restaurant.RestaurantPostDto;
import com.pbg.tpvbackend.dto.user.UserExtendedInfoDto;
import com.pbg.tpvbackend.dto.zone.ZoneDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantAlreadyExists;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.exception.user.UserWithoutRestaurantChain;
import com.pbg.tpvbackend.exception.zone.ZoneNotFoundException;
import com.pbg.tpvbackend.service.RestaurantService;
import com.pbg.tpvbackend.service.ZoneService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/restaurant")
@AllArgsConstructor
public class RestaurantController {

	RestaurantService restaurantService;
	ZoneService zoneService;
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public RestaurantDto create(@RequestBody RestaurantPostDto restaurantPostDto) throws UserNotFoundException, RestaurantAlreadyExists, UserWithoutRestaurantChain {
		return restaurantService.create(restaurantPostDto);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public RestaurantDto getOne(@RequestParam("name") String name) throws UserNotFoundException, UserWithoutRestaurantChain, RestaurantNotFoundException {
		return restaurantService.findOne(name);
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@GetMapping("/{id}/workers")
	public ResponseEntity<?> getWorkers(@PathVariable("id") Integer id) throws RestaurantNotFoundException {
		return ResponseEntity.ok(restaurantService.getWorkers(id));
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@PostMapping("/{id}/workers")
	public ResponseEntity<?> postWorkers(@PathVariable("id") Integer id, @RequestBody ArrayList<UserExtendedInfoDto> workers) throws RestaurantNotFoundException, NumberFormatException, UserNotFoundException {
		return ResponseEntity.ok(restaurantService.setWorkers(id, workers));
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@GetMapping("/{id}/screens")
	public ResponseEntity<?> getScreens(@PathVariable("id") Integer id) throws RestaurantNotFoundException {
		return ResponseEntity.ok(restaurantService.getScreens(id));
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@PostMapping("/{id}/screens")
	public ResponseEntity<?> postScreens(@PathVariable("id") Integer id, @RequestBody ArrayList<UserExtendedInfoDto> screens) throws RestaurantNotFoundException, NumberFormatException, UserNotFoundException {
		return ResponseEntity.ok(restaurantService.setScreens(id, screens));
	}
	
	@GetMapping("/{id}/zones")
	public Page<ZoneDto> search(
		@PathVariable("id") Integer id,
		@RequestParam(value = "page", required = false, defaultValue = "0") Integer page, 
		@RequestParam(value = "max_per_page", required = false, defaultValue = "10") Integer max_per_page) throws RestaurantNotFoundException {
		return zoneService.findByRestaurant(id, page, max_per_page);
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@PostMapping("/{id}/zone")
	public ZoneDto postZone(
		@PathVariable("id") Integer id,
		@RequestBody ZoneDto zoneDto) throws RestaurantNotFoundException {
		return zoneService.postZoneByRestaurant(id, zoneDto);
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@PutMapping("/{id}/zone")
	public ZoneDto putZone(
		@PathVariable("id") Integer id,
		@RequestBody ZoneDto zoneDto) throws NumberFormatException, ZoneNotFoundException {
		return zoneService.putZoneByRestaurant(id, zoneDto);
	}
	
	@PreAuthorize("hasRole('ROLE_RESTAURANT_CHAIN_ADMIN')")
	@DeleteMapping("/{idRestaurant}/zone/{idZone}")
	public void deleteZone(@PathVariable("idRestaurant") Integer idRestaurant, @PathVariable("idZone") Integer idZone) {
		zoneService.deleteZoneByRestaurant(idRestaurant, idZone);
	}
	
}
