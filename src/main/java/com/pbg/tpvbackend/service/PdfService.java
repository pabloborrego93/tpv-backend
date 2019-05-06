package com.pbg.tpvbackend.service;

import java.io.InputStream;

import com.itextpdf.text.DocumentException;
import com.pbg.tpvbackend.exception.order.OrderNotFoundException;

public interface PdfService {

	InputStream generateTicket(Integer idOrder) throws DocumentException, OrderNotFoundException;
	
}
