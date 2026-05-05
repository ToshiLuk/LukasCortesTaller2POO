package dominio;

import java.util.ArrayList;

public class Gimnasio {
	private int numero;
	private String lider; //Nombre de lider
	private String estado; //Derrotado: True? False?
	private ArrayList<Pokemon> equipo; //Equipo del lider de gimnasio
	public Gimnasio(int numero, String lider, String estado) {
		this.numero = numero;
		this.lider = lider;
		this.estado = estado;
		this.equipo = new ArrayList<>(); //Existe nuestra lista pero vacia
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLider() {
		return lider;
	}
	public void setLider(String lider) {
		this.lider = lider;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}
	
}
