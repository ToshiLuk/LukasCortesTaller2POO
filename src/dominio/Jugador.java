package dominio;

import java.util.ArrayList;

public class Jugador {
	private String nombre;
	private int medallas;
	//Guardar pokemon
	private ArrayList<Pokemon> equipo;
	private ArrayList<Pokemon> pc;
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.medallas = 0;
		this.equipo = new ArrayList<>();
		this.pc = new ArrayList<>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getMedallas() {
		return medallas;
	}
	public void setMedallas(int medallas) {
		this.medallas = medallas;
	}
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}
	public ArrayList<Pokemon> getPc() {
		return pc;
	}
	
}
