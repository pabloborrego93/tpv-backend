package com.pbg.tpvbackend.utils;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;

public class PdfUtils {

	public static PdfPCell noBordersCell(String text) {
		Phrase phrase = new Phrase(text);
		phrase.setFont(PdfUtils.smallFont());
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}
	
	public static Font smallFont() {
		return new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
	}
	
}
