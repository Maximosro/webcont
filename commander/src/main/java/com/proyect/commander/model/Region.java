package com.proyect.commander.model;

public class Region {

	private int id;
	private String nombre;
	private String urlbanner;
	private int idResource;
	private String nombreResource;
	private int idForeingState;
	
	public Region() {
		super();
	}
	
	public Region(int id, String nombre, String urlbanner, int idResource, String nombreResource, int idForeingState) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.urlbanner = urlbanner;
		this.idResource = idResource;
		this.nombreResource = nombreResource;
		this.idForeingState = idForeingState;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrlbanner() {
		return urlbanner;
	}

	public void setUrlbanner(String urlbanner) {
		this.urlbanner = urlbanner;
	}

	public int getIdResource() {
		return idResource;
	}

	public void setIdResource(int idResource) {
		this.idResource = idResource;
	}

	public String getNombreResource() {
		return nombreResource;
	}

	public void setNombreResource(String nombreResource) {
		this.nombreResource = nombreResource;
	}

	public int getIdForeingState() {
		return idForeingState;
	}

	public void setIdForeingState(int idForeingState) {
		this.idForeingState = idForeingState;
	}

}
