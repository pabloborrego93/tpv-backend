package com.pbg.tpvbackend.model.product;

public enum IVA {
	
	TYPE1(.10f), TYPE2(.21f);
	
	private final double iva;

    private IVA(double iva) {
        this.iva = iva;
    }

    public double getIVA() {
        return iva;
    }
    
}
