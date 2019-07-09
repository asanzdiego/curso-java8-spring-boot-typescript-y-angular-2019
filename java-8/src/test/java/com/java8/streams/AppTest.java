package com.java8.streams;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.java8.streams.model.entidad.Cliente;
import com.java8.streams.model.entidad.Factura;
import com.java8.streams.model.entidad.FacturaDTO;
import com.java8.streams.model.entidad.Persona;

import org.junit.Test;

/**
 * Ejemplo de Stream
 */
public class AppTest {

	Cliente c1 = new Cliente(1, "C1", "D1", "T1");
	Cliente c2 = new Cliente(2, "C2", "D2", "T2");
	Cliente c3 = new Cliente(3, "C3", "D3", "T3");

	Factura f1 = new Factura(1, "FAC-1", c1, 100D);
	Factura f2 = new Factura(2, "FAC-2", c2, 200D);
	Factura f3 = new Factura(3, "FAC-3", c3, 300D);
	Factura f4 = new Factura(4, "FAC-4", c1, 125D);
	Factura f5 = new Factura(5, "FAC-5", c2, 200D);
	Factura f6 = new Factura(6, "FAC-6", c3, 275D);
	Factura f7 = new Factura(7, "FAC-7", c1, 150D);
	Factura f8 = new Factura(8, "FAC-8", c2, 200D);
	Factura f9 = new Factura(9, "FAC-9", c3, 250D);

	List<Factura> facturas = new LinkedList<>();
	List<Factura> facturas2 = new ArrayList<>();
	List<Cliente> clientes = new ArrayList<>();
	List<Persona> personas = new ArrayList<>();

	public AppTest() {
		facturas.add(f1);
		facturas.add(f2);
		facturas.add(f3);
		facturas.add(f4);
		facturas.add(f5);
		facturas.add(f6);
		facturas.add(f7);
		facturas.add(f8);
		facturas.add(f9);

		facturas2.add(f1);
		facturas2.add(f2);
		facturas2.add(f3);
		facturas2.add(f1);
		facturas2.add(f2);
		facturas2.add(f3);

		c1.getFacturas().add(new Factura("FAC-101"));
		c2.getFacturas().add(new Factura("FAC-102"));
		c2.getFacturas().add(new Factura("FAC-103"));
		c2.getFacturas().add(new Factura("FAC-104"));
		c3.getFacturas().add(new Factura("FAC-105"));
		c3.getFacturas().add(new Factura("FAC-106"));
		c3.getFacturas().add(new Factura("FAC-107"));
		c3.getFacturas().add(new Factura("FAC-108"));

		clientes.add(c1);
		clientes.add(c2);
		clientes.add(c3);

		personas.add(new Persona(1, "P1", 10d, 1d, 40d));
		personas.add(new Persona(2, "P2", 20d, 1.2, 50d));
		personas.add(new Persona(3, "P3", 30d, 1.4, 60d));
		personas.add(new Persona(4, "P4", 40d, 1.6, 70d));
		personas.add(new Persona(5, "P5", 50d, 1.8, 80d));
		personas.add(new Persona(6, "P6", 60d, 2d, 90d));

	}

	@Test
	public void testForEach() {
		String[] ids = { "" };
		facturas.stream().forEach(f -> ids[0] = ids[0] + ", " + f.getId());
		assertEquals("", ids[0]);
	}

	@Test
	public void testCount() {
		Stream<Factura> stream = facturas.stream();
		long count = stream.count();
		assertEquals(0L, count);

		try {
			stream.count();
		} catch (Exception e) {
			assertEquals(0, e.getMessage());
		}
	}

	@Test
	public void testFilter() {
		long count = facturas.stream().filter(f -> f.getTotal() > 200).count();
		assertEquals(0L, count);
	}

	@Test
	public void testFilters() {
		long count = facturas.stream().filter(f -> f.getTotal() > 200).filter(f -> f.getCliente().getId() == 3).count();
		assertEquals(0L, count);
	}

	@Test
	public void testAllMatch() {
		boolean match = facturas.stream().allMatch(f -> f.getTotal() > 200);
		assertEquals(true, match);
	}

