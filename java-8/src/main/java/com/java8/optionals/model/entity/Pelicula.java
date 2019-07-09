package com.java8.optionals.model.entity;

import java.util.Optional;

public class Pelicula {

	private Integer id;
	private String titulo;
	private Optional<Director> director;

	public Pelicula() {
		super();
		director = Optional.empty();
	}

	public Pelicula(Integer id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
		
		Director d = new Director("Sr. Smith");
		this.director = Optional.of(d);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Optional<Director> getDirector() {
		return director;
	}

	/*
	public void setDirector(Optional<Director> director) {
		this.director = director;
	}
	*/

}
