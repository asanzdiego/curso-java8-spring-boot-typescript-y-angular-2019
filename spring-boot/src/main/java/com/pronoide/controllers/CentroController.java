package com.pronoide.controllers;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;

import com.pronoide.entities.Centro;
import com.pronoide.entities.User;
import com.pronoide.repositories.CentroRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CentroController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private CentroRepository centroRepository;

	@GetMapping(value = "/centros")
	public ResponseEntity<Page<Centro>> getCentros(
			@RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {

		this.logger.debug("GET:/centros pageNumber: " + pageNumber + " pageSize:" + pageSize);

		final Direction direction = Direction.DESC;
		String sortColumn = "ultimaModificacion";
		final Sort sort = new Sort(direction, sortColumn);
		final PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

		final Page<Centro> resultPage = this.centroRepository.findAll(pageRequest);

		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}

	@GetMapping(value = "/centros/{nombre}")
	public ResponseEntity<Page<Centro>> getCentrosByNombre(@PathVariable final String nombre,
			@RequestParam(value = "pageNumber", defaultValue = "0") final Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5") final Integer pageSize) {

		this.logger.debug("GET:/centros/" + nombre + " pageNumber: " + pageNumber + " pageSize:" + pageSize);

		final Direction direction = Direction.DESC;
		String sortColumn = "ultimaModificacion";
		final Sort sort = new Sort(direction, sortColumn);
		final PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

		final Page<Centro> resultPage = this.centroRepository.findAllByNombreLikeIgnoreCase("%" + nombre + "%",
				pageRequest);

		return new ResponseEntity<>(resultPage, HttpStatus.OK);
	}

	@GetMapping(value = "/centro/{codigoCentro}")
	public ResponseEntity<Centro> getCentro(@PathVariable final Long codigoCentro) {

		this.logger.debug("GET:/centro/" + codigoCentro);

		final Optional<Centro> centro = this.centroRepository.findById(codigoCentro);

		if (!centro.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(centro.get(), HttpStatus.OK);
	}

	@PostMapping(value = "/centro/")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Centro> createCentro(final Authentication auth, @RequestBody final Centro centro) {

		this.logger.debug("POST:/centro/");

		User principal = (User) auth.getPrincipal();

		centro.setUltimaModificacion(new Date());
		centro.setUsuarioModificacion(principal.getUsername());

		final Centro newCentro = this.centroRepository.save(centro);

		return new ResponseEntity<>(newCentro, HttpStatus.CREATED);
	}

	@PutMapping(value = "/centro/{codigoCentro}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Centro> updateCentro(final Authentication auth, 
			@PathVariable final Long codigoCentro,
			@RequestBody final Centro centro) {

		this.logger.debug("PUT:/centro/" + codigoCentro);

		final Optional<Centro> optional = this.centroRepository.findById(codigoCentro);

		if (!optional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		User principal = (User) auth.getPrincipal();

		final Centro oldCentro = optional.get();
		oldCentro.setLocalidad(centro.getLocalidad());
		oldCentro.setNombre(centro.getNombre());
		oldCentro.setUltimaModificacion(new Date());
		oldCentro.setUsuarioModificacion(principal.getUsername());

		final Centro newCentro = this.centroRepository.save(oldCentro);

		return new ResponseEntity<>(newCentro, HttpStatus.OK);
	}

	@DeleteMapping(value = "/centro/{codigoCentro}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Long> updateCentro(@PathVariable final Long codigoCentro) {

		this.logger.debug("DELETE:/centro/" + codigoCentro);

		this.centroRepository.deleteById(codigoCentro);

		return new ResponseEntity<>(codigoCentro, HttpStatus.OK);
	}
}