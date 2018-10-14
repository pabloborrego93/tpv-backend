package com.pbg.tpvbackend.dto.navigation;

public enum NavigationType {

	COLLAPSE("collapse"),
	ITEM("item");
	
	private String value;

	private NavigationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
