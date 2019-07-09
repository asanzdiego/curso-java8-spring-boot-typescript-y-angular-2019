package com.java8.lambdas;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.junit.Test;

/**
 * Ejemplo de Lambdas
 */
public class AppTest {


	interface Interfaz {
		public String metodo(String param1, String param2);
	}

	@Test
	public void testInterfaz() {
				
		// Clase interna anonima
		Interfaz i1 = new Interfaz() {
			@Override
			public String metodo(String param1, String param2) {
				return param1 + param2;
			}
		};

		// Expresion lambda
		Interfaz i2 = (p1, p2) -> p1 + p2;

		assertEquals("", i1.metodo("hola", "1"));
		assertEquals("", i2.metodo("hola", "2"));
	}

	@Test
	public void testSupplier() {

		// supplier: public T get()
		Supplier<String> mySuplier = () -> "hola";
		assertEquals("", mySuplier.get());
	}

	@Test
	public void testFunction() {

		// function: public Tipo1 apply(Tipo2 t)
		Function<String, String> myFunction = txt -> txt.toUpperCase();
		assertEquals("", myFunction.apply("abcdefg"));
	}

	@Test
	public void testPredicate() {

		// predicate: public boolean test(T t)
		Predicate<String> myPredicate = txt -> txt.length() > 10;
		assertEquals(true, myPredicate.test("HOLA"));
		assertEquals(false, myPredicate.test("HOLA CARACOLA"));
	}

	@Test
	public void testOperator() {

		// operator: public Tipo1 apply(Tipo1 t)
		UnaryOperator<String> unaryOperator = txt -> txt.toUpperCase();
		assertEquals("", unaryOperator.apply("abcdefg"));
	}

}