package com.pbg.tpvbackend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.dto.navigation.NavigationDto;
import com.pbg.tpvbackend.exception.UserNotFoundException;
import com.pbg.tpvbackend.service.NavigationService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/navigation")
@RestController
@AllArgsConstructor
public class NavigationController {

	private NavigationService navigationService;
	
	@GetMapping
	public List<NavigationDto> getNavigation() throws UserNotFoundException {
		return navigationService.getNavigation();
	}
	
}
