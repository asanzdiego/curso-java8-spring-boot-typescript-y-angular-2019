package com.java8.optionals;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import com.java8.optionals.model.entity.Direccion;
import com.java8.optionals.model.entity.Director;
import com.java8.optionals.model.entity.Pelicula;
import com.java8.optionals.model.logic.GestorPeliculas;

/**
 * Ejemplos de Optional
 */
public class AppTest {

	GestorPeliculas gp = new GestorPeliculas();
	Optional<Pelicula> pOp = gp.buscarOptional(1);

	@Test
	public void testSinOpcional() {

		Pelicula p1 = gp.buscar(3);
		assertEquals("", p1.getTitulo());

		Pelicula p2 = gp.buscar(20000);
		try {
			p2.getTitulo();
		} catch (Exception e) {
			assertEquals("", e.getMessage());
		}
	}

	@Test
	public void testConOpcional() {

		// Con optional avisamos al que invoca el método de que puede recibir un null
		Optional<Pelicula> p3 = gp.buscarOptional(3);
		if (p3.isPresent()) {
			assertEquals("", p3.get().getTitulo());
		}

		Optional<Pelicula> p4 = gp.buscarOptional(20000);
		if (p4.isPresent()) {
			assertEquals("", p4.get().getTitulo());
		} else {
			assertEquals("", "no present");
		}

		Optional<Pelicula> pOp = gp.buscarOptional(1);
		if (pOp.isPresent()) {
			Optional<Director> dOp = pOp.get().getDirector();
			if (dOp.isPresent()) {
				Optional<Direccion> dirOp = dOp.get().getDireccion();
				if (dirOp.isPresent()) {
					assertEquals("", dirOp.get().getCiudad());
				}
			}
		}
	}

	@Test
	public void testFlatMapYMap() {

		String ciudad1 = pOp.flatMap(p -> p.getDirector())
							.flatMap(d -> d.getDireccion())
							.map(dir -> dir.getCiudad())
							.orElse("No hay");
		assertEquals("", ciudad1);
	}

	@Test
	public void testOrElse() {
		String ciudad2 = pOp.flatMap(p -> p.getDirector())
							.flatMap(d -> d.getDireccion())
							.map(dir -> dir.getCiudad())
							.filter(c -> c.equals("Santa Pola"))
							.orElse("No es Santa Pola");
		assertEquals("", ciudad2);
	}

	@Test
	public void testOrElseGet() {
		
		String ciudad3 = pOp.flatMap(p -> p.getDirector())
							.flatMap(d -> d.getDireccion())
							.map(dir -> dir.getCiudad())
							.filter(c -> c.equals("Santa Pola"))
							.orElseGet(()->"Recupero Santa Pola");
		assertEquals("", ciudad3);
	}
}



