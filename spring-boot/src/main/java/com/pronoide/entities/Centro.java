package com.pronoide.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Centros")
public class Centro {

	@Id
	@Column(name = "codigo", nullable = false)
	private Long codigo;

    @Column(name = "localidad", nullable = false)
	private String localidad;

	@Column(name = "generico", nullable = false)
	private String generico;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "ultimaModificacion", nullable = true)
	private Date ultimaModificacion;

	@Column(name = "usuarioModificacion", nullable = true)
	private String usuarioModificacion;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(final Long codigo) {
		this.codigo = codigo;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(final String localidad) {
		this.localidad = localidad;
	}

	public String getGenerico() {
		return generico;
	}

	public void setGenerico(final String generico) {
		this.generico = generico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(final Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(final String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

}