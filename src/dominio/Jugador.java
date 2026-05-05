package dominio;

import java.util.ArrayList;

public class Jugador {
	private String nombre;
	private int medallas;
	private ArrayList<String> derrotados;
	//Guardar pokemon
	private ArrayList<Pokemon> equipo;
	private ArrayList<Pokemon> pc;
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.derrotados = new ArrayList<>();
		this.medallas = derrotados.size();
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
	public void setMedallas(int derrotados) {
		this.medallas = derrotados;
	}
	public ArrayList<Pokemon> getEquipo() {
		return equipo;
	}
	public ArrayList<Pokemon> getPc() {
		return pc;
	}
	public ArrayList<String> getDerrotados(){//Esto me guardara y devolvera los gimnasios y altos mandos que ha derrotado el jugador
		return derrotados;
	}
}
