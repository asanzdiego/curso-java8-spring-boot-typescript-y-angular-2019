package com.java8.interfaces;

import static org.junit.Assert.assertEquals;

import com.java8.interfaces.model.Clase1;
import com.java8.interfaces.model.Clase2;
import com.java8.interfaces.model.Interfaz1;

/**
 * Ejemplos de interfaces
 */
public class AppTest {

	Clase1 c1 = new Clase1();
	Clase2 c2 = new Clase2();
	
	@Test
	public void testMetodos() {
		assertEquals("", c1.metodo());
		assertEquals("", c2.metodo());
	}
	
	@Test
	public void testMetodosDefault() {
		assertEquals("", c1.metodoDefault());
		assertEquals("", c2.metodoDefault());
	}
	
	@Test
	public void testMetodoStatic() {
		assertEquals("", Interfaz1.metodoEstatico());
	}

}
