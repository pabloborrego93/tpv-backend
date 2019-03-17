package com.pbg.tpvbackend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.pbg.tpvbackend.dao.kitchen.KitchenDao;
import com.pbg.tpvbackend.dto.kitchen.KitchenProductDto;
import com.pbg.tpvbackend.mapper.KitchenMapper;
import com.pbg.tpvbackend.model.kitchen.KitchenProduct;
import com.pbg.tpvbackend.service.KitchenService;
import com.pbg.tpvbackend.service.security.UserDataService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class KitchenServiceImpl implements KitchenService {

	KitchenDao kitchenDao;
	KitchenMapper kitchenMapper;
	UserDataService userDataService;

	@Override
	public KitchenProductDto create(KitchenProduct kP) {
		kP = kitchenDao.save(kP);
		return kitchenMapper.asDto(kP);
	}
	
}
