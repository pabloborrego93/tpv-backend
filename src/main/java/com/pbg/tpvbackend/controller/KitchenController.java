package com.pbg.tpvbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbg.tpvbackend.service.KitchenService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/kitchen")
public class KitchenController {

	KitchenService kitchenService;
	
}
