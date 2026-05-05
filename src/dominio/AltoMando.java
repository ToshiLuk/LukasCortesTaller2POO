package dominio;

import java.util.ArrayList;

public class AltoMando {
	private int numero;
	private String nombre;
	private ArrayList<Pokemon> equipo;
	public AltoMando(int numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
		this.equipo = new ArrayList<>();//El autocompletar pone que es una lista de Pokemon pero no es necesario que este pq el eclipse le sabe
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}
	
}
