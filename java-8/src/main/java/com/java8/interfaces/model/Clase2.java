package com.java8.interfaces.model;

public class Clase2 implements Interfaz1, Interfaz2 {

	@Override
	public String metodo() {
		return "Clase2:metodo";
	}
	
	@Override
	public String metodoDefault(){ // Obligado a sobreescribir
		return Interfaz1.super.metodoDefault() + " "
			+ Interfaz2.super.metodoDefault() + " "
			+ "Clase2:metodoDefault";
	}

}

