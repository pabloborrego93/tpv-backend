package com.pbg.tpvbackend.mapper;

import org.mapstruct.Mapper;

import com.pbg.tpvbackend.dto.printer.PrinterDto;
import com.pbg.tpvbackend.model.Printer;

@Mapper(componentModel = "spring")
public interface PrinterMapper {

	public PrinterDto asPrinterDto(Printer printer);
	
}
