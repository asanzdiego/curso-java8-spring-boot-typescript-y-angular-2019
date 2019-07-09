package com.java8.optionals.model.entity;

import java.util.Optional;

public class Director {

	private String nombre;
	private Optional<Direccion> direccion;

	public Director() {
		super();
		direccion = Optional.empty();
	}

	public Director(String nombre) {
		super();
		this.nombre = nombre;

		direccion = Optional.of(new Direccion("Chinchon","Plaza"));
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Optional<Direccion> getDireccion() {
		return direccion;
	}

	/*
	public void setDireccion(Optional<Direccion> direccion) {
		this.direccion = direccion;
	}
	*/

}
