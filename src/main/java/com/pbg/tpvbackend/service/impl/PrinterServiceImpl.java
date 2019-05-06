package com.pbg.tpvbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.common.collect.Lists;
import com.pbg.tpvbackend.dao.PrinterDao;
import com.pbg.tpvbackend.dto.printer.PrinterDto;
import com.pbg.tpvbackend.exception.printer.PrinterNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.mapper.PrinterMapper;
import com.pbg.tpvbackend.model.Printer;
import com.pbg.tpvbackend.model.Restaurant;
import com.pbg.tpvbackend.service.PrinterService;
import com.pbg.tpvbackend.service.RestaurantService;

import lombok.AllArgsConstructor;

@Service
@Validated
@AllArgsConstructor
public class PrinterServiceImpl implements PrinterService {

	PrinterDao printerDao;
	PrinterMapper printerMapper;
	RestaurantService restaurantService;

	@Override
	public Printer findById(Integer id) throws PrinterNotFoundException {
		Optional<Printer> printer = printerDao.findById(id);
		if(printer.isPresent()) {
			return printer.get();
		} else {
			throw new PrinterNotFoundException();
		}
	}

	@Override
	public List<Printer> findAllById(List<Integer> ids) {
		return Lists.newArrayList(printerDao.findAllById(ids));
	}

	@Override
	public Printer save(Printer printer) {
		return printerDao.save(printer);
	}

	@Override
	public void delete(Printer printer) {
		printerDao.delete(printer);
	}

	@Override
	public void deleteById(Integer id) {
		printerDao.deleteById(id);
	}

	@Override
	public Page<PrinterDto> findByRestaurant(Integer idRestaurant, Integer page, Integer max_per_page)
			throws RestaurantNotFoundException {
		Restaurant restaurant = restaurantService.findById(idRestaurant);
		return printerDao.findByRestaurantOwner(restaurant, PageRequest.of(page, max_per_page)).map((p) -> printerMapper.asPrinterDto(p));
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public PrinterDto postPrinterByRestaurant(Integer id, PrinterDto printerDto) throws RestaurantNotFoundException {
		Restaurant restaurant = restaurantService.findById(id);
		Printer printer = new Printer();
		printer.setRestaurantOwner(restaurant);
		printer.setName(printerDto.getName());
		printer.setPrinterid(printerDto.getPrinterid());
		printer.setDefaultPrinter(printerDto.getDefaultPrinter());
		if(printerDto.getDefaultPrinter()) {
			printerDao.setAllPrintersAsNotDefault(restaurant);
		}
		printer = printerDao.save(printer);
		return printerMapper.asPrinterDto(printer);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public PrinterDto putPrinterByRestaurant(Integer id, PrinterDto printerDto)
			throws NumberFormatException, PrinterNotFoundException, RestaurantNotFoundException {
		Restaurant restaurant = restaurantService.findById(id);
		if(printerDto.getDefaultPrinter()) {
			printerDao.setAllPrintersAsNotDefault(restaurant);
		}
		Printer printer = this.findById(printerDto.getId());
		printer.setName(printerDto.getName());
		printer.setPrinterid(printerDto.getPrinterid());
		printer.setDefaultPrinter(printerDto.getDefaultPrinter());
		printer = printerDao.save(printer);
		return printerMapper.asPrinterDto(printer);
	}

	@Override
	public void deletePrinterByRestaurant(Integer idRestaurant, Integer idPrinter) {
		printerDao.deleteById(idPrinter);
	}

	@Override
	public Printer defaultPrinter(Restaurant restaurant) throws PrinterNotFoundException {
		Optional<Printer> optPrinter = this.printerDao.findByDefaultPrinter(restaurant);
		if(optPrinter.isPresent()) {
			return optPrinter.get();
		} else {
			throw new PrinterNotFoundException();
		}
	}
	
}
