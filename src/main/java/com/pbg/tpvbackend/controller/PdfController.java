package com.pbg.tpvbackend.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.pbg.tpvbackend.exception.gcp.AccessTokenException;
import com.pbg.tpvbackend.exception.gcp.SubmitJobException;
import com.pbg.tpvbackend.exception.order.OrderNotFoundException;
import com.pbg.tpvbackend.exception.printer.PrinterNotFoundException;
import com.pbg.tpvbackend.service.CloudPrintService;
import com.pbg.tpvbackend.service.OrderService;
import com.pbg.tpvbackend.service.PdfService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/pdf")
@RestController
@AllArgsConstructor
public class PdfController {

	PdfService pdfService;
	CloudPrintService cloudPrintService;
	OrderService orderService;
	
	@GetMapping("/ticket")
	public ResponseEntity<InputStreamResource> generateTicket(@RequestParam("idOrder") Integer idOrder) throws DocumentException, OrderNotFoundException {
		InputStream input = pdfService.generateTicket(idOrder);
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    String filename = "ticket.pdf";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    return new ResponseEntity<>(new InputStreamResource(input), headers, HttpStatus.OK);
	}
	
	@GetMapping("/ticket/print")
	public void printTicker(@RequestParam("idOrder") Integer idOrder) throws DocumentException, OrderNotFoundException, PrinterNotFoundException, IOException, AccessTokenException, GeneralSecurityException, SubmitJobException {
		orderService.printTicket(idOrder);
	}
	
}
