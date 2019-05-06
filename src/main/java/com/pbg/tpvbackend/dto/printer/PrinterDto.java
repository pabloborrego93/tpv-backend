package com.pbg.tpvbackend.dto.printer;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PrinterDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 235245860418623868L;

	@Getter @Setter private Integer id;
	
	@Getter @Setter private String name;
	
	@Getter @Setter private String printerid;
	
	@Getter @Setter private Boolean defaultPrinter;
	
}
