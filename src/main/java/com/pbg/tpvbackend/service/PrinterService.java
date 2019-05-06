package com.pbg.tpvbackend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pbg.tpvbackend.dto.printer.PrinterDto;
import com.pbg.tpvbackend.exception.printer.PrinterNotFoundException;
import com.pbg.tpvbackend.exception.restaurant.RestaurantNotFoundException;
import com.pbg.tpvbackend.model.Printer;
import com.pbg.tpvbackend.model.Restaurant;

public interface PrinterService {

	public Printer findById(Integer id) throws PrinterNotFoundException;
	public List<Printer> findAllById(List<Integer> ids);
	public Printer save(Printer printer);
	public void delete(Printer printer);
	public void deleteById(Integer id);
	public Page<PrinterDto> findByRestaurant(Integer idRestaurant, Integer page, Integer max_per_page) throws RestaurantNotFoundException;
	public PrinterDto postPrinterByRestaurant(Integer id, PrinterDto printerDto) throws RestaurantNotFoundException;
	public PrinterDto putPrinterByRestaurant(Integer id, PrinterDto printerDto) throws NumberFormatException, PrinterNotFoundException, RestaurantNotFoundException;
	public void deletePrinterByRestaurant(Integer idRestaurant, Integer idPrinter);
	public Printer defaultPrinter(Restaurant restaurant) throws PrinterNotFoundException;
	
}
