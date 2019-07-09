package com.java8.time;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * Ejemplo de Time
 */
public class AppTest {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	ZonedDateTime dateTime = ZonedDateTime.parse("10/05/2019 16:30:00", formatter.withZone(ZoneId.systemDefault()));

	@Test
	public void testFormat() {
		assertEquals("", formatter.format(dateTime));
	}

	@Test
	public void testAdd2Hours() {
		assertEquals("", formatter.format(dateTime.plus(Duration.ofHours(2))));
	}
	@Test
	public void testAdd2Days() {
		assertEquals("", formatter.format(dateTime.plus(Period.ofDays(2))));
	}
}
