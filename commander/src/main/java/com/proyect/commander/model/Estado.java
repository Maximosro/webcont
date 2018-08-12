package com.proyect.commander.model;

public class Estado {
	int id;
	String nombre;
	String urlBanner;
	int tFrontera;
	String frontera;
	int tGob;
	String gobierno;
	String linkLeader;
	String linkMofa;
	int salMin;
	int pib;

	public Estado() {
		super();
	}

	public Estado(int id, String nombre, String urlBanner, int tFrontera, int tGob, String linkLeader, String linkMofa,
			int salMin, int pib) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.urlBanner = urlBanner;
		this.tFrontera = tFrontera;
		this.tGob = tGob;
		this.linkLeader = linkLeader;
		this.linkMofa = linkMofa;
		this.salMin = salMin;
		this.pib = pib;
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

	public String getUrlBanner() {
		return urlBanner;
	}

	public void setUrlBanner(String urlBanner) {
		this.urlBanner = urlBanner;
	}

	public int gettFrontera() {
		return tFrontera;
	}

	public void settFrontera(int tFrontera) {
		this.tFrontera = tFrontera;
	}

	public int gettGob() {
		return tGob;
	}

	public void settGob(int tGob) {
		this.tGob = tGob;
	}

	public String getLinkLeader() {
		return linkLeader;
	}

	public void setLinkLeader(String linkLeader) {
		this.linkLeader = linkLeader;
	}

	public String getLinkMofa() {
		return linkMofa;
	}

	public void setLinkMofa(String linkMofa) {
		this.linkMofa = linkMofa;
	}

	public int getSalMin() {
		return salMin;
	}

	public void setSalMin(int salMin) {
		this.salMin = salMin;
	}

	public int getPib() {
		return pib;
	}

	public void setPib(int pib) {
		this.pib = pib;
	}

	public String getFrontera() {
		return frontera;
	}

	public void setFrontera(String frontera) {
		this.frontera = frontera;
	}

	public String getGobierno() {
		return gobierno;
	}

	public void setGobierno(String gobierno) {
		this.gobierno = gobierno;
	}

}
