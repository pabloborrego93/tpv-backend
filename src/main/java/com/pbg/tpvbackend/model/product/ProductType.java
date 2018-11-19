package com.pbg.tpvbackend.model.product;

public enum ProductType {

	SIMPLE(Values.SIMPLE),
	COMPOSITE(Values.COMPOSITE);

	private ProductType(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static class Values {
		public static final String SIMPLE = "SIMPLE";
		public static final String COMPOSITE = "COMPOSITE";
	}

}
