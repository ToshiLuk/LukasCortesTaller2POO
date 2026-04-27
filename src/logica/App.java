package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import dominio.Jugador;
import dominio.Pokemon;

public class App {
	public static Scanner sc = new Scanner(System.in);
	public static Scanner lector;
	public static ArrayList<Pokemon> pokedexGlobal = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
		int opcion = 0;
		boolean opcionValida = false; // Controla el bucle
		leerPokedex();
		do {
			do {
				System.out.println("1) Continuar.");
				System.out.println("2) Nueva Partida.");
				System.out.println("3) Salir.");
				System.out.print("> ");
				try {
					opcion = Integer.parseInt(sc.nextLine()); // Lee y convierte a int

					if (opcion >= 1 && opcion <= 3) {
						opcionValida = true; // El número es válido, rompemos este mini-bucle
					} else {
						System.out.println("Error: El número debe ser 1, 2 o 3.\n");
					}
				} catch (NumberFormatException e) {
					// Atrapa si pone letras como "a", "hola", etc.
					System.out.println("Error: Ingresó una letra o carácter inválido. Debe ser un número.\n");
				}
			} while (!opcionValida);
			switch (opcion) {
			case 1:
				Jugador jugadorGuardado = cargarPartida();
				if (jugadorGuardado != null) {
					System.out.println("\n¡Bienvenido de vuelta, " + jugadorGuardado.getNombre() + "!");
					menuDeJuego(jugadorGuardado);
				} else {
					System.out.println("\nNo se encontró ninguna partida guardada o el archivo está vacío.");
				}
				break;
			case 2:
				System.out.print("Ingrese su Apodo: ");
				String nombre = sc.nextLine();
				Jugador player = new Jugador(nombre);
				// Algo para crear al jugador como objeto y asi
				menuDeJuego(player);
				break;
			case 3:
				break;
			default:
				break;
			}
		} while (opcion != 3);
	}

	private static void leerPokedex() throws FileNotFoundException {
		File arch = new File("datos/Pokedex.txt");
		lector = new Scanner(arch);
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			String nombre = partes[0].strip();
			String habitat = partes[1].strip();
			double porcentajeAparicion = Double.parseDouble(partes[2].strip());
			int vida = Integer.parseInt(partes[3].strip());
			int ataque = Integer.parseInt(partes[4].strip());
			int defensa = Integer.parseInt(partes[5].strip());
			int ataqueEspecial = Integer.parseInt(partes[6].strip());
			int defensaEspecial = Integer.parseInt(partes[7].strip());
			int velocidad = Integer.parseInt(partes[8].strip());
			String tipo = partes[9].strip();
			// Ya guardado toda la info ahora creamos el
			Pokemon nuevoPokemon = new Pokemon(nombre, habitat, porcentajeAparicion, vida, ataque, defensa,
					ataqueEspecial, defensaEspecial, velocidad, tipo);
			pokedexGlobal.add(nuevoPokemon);
		}
	}

	private static Jugador cargarPartida() {
		File arch = new File("datos/Registrox.txt");
		if (!arch.exists() || arch.length() == 0) {
			return null;
		}
		try {
			Scanner lectorRegistro = new Scanner(arch);
			if (lectorRegistro.hasNextLine()) {
				// leer datos
				String linea = lectorRegistro.nextLine();
				String[] partes = linea.split(";");

				Jugador player = new Jugador(partes[0]);
				player.setMedallas(Integer.parseInt(partes[1]));
				// Vemos que pokemons estan guardados
				while (lectorRegistro.hasNextLine()) {
					String lineaPokemon = lectorRegistro.nextLine();
					if (lineaPokemon.trim().isEmpty())
						continue;
					String[] partesPokemon = lineaPokemon.split(";");
					String nombrePokemon = partesPokemon[0];
					String estadoPokemon = partesPokemon[1];

					Pokemon poke = clonarPokemonPokedex(nombrePokemon);

					if (poke != null) {
						poke.setEstado(estadoPokemon);
						if (player.getEquipo().size() < 6) {
							player.getEquipo().add(poke);
						} else {
							player.getPc().add(poke);
						}
					}
				}
				lectorRegistro.close();
				return player; // Retornamos jugador armado
			}
			lectorRegistro.close();
		} catch (Exception e) {
			System.out.println("Error al intentar leer el archivo Registros.txt");
		}
		return null;
	}

	private static Pokemon clonarPokemonPokedex(String nombrePokemon) {
		for (Pokemon p : pokedexGlobal) {
			if (p.getNombre().equalsIgnoreCase(nombrePokemon)) {
				// Creamos el pokemon
				return new Pokemon(p.getNombre(), p.getHabitat(), p.getPorcentajeAparicion(), p.getVida(),
						p.getAtaque(), p.getDefensa(), p.getAtaqueEspecial(), p.getDefensaEspecial(), p.getVelocidad(),
						p.getTipo());
			}
		}
		return null;// Si el pokemon no existe en la pokedex
	}

	private static void menuDeJuego(Jugador player) {
		int opcion = 0;
		boolean opcionValida = false;
		do {
			do {
				System.out.println(player.getNombre() + ", que deseas hacer?");
				System.out.println("1) Revisar equipo.");
				System.out.println("2) Salir a capturar.");
				System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
				System.out.println("4) Retar un gimnasio.");
				System.out.println("5) Desafío Al Alto Mando.");
				System.out.println("6) Curar Pokémon.");
				System.out.println("7) Guardar.");
				System.out.println("8) Guardar y Salir.");
				try {
					opcion = Integer.parseInt(sc.nextLine()); // Lee y convierte a int

					if (opcion >= 1 && opcion <= 8) {
						opcionValida = true; // El número es válido, rompemos este mini-bucle
					} else {
						System.out.println("Error: El número debe ser 1, 2, 3, 4, 5, 6, 7 u 8.\n");
					}
				} catch (NumberFormatException e) {
					// Atrapa si pone letras como "a", "hola", etc.
					System.out.println("Error: Ingresó una letra o carácter inválido. Debe ser un número.\n");
				}
			} while (!opcionValida);
			switch (opcion) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			}
		} while (opcion != 8);

	}

}
