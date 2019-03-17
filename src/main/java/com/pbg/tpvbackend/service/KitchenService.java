package com.pbg.tpvbackend.service;

import com.pbg.tpvbackend.dto.kitchen.KitchenProductDto;
import com.pbg.tpvbackend.model.kitchen.KitchenProduct;

public interface KitchenService {

	KitchenProductDto create(KitchenProduct kP);

}
