package com.proyect.commander.model;

import java.util.Map;

public class Estado {

	private int id;
	private String nombre;
	private String urlBanner;
	private int tFrontera;
	private String frontera;
	private int tGob;
	private String gobierno;
	private String linkLeader;
	private String linkMofa;
	private int salMin;
	private int pib;
	private Map<Integer, Region> listaRegiones;

	public Estado() {
		super();
	}
	
	public Estado(int id, String nombre, String urlBanner, int tFrontera, String frontera, int tGob, String gobierno,
			String linkLeader, String linkMofa, int salMin, int pib, Map<Integer, Region> listaRegiones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.urlBanner = urlBanner;
		this.tFrontera = tFrontera;
		this.frontera = frontera;
		this.tGob = tGob;
		this.gobierno = gobierno;
		this.linkLeader = linkLeader;
		this.linkMofa = linkMofa;
		this.salMin = salMin;
		this.pib = pib;
		this.listaRegiones = listaRegiones;
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

	public Map<Integer, Region> getListaRegiones() {
		return listaRegiones;
	}

	public void setListaRegiones(Map<Integer, Region> listaRegiones) {
		this.listaRegiones = listaRegiones;
	}

}
