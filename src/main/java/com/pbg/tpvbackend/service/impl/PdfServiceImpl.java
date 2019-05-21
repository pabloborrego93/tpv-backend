package com.pbg.tpvbackend.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.pbg.tpvbackend.exception.order.OrderNotFoundException;
import com.pbg.tpvbackend.model.order.Order;
import com.pbg.tpvbackend.service.OrderService;
import com.pbg.tpvbackend.service.PdfService;
import com.pbg.tpvbackend.utils.PdfUtils;

@Service
@Validated
public class PdfServiceImpl implements PdfService {

	ResourceLoader resourceLoader;
	OrderService orderService;
	
	public PdfServiceImpl(ResourceLoader resourceLoader, @Lazy OrderService orderService) {
		super();
		this.resourceLoader = resourceLoader;
		this.orderService = orderService;
	}

	@Override
	public InputStream generateTicket(Integer idOrder) throws DocumentException, OrderNotFoundException {

		Order orderDto = orderService.findById(idOrder);
		
		Document pdf = new Document(PageSize.A6);
		pdf.setMargins(4, 4, 4, 4);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfWriter.getInstance(pdf, out);
		pdf.open();

		/* Image */
//		PdfContentByte canvas = writer.getDirectContentUnder();
//		Image image = Image.getInstance(IMAGE);
//		image.scaleAbsolute(PageSize.A4.rotate());
//		image.setAbsolutePosition(10f, 350f);
//		float scaler = ((pdf.getPageSize().getWidth() - pdf.leftMargin() - pdf.rightMargin() - 10) / image.getWidth())
//				* 100;
//		image.scalePercent(scaler);
//		canvas.addImage(image);
		/* End Image */

//		pdf.add(new Paragraph("\n"));
//		pdf.add(new Paragraph("\n"));
//		pdf.add(new Paragraph("\n"));
//		pdf.add(new Paragraph("\n"));
		Paragraph title1 = new Paragraph(orderDto.getZone().getRestaurant().getName() + ", " + orderDto.getZone().getRestaurant().getAddress());
		title1.setAlignment(Element.ALIGN_CENTER);
		pdf.add(title1);
		Paragraph title2 = new Paragraph(orderDto.getZone().getDescription());
		title2.setAlignment(Element.ALIGN_CENTER);
		pdf.add(title2);
		LineSeparator ls = new LineSeparator();
		pdf.add(new Chunk(ls));
		pdf.add(new Paragraph("\n"));
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(90);
		table.addCell("Descripción");
		table.addCell("nº");
		table.addCell("€");
		table.addCell("Total");
		table.setWidths(new int[] { 4, 1, 1, 1 });
		orderDto.getOrderLines().forEach(l -> {
			table.addCell(PdfUtils.noBordersCell(l.getProduct().getName()));
			table.addCell(PdfUtils.noBordersCell(l.getAmount().toString()));
			table.addCell(PdfUtils.noBordersCell(l.getProduct().getPrice().toString()));
			table.addCell(PdfUtils.noBordersCell(BigDecimal.valueOf(l.getTotal()).setScale(2, RoundingMode.HALF_UP).toString()));
		});

		pdf.add(table);

		Double totalOrder = orderDto.getOrderLines().stream().mapToDouble(ol -> ol.getTotal()).sum();
		Paragraph total = new Paragraph("Total: " + BigDecimal.valueOf(totalOrder).setScale(2, RoundingMode.HALF_UP).toString() + " €");
		total.setAlignment(Element.ALIGN_CENTER);
		pdf.add(total);

		pdf.add(new Chunk(ls));
		pdf.add(new Chunk(ls));

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy HH:mm:ss");
		if(orderDto.getClosedAt() != null) {
			Paragraph last = new Paragraph("Hasta pronto! - " + sdf.format(orderDto.getClosedAt()));
			last.setAlignment(Element.ALIGN_CENTER);
			pdf.add(last);
		} else {
			Paragraph last = new Paragraph("Hasta pronto!" + sdf.format(new Date()));
			last.setAlignment(Element.ALIGN_CENTER);
			pdf.add(last);
		}

		pdf.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
	
}
