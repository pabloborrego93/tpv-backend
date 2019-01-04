package com.pbg.tpvbackend.model;

public enum ZoneType {
	
	BAR("BAR"), 
	LOUNGE("LOUNGE"), 
	TERRACE("TERRACE");

	private ZoneType(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