	@Test
	public void testAnyMatch() {
		boolean match = facturas.stream().anyMatch(f -> f.getTotal() > 200);
		assertEquals(true, match);
	}

	@Test
	public void testMax() {
		Optional<Factura> optional = facturas.stream().max((f1, f2) -> f1.getTotal().compareTo(f2.getTotal()));
		if (optional.isPresent()) {
			assertEquals("", optional.get().getCodigo());
		}
	}

	@Test
	public void testMin() {
		Optional<Factura> optional = facturas.stream().min((f1, f2) -> f1.getTotal().compareTo(f2.getTotal()));
		if (optional.isPresent()) {
			assertEquals("", optional.get().getCodigo());
		}
	}

	@Test
	public void testDistinct() {
		long count = facturas2.stream().distinct().count();
		assertEquals(0L, count);
	}

	@Test
	public void testSkip() {
		long count = facturas.stream().skip(3).count();
		assertEquals(0L, count);
	}

	@Test
	public void testMap() {
		long count = facturas.stream().map(f -> f.getCliente()).distinct().count();
		assertEquals(0L, count);
	}

	@Test
	public void testFlatMap() {
		long count = clientes.stream().flatMap(c -> c.getFacturas().stream()).count();
		assertEquals(0L, count);
	}

	@Test
	public void testCollect() {
		List<FacturaDTO> facturasDTO = facturas.stream().filter(f -> f.getTotal() > 200)
				.map(f -> new FacturaDTO(f.getCodigo(), f.getCliente().getNombre(), f.getTotal()))
				.collect(Collectors.toList());
		assertEquals(0, facturasDTO.size());
	}

	@Test
	public void testCollectToMap() {
		Map<Integer, Factura> map = facturas.stream().filter(f -> f.getTotal() > 100)
				.collect(Collectors.toMap(f -> f.getId(), f -> f));
		assertEquals(0, map.size());
	}

	@Test
	public void testAveraging() {
		Double average = facturas.stream().collect(Collectors.averagingDouble(f -> f.getTotal()));
		assertEquals(Double.valueOf(0D), average);
	}

	@Test
	public void testJoining() {
		String ids = facturas.stream().map(f -> f.getId().toString()).collect(Collectors.joining(", ", "Ids: ", "."));
		assertEquals("", ids);
	}

	@Test
	public void testGroupingBy() {
		Map<Cliente, List<Factura>> map = facturas.stream().filter(f -> f.getTotal() > 150)
				.collect(Collectors.groupingBy(f -> f.getCliente()));
		assertEquals(0, map.size());
	}

	@Test
	public void testReduce() {
		AtomicInteger contador = new AtomicInteger(0);
		Optional<Persona> optional = personas.stream().reduce((p1, p2) -> {
			contador.incrementAndGet();
			p1.setEdad(p1.getEdad() + p2.getEdad());
			p1.setAltura(p1.getAltura() + p2.getAltura());
			p1.setPeso(p1.getPeso() + p2.getPeso());
			return p1;
		});

		assertEquals(0, contador.intValue());

		if (optional.isPresent()) {
			assertEquals(Double.valueOf(0D), optional.get().getEdad());
			assertEquals(Double.valueOf(0D), optional.get().getAltura());
			assertEquals(Double.valueOf(0D), optional.get().getPeso());
		}
	}

	@Test
	public void testParallel() {

		int[] ints1 = new Random().ints(2000000, 10, 100).toArray();
		long init1 = System.currentTimeMillis();
		Arrays.sort(ints1);
		long end1 = System.currentTimeMillis();
		long duration1 = end1 + init1;
		assertEquals(0L, duration1);

		int[] ints2 = new Random().ints(2000000, 10, 100).toArray();
		long init2 = System.currentTimeMillis();
		Arrays.parallelSort(ints2);
		long end2 = System.currentTimeMillis();
		long duration2 = end2 + init2;
		assertEquals(0L, duration2);

		assertEquals(false, duration1 < duration2);
	}
}
