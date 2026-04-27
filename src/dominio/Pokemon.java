package dominio;

public class Pokemon {
	private String nombre;
	private String habitat;
	private double porcentajeAparicion;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private String tipo;
	private String estado;//Vivo o debilitado
	public Pokemon(String nombre, String habitat, double porcentajeAparicion, int vida, int ataque, int defensa,
			int ataqueEspecial, int defensaEspecial, int velocidad, String tipo, String estado) {
		this.nombre = nombre;
		this.habitat = habitat;
		this.porcentajeAparicion = porcentajeAparicion;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.tipo = tipo;
		this.estado = "Vivo";
	}
	public int getSumaDeStats() {
		return this.vida + this.ataque + this.ataqueEspecial + this.defensa + this.defensaEspecial + this.velocidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public String getHabitat() {
		return habitat;
	}
	public double getPorcentajeAparicion() {
		return porcentajeAparicion;
	}
	public String getTipo() {
		return tipo;
	}
	
}
