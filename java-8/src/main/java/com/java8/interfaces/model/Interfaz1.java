package com.java8.interfaces.model;

public interface Interfaz1 {
	
	public abstract String metodo();
	
	default String metodoDefault(){
		return "Interfaz1:metodoDefault";
	}
	
	public static String metodoEstatico(){
		return "Interfaz1:metodoEstatico";
	}	
	
}


















