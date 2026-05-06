package logica;

//Lukas Paolo Toshisuke Cortés Alfaro, 22.108.123-4, ICCI
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import dominio.AltoMando;
import dominio.Gimnasio;
import dominio.Jugador;
import dominio.Pokemon;
import dominio.TablaTipos;

public class App {
	public static Scanner sc = new Scanner(System.in);//Para toda entrada por consola/teclado
	public static Scanner lector;//Para toda entrada de .txt
	public static ArrayList<Pokemon> pokedexGlobal = new ArrayList<>();//En donde se guardan los objetos Pokemon de Pokedex.txt
	public static ArrayList<Gimnasio> lideresGym = new ArrayList<>();//En donde se guardan los objetos Gimnasio de Gimasios.txt
	public static ArrayList<AltoMando> altoMandoMiembros = new ArrayList<>();//En donde se guardan los objetos AltoMando de Alto Mando.txt
	public static String[] tipos = { "Normal", "Fuego", "Agua", "Planta", "Electrico", "Hielo", "Lucha", "Veneno",
			"Tierra", "Volador", "Psiquico", "Bicho", "Roca", "Fantasma", "Dragon", "Acero", "Siniestro", "Hada" };//Lista con todos los tipos ordenados segun la clase de TablaTipos para conseguir indice
	public static void main(String[] args) throws FileNotFoundException {
		int opcion = 0;//Opcion para menus
		boolean opcionValida = false; // Controla el bucle
		leerPokedex();//Se lee crea y guarda los pokemon en una ArrayList
		leerGymLeaders();//Se lee crea y guarda los gimnasios en una ArrayList
		leerAltoMando();//Se lee crea y guarda el AltoMando en una ArrayList
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
					// Atrapa si se pone letras como "a", "hola", etc.
					System.out.println("Error: Ingresó una letra o carácter inválido. Debe ser un número.\n");
				}
			} while (!opcionValida);
			switch (opcion) {
			case 1:
				Jugador jugadorGuardado = cargarPartida();//Cargar partida nos toma lo que se guardó en Registros.txt y crea un objeto Jugador con los datos
				if (jugadorGuardado != null) {//Si se creo un objeto Jugador nos llama al menu con este objeto
					System.out.println("\n¡Bienvenido de vuelta, " + jugadorGuardado.getNombre() + "!");
					menuDeJuego(jugadorGuardado);//El menu del juego
				} else {
					System.out.println("\nNo se encontró ninguna partida guardada o el archivo está vacío.");//Cuando el objeto jugador se crea con un Registros.txt vacio
				}
				break;
			case 2:
				System.out.print("Ingrese su Apodo: ");
				String nombre = sc.nextLine();
				Jugador player = new Jugador(nombre);
				//Se crea el jugador
				menuDeJuego(player);//Y se entra al menu
				break;
			case 3:
				System.out.println("Hasta luego entrenador...");//Salida de programa
				break;
			default:
				break;
			}
		} while (opcion != 3);
	}
	private static void menuDeJuego(Jugador player) {
		int opcion = 0;
		boolean opcionValida = false;
		int cont = 0;
		do {
			do {
				System.out.println("\n" + player.getNombre() + ", que deseas hacer?");
				System.out.println("1) Revisar equipo.");
				System.out.println("2) Salir a capturar.");
				System.out.println("3) Acceso al PC (cambiar Pokémon del equipo).");
				System.out.println("4) Retar un gimnasio.");
				System.out.println("5) Desafío Al Alto Mando.");
				System.out.println("6) Curar Pokémon.");
				System.out.println("7) Guardar.");
				System.out.println("8) Guardar y Salir.");
				System.out.print("Ingrese Opcion: ");
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
				cont = 0; // Contador para printear el primer numero
				System.out.println("\nEquipo Actual:");
				// Recorremos el equipo
				for (Pokemon p : player.getEquipo()) {
					cont += 1;
					System.out.println(
							cont + ") " + p.getNombre() + "|" + p.getTipo() + "|Stats totales: " + p.getStats());
				}
				break;
			case 2:
				do {
					System.out.println("Donde deseas ir a explorar? \n\nZonas disponibles:\n");
					System.out.println("1) Lago");
					System.out.println("2) Cueva");
					System.out.println("3) Montaña");
					System.out.println("4) Bosque");
					System.out.println("5) Prado");
					System.out.println("6) Mar");
					System.out.println("7) Volver al menu.");
					System.out.print("Ingrese Zona: ");
					try {
						opcion = Integer.parseInt(sc.nextLine()); // Lee y convierte a int

						if (opcion >= 1 && opcion <= 7) {
							opcionValida = true; // El número es válido, rompemos este mini-bucle
						} else {
							System.out.println("Error: El número debe ser 1, 2, 3, 4, 5, 6 u 7.\n");
						}
					} catch (NumberFormatException e) {
						// Atrapa si pone letras como "a", "hola", etc.
						System.out.println("Error: Ingresó una letra o carácter inválido. Debe ser un número.\n");
					}
				} while (!opcionValida);
				String habitat = "";
				switch (opcion) {
				case 1:
					habitat = "Lago";
					break;
				case 2:
					habitat = "Cueva";
					break;
				case 3:
					habitat = "Montaña";
					break;
				case 4:
					habitat = "Bosque";
					break;
				case 5:
					habitat = "Prado";
					break;
				case 6:
					habitat = "Mar";
					break;
				case 7:
					break;// Nos devolvemos
				}
				if (!habitat.isEmpty()) {
					Pokemon pokemonSalvaje = encontrarPokemon(habitat);
					System.out.println("\nOh!! Ha aparecido un increible " + pokemonSalvaje.getNombre() + "!!");

					System.out.println("\nQue deseas hacer?");
					System.out.println("1) Capturar");
					System.out.println("2) Huir");
					System.out.print("Ingrese Opción: ");
					int accion = Integer.parseInt(sc.nextLine());

					if (accion == 1) {
						// Ya fue capturado?
						boolean posesion = false;
						for (Pokemon p : player.getEquipo()) {
							if (p.getNombre().equals(pokemonSalvaje.getNombre()))
								posesion = true;
						}
						for (Pokemon p : player.getPc()) {
							if (p.getNombre().equals(pokemonSalvaje.getNombre()))
								posesion = true;
						}
						if (posesion) {
							System.out.println(
									"\nCalmado Entrenador!! Ya has capturado a " + pokemonSalvaje.getNombre() + ".");
						} else {
							// Agregamos a equipo o pc
							System.out.println(pokemonSalvaje.getNombre() + " capturado con exito!!");

							if (player.getEquipo().size() < 6) {
								player.getEquipo().add(pokemonSalvaje);
								System.out.println(pokemonSalvaje.getNombre() + " a sido agregado a tu equipo!");
							} else {
								player.getPc().add(pokemonSalvaje);
								System.out.println(pokemonSalvaje.getNombre() + " ha sido enviado al PC!");
							}
						}
					} else {
						System.out.println("\nHuiste del combate sin problemas...");
					}
				}
				break;
			case 3:
				cont = 0;
				opcion = 0;
				System.out.println("\n=== Acceso al PC ===");
				System.out.println("Equipo:");
				for (Pokemon p : player.getEquipo()) {// Printeamos equipo
					cont += 1;
					System.out.println(
							cont + ") " + p.getNombre() + "|" + p.getTipo() + "|Stats totales: " + p.getStats());
				}
				System.out.println("PC:");
				for (Pokemon p : player.getPc()) {// Printeamos el pc sin resetear el contador
					cont += 1;
					System.out.println(
							cont + ") " + p.getNombre() + "|" + p.getTipo() + "|Stats totales: " + p.getStats());
				}
				System.out.println("1) Cambiar Pokemon.");
				System.out.println("2) Salir.");
				System.out.print("> ");
				opcion = Integer.parseInt(sc.nextLine());
				if (opcion == 2) {
					System.out.println("Se desconectó del PC...");
				} else if (opcion == 1) {
					if (player.getPc().isEmpty()) {
						System.out.println("\nNo tienes Pokemon en el PC para intercambiar.");
						break;
					}
					System.out.println("¿Que Pokemon de su equipo va a cambiar?(Ingrese su indice)");
					System.out.print("> ");
					int opcionEquipo = Integer.parseInt(sc.nextLine());
					System.out.println("¿Que Pokemon del PC quiere traer a su equipo?(Ingrese el indice)");
					System.out.print(">");
					int opcionPC = Integer.parseInt(sc.nextLine());
					int indiceEquipo = opcionEquipo - 1;
					int indicePC = opcionPC - 1 - player.getEquipo().size();
					if (indiceEquipo >= 0 && indiceEquipo < player.getEquipo().size() && indicePC >= 0
							&& indicePC < player.getPc().size()) {
						// Guardamos ambos pokemon
						Pokemon auxE = player.getEquipo().get(indiceEquipo);
						Pokemon auxPC = player.getPc().get(indicePC);
						// .set para cambiar los arraylist
						player.getEquipo().set(indiceEquipo, auxPC);
						player.getPc().set(indicePC, auxE);

						System.out.println("\n¡Intercambio exitoso!");
						System.out.println(auxPC.getNombre() + " ahora está en tu equipo.");
					} else {
						System.out.println("\nError: Numeros ingresados fuera de rango.");
					}
				}

				break;
			case 4:
				System.out.println("\nA cual Lider deseas retar??\n");
				for (Gimnasio g : lideresGym) {
					System.out.println(g.getNumero() + ") " + g.getLider() + " - Estado:" + g.getEstado());
				}
				System.out.println("9) Volver al menu.");
				System.out.print("Ingrese Opcion: ");

				int opcionGym = Integer.parseInt(sc.nextLine());
				if (opcionGym == 9) {
					System.out.println("Volviendo...");
					break;
				}

				if (opcionGym >= 1 && opcionGym <= 8) {
					Gimnasio gymElegido = lideresGym.get(opcionGym - 1);

					if (opcionGym <= player.getMedallas()) {
						System.out.println("\nYa has derrotado a " + gymElegido.getLider() + " anteriormente.");
					} else if (opcionGym > player.getMedallas() + 1) {
						System.out.println("\nCalmado Entrenador!!! No puedes retar a " + gymElegido.getLider()
								+ " sin haber derrotado a los lideres anteriores!!");
					} else if (opcionGym == player.getMedallas() + 1) {

						System.out.println("\nDesafiando a " + gymElegido.getLider() + "!!");

						int indicePokemonLider = 0;
						Pokemon pokemonLider = gymElegido.getEquipo().get(indicePokemonLider);

						Pokemon pokemonJugador = null;
						for (Pokemon p : player.getEquipo()) {
							if (p.getEstado().equals("Vivo")) {
								pokemonJugador = p;
								break;
							}
						}
						if (pokemonJugador == null) {
							System.out.println("No tienes Pokemons sin debilitar para pelear. Ve a curarlos primero.");
							break;
						}

						System.out.println(gymElegido.getLider() + " saca a " + pokemonLider.getNombre() + "!");
						System.out.println(player.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");

						boolean batalla = true;
						while (batalla) {
							// Comprobacion de que si el pokemon actual está debilitado
							if (pokemonJugador.getEstado().equals("Debilitado")) {
								System.out.println("\nTu pokemon actual esta debilitado. Debes cambiarlo.");
								System.out.println("Elige un pokemon Vivo de tu equipo:");
								int auxCont = 1;
								for (Pokemon p : player.getEquipo()) {
									System.out.println(auxCont + ") " + p.getNombre() + " - " + p.getEstado());
									auxCont++;
								}
								System.out.print("> ");
								int eleccionF = Integer.parseInt(sc.nextLine());
								Pokemon posibleCambio = player.getEquipo().get(eleccionF - 1);

								if (posibleCambio.getEstado().equals("Vivo")) {
									pokemonJugador = posibleCambio;
									System.out.println(
											player.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
								} else {
									System.out.println("¡Ese Pokemon no puede pelear!");
									continue; // Vuelve al inicio del while para que elija bien
								}
							}

							System.out.println("\nQue deseas hacer?");
							System.out.println("1) Atacar");
							System.out.println("2) Cambiar de pokemon");
							System.out.println("3) Rendirse");
							System.out.print("Ingrese Opción: ");
							int accionDeBatalla = Integer.parseInt(sc.nextLine());

							if (accionDeBatalla == 1) { // ATACAR

								double statsJugador = pokemonJugador.getStats();
								double statsLider = pokemonLider.getStats();
								// Print stats normales
								System.out.println(pokemonJugador.getNombre() + " -> " + statsJugador + " puntos");
								System.out.println(pokemonLider.getNombre() + " -> " + statsLider + " puntos\n");

								int indiceJugador = obtenerIndiceTipo(pokemonJugador.getTipo());
								int indiceLider = obtenerIndiceTipo(pokemonLider.getTipo());

								double efectividad = TablaTipos.getEfectividad()[indiceJugador][indiceLider];
								double statsEfectividad = statsJugador * efectividad;

								// Mensajes de efectividad
								if (efectividad != 1.0) {
									if (efectividad == 2.0) {
										System.out.println("\n¡" + pokemonJugador.getNombre() + " es muy eficaz contra "
												+ pokemonLider.getNombre() + "!");
									} else if (efectividad == 0.5) {
										System.out.println("\n" + pokemonJugador.getNombre() + " no es efectivo contra "
												+ pokemonLider.getNombre() + "!");
									} else if (efectividad == 0.0) {
										System.out.println("\n¡" + pokemonJugador.getNombre() + " no afecta a "
												+ pokemonLider.getNombre() + "!");
									}

									System.out.println("Nuevo puntaje:");
									System.out.println(
											pokemonJugador.getNombre() + " -> " + statsEfectividad + " puntos");
									System.out.println(pokemonLider.getNombre() + " -> " + statsLider + " puntos\n");
								} else {
									System.out.println(); // Salto de línea estético si no hubo mensaje
								}
								// Resolución del combate
								if (statsEfectividad >= statsLider) {
									// Gana Jugador
									System.out.println("Ha ganado " + pokemonJugador.getNombre() + "! "
											+ pokemonLider.getNombre() + " ha sido derrotado...");
									indicePokemonLider++;

									if (indicePokemonLider < gymElegido.getEquipo().size()) {
										// Le quedan pokemon al lider
										pokemonLider = gymElegido.getEquipo().get(indicePokemonLider);
										System.out.println("\n" + gymElegido.getLider() + " saca a "
												+ pokemonLider.getNombre() + "!");
									} else {
										// Gana la medalla
										System.out.println("\n¡Has derrotado a todos los Pokemon de "
												+ gymElegido.getLider() + "!");
										player.setMedallas(player.getMedallas() + 1);
										gymElegido.setEstado("Derrotado");
										lideresGym.get(opcionGym).setEstado("Derrotado");
										player.getDerrotados().add(gymElegido.getLider());
										batalla = false;
									}

								} else {
									// Gana Lider
									System.out.println("Ha ganado " + pokemonLider.getNombre() + "! "
											+ pokemonJugador.getNombre() + " ha sido derrotado...");
									pokemonJugador.setEstado("Debilitado"); // Aquí el objeto queda marcado en toda la
																			// lista

									// Comprobamos si el jugador perdió la partida completa
									boolean quedanVivos = false;
									for (Pokemon p : player.getEquipo()) {
										if (p.getEstado().equals("Vivo")) {
											quedanVivos = true;
											break;
										}
									}

									if (!quedanVivos) {
										System.out.println("¡Te has quedado sin pokemons en tu equipo!");
										System.out.println("Volviendo al menu...");
										batalla = false;
									}
									// Si le quedan vivos, el inicio del while
									// va a pedir que cambie de Pokemon automáticamente.
								}

							} else if (accionDeBatalla == 2) { // Cambiar de pokemon
								System.out.println("\nElige un pokemon Vivo de tu equipo:");
								int auxCont = 1;
								for (Pokemon p : player.getEquipo()) {
									System.out.println(auxCont + ") " + p.getNombre() + " - " + p.getEstado());
									auxCont++;
								}
								System.out.print("> ");
								int eleccion = Integer.parseInt(sc.nextLine());
								Pokemon posibleCambio = player.getEquipo().get(eleccion - 1);

								if (posibleCambio.getEstado().equals("Vivo")) {
									pokemonJugador = posibleCambio;
									System.out.println("¡Regresa! " + player.getNombre() + " saca a "
											+ pokemonJugador.getNombre() + "!");
								} else {
									System.out.println("¡Ese Pokemon no puede pelear!");
								}

							} else if (accionDeBatalla == 3) { // Rendirse
								System.out.println("\nTe has rendido. ¡Vuelve cuando seas mas fuerte!");
								batalla = false;
							}
						}
					}
				} else {
					System.out.println("\nOpción no válida.");
				}
				break;
			case 5:
				if (player.getMedallas() != lideresGym.size()) {
					System.out.println(
							"\nPrimero tiene que conseguir todas las medallas de gimnasio antes de enfrentarse al Alto Mando...");
					System.out.println(
							"Tienes " + player.getMedallas() + " de " + lideresGym.size() + " que hay en total");

				} else if (player.getDerrotados().contains("Alto Mando")) {
					System.out.println("¡Ya derrotaste al Alto Mando y te coronaste como Campeón!");

				} else {
					System.out.println("\n=== Bienvenido al Alto Mando ===\n");
					System.out.println("Te enfrentaras a " + altoMandoMiembros.size()
							+ " entrenadores de un nivel mayor al que has visto antes");
					System.out.println(
							"Tendras que ganar en contra de ellos de forma consecutiva sin poder cambiar de pokemon ni curarlos");
					System.out.println("¿Seguro que estas preparado para enfrentar al Alto Mando?");
					System.out.println("1) ¡Comenzar el combate!");
					System.out.println("2) Volver al menu");
					System.out.print("Ingresar Opción: ");
					int opcionAltoMando = Integer.parseInt(sc.nextLine());
					int altoMandoDerrotado = 0;
					if (opcionAltoMando == 2) {
						System.out.println("Volviendo al menú...\n");
						break;
					} else if (opcionAltoMando == 1) {
						for (AltoMando alto : altoMandoMiembros) {// Se repite x veces segun cuantos miembros del Alto
																	// Mando hay
							System.out.println("\nDesafiando a " + alto.getNombre() + "!!");

							int indicePokemonAltom = 0;// Primer pokemon de miembro del alto mando
							Pokemon pokemonAlto = alto.getEquipo().get(indicePokemonAltom);

							Pokemon pokemonJugador = null;
							for (Pokemon p : player.getEquipo()) {
								if (p.getEstado().equals("Vivo")) {
									pokemonJugador = p;
									break;
								}
							}
							if (pokemonJugador == null) {
								System.out.println(
										"No tienes Pokemons sin debilitar para pelear. Ve a curarlos primero.");
								break;
							}

							System.out.println(alto.getNombre() + " saca a " + pokemonAlto.getNombre() + "!");
							System.out.println(player.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");

							boolean batalla = true;
							while (batalla) {// Ciclo de cuando termina el combate
								// Comprobacion de que si el pokemon actual está debilitado
								// MODIFICAR
								if (pokemonJugador.getEstado().equals("Debilitado")) {
									System.out.println("\nTu pokemon actual esta debilitado. Debes cambiarlo.");
									System.out.println("Elige un pokemon Vivo de tu equipo:");
									int auxCont = 1;
									for (Pokemon p : player.getEquipo()) {
										System.out.println(auxCont + ") " + p.getNombre() + " - " + p.getEstado());
										auxCont++;
									}
									System.out.print("> ");
									int eleccionF = Integer.parseInt(sc.nextLine());
									Pokemon posibleCambio = player.getEquipo().get(eleccionF - 1);

									if (posibleCambio.getEstado().equals("Vivo")) {
										pokemonJugador = posibleCambio;
										System.out.println(
												player.getNombre() + " saca a " + pokemonJugador.getNombre() + "!");
									} else {
										System.out.println("¡Ese Pokemon no puede pelear!");
										continue; // Vuelve al inicio del while para que elija bien
									}
								}
								System.out.println("\nQue deseas hacer?");
								System.out.println("1) Atacar");
								System.out.println("2) Cambiar de pokemon");
								System.out.println("3) Rendirse");
								System.out.print("Ingrese Opción: ");
								int accionDeBatalla = Integer.parseInt(sc.nextLine());

								if (accionDeBatalla == 1) {// Atacar

									double statsJugador = pokemonJugador.getStats();
									double statsAlto = pokemonAlto.getStats();
									// Print stats normales
									System.out.println(pokemonJugador.getNombre() + " -> " + statsJugador + " puntos");
									System.out.println(pokemonAlto.getNombre() + " -> " + statsAlto + " puntos\n");

									int indiceJugador = obtenerIndiceTipo(pokemonJugador.getTipo());
									int indiceLider = obtenerIndiceTipo(pokemonAlto.getTipo());

									double efectividad = TablaTipos.getEfectividad()[indiceJugador][indiceLider];
									double statsEfectividad = statsJugador * efectividad;

									// Mensajes de efectividad
									if (efectividad != 1.0) {
										if (efectividad == 2.0) {
											System.out.println("\n¡" + pokemonJugador.getNombre()
													+ " es muy eficaz contra " + pokemonAlto.getNombre() + "!");
										} else if (efectividad == 0.5) {
											System.out.println("\n" + pokemonJugador.getNombre()
													+ " no es efectivo contra " + pokemonAlto.getNombre() + "!");
										} else if (efectividad == 0.0) {
											System.out.println("\n¡" + pokemonJugador.getNombre() + " no afecta a "
													+ pokemonAlto.getNombre() + "!");
										}

										System.out.println("Nuevo puntaje:");
										System.out.println(
												pokemonJugador.getNombre() + " -> " + statsEfectividad + " puntos");
										System.out.println(pokemonAlto.getNombre() + " -> " + statsAlto + " puntos\n");
									} else {
										System.out.println(); // Salto de linea si no hubo efectividad
									}
									// Resolución del combate
									if (statsEfectividad >= statsAlto) {
										// Gana Jugador
										System.out.println("Ha ganado " + pokemonJugador.getNombre() + "! "
												+ pokemonAlto.getNombre() + " ha sido derrotado...");
										indicePokemonAltom++;

										if (indicePokemonAltom < alto.getEquipo().size()) {
											// Le quedan pokemon al miembro del alto mando
											pokemonAlto = alto.getEquipo().get(indicePokemonAltom);
											System.out.println("\n" + alto.getNombre() + " saca a "
													+ pokemonAlto.getNombre() + "!");
										} else {
											// Gana el combate el jugador
											altoMandoDerrotado++;
											System.out.println("\n¡Has derrotado a todos los Pokemon de "
													+ alto.getNombre() + "!");
											System.out.println("Te quedan " + (7 - altoMandoDerrotado)
													+ " miembros del Alto Mando\n");
											batalla = false;
										}

									} else {
										// Gana el alto mando
										System.out.println("Ha ganado " + pokemonAlto.getNombre() + "! "
												+ pokemonJugador.getNombre() + " ha sido derrotado...");
										pokemonJugador.setEstado("Debilitado"); // Aquí el objeto queda marcado en toda
																				// la
																				// lista

										// Comprobamos si el jugador perdió la partida completa
										boolean quedanVivos = false;
										for (Pokemon p : player.getEquipo()) {
											if (p.getEstado().equals("Vivo")) {
												quedanVivos = true;
												break;
											}
										}

										if (!quedanVivos) {
											System.out.println("¡Te has quedado sin pokemons en tu equipo!");
											System.out.println("El Alto Mando te derrotó");
											System.out.println("Volviendo al menu...");
											batalla = false;
										}
										// Si le quedan vivos, el inicio del while
										// va a pedir que cambie de Pokemon automáticamente.
									}
								} else if (accionDeBatalla == 2){//Cambiar Pokemon
									System.out.println("\nElige un pokemon Vivo de tu equipo:");
									int auxCont = 1;
									for (Pokemon p : player.getEquipo()) {
										System.out.println(auxCont + ") " + p.getNombre() + " - " + p.getEstado());
										auxCont++;
									}
									System.out.print("> ");
									int eleccion = Integer.parseInt(sc.nextLine());
									Pokemon posibleCambio = player.getEquipo().get(eleccion - 1);

									if (posibleCambio.getEstado().equals("Vivo")) {
										pokemonJugador = posibleCambio;
										System.out.println("¡Regresa! " + player.getNombre() + " saca a "
												+ pokemonJugador.getNombre() + "!");
									} else {
										System.out.println("¡Ese Pokemon no puede pelear!");
									}

								}else if(accionDeBatalla == 3) {//Rendirse
									System.out.println("\nTe has rendido. ¡Vuelve cuando seas mas fuerte!");
									batalla = false;
								}
							}
						}
					}else {
						System.out.println("\nOpción no válida.");
					}
					if(altoMandoDerrotado == altoMandoMiembros.size()) {
						System.out.println("===================");
						System.out.println("¡Felicitaciones! ¡Eres el nuevo Campeón/a de la Liga Pokémon! \n¡Tu nombre y el de tu equipo quedarán grabados en el Hall de la Fama para siempre!");
						System.out.println("===================");
						player.getDerrotados().add("AltoMando");
					}
				}
				break;
			case 6:
				for (Pokemon p : player.getEquipo()) {
					p.setEstado("Vivo");
				}
				System.out.println("Tu equipo se ha recuperado!");
				break;
			case 7:
				guardarPartida(player);
				break;
			case 8:
				guardarPartida(player);
				System.out.println("Nos vemos entrenador...");
				System.exit(0);// Para matar el menu
			}
		} while (opcion != 8);
	}
	private static void leerAltoMando() {//Guardamos en objetos y en un ArrayList al alto mando que esta en Alto Mando.txt
		try {
			File arch = new File("datos/Alto Mando.txt");
			lector = new Scanner(arch);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				String[] partes = linea.split(";");
				// Info de miembro de alto mando
				int numero = Integer.parseInt(partes[0]);
				String nombre = partes[1].strip();
				// Como todos tienen 6 pokemon no tenemos que guardar cantidad de pokemon como con los gimnasios
				AltoMando altoMando = new AltoMando(numero, nombre);// Creamos miembro del alto mando
				// Guardamos el equipo
				for (int i = 0; i < 6; i++) {
					String nombrePokemon = partes[i + 1].strip();//Guardamos el nombre
					Pokemon poke = clonarPokemonPokedex(nombrePokemon);// Clonamos pokemon desde la Arraylist con los pokemon
					if (poke != null) {//Si existe el pokemon, se agrega a su equipo
						altoMando.getEquipo().add(poke);
					}
				}
				altoMandoMiembros.add(altoMando);//Y finalmente se agrega a una lista con todos los del alto mando
			}
		} catch (Exception e) {//Si el txt no se puede leer
			System.out.println("Error al intentar leer el archivo Alto Mando.txt");
		}
	}
	private static void leerGymLeaders() {//Guardamos en objetos y en un ArrayList al alto mando que esta en Gimnasios.txt
		try {
			File arch = new File("datos/Gimnasios.txt");
			lector = new Scanner(arch);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				String partes[] = linea.split(";");// No sabia que esto tmb funciona asi lol
				// Sacamos las cositas del lider
				int numero = Integer.parseInt(partes[0]);
				String lider = partes[1].strip();
				String estado = partes[2].strip();
				int tamañoEquipo = Integer.parseInt(partes[3]);
				// Creamos el gimnasio pero sin equipo
				Gimnasio gym = new Gimnasio(numero, lider, estado);
				// Ahora vamos a guardar su equipo
				for (int i = 0; i < tamañoEquipo; i++) {
					String nombrePokemon = partes[4 + i].trim();
					// Clonamos pokemon de la pokedex
					Pokemon poke = clonarPokemonPokedex(nombrePokemon);

					if (poke != null) {
						gym.getEquipo().add(poke); // Y se agrega el pokemon clonado al equipo del lider de gym
					}
				}
				lideresGym.add(gym);//Y se agrega a la Arraylist con todos los gimnasios
			}
		} catch (Exception e) {
			System.out.println("Error al intentar leer el archivo Gimnasios.txt");
		}
	}
	private static void leerPokedex() throws FileNotFoundException {//Se lee crea y guardan los pokemon
		File arch = new File("datos/Pokedex.txt");
		lector = new Scanner(arch);
		while (lector.hasNextLine()) {
			String linea = lector.nextLine();
			String[] partes = linea.split(";");
			//Informacion de los pokemon
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
			// Ya guardado toda la info ahora creamos el objeto con los atributos
			Pokemon nuevoPokemon = new Pokemon(nombre, habitat, porcentajeAparicion, vida, ataque, defensa,
					ataqueEspecial, defensaEspecial, velocidad, tipo);
			pokedexGlobal.add(nuevoPokemon);//Se agrega el pokemon a la pokedex que es un Arraylist
		}
	}

	private static Jugador cargarPartida() {
		File arch = new File("datos/Registros.txt");
		if (!arch.exists() || arch.length() == 0) {//Se comprueba si es que Registros.txt existe o si tiene texto
			return null;
		}
		try {
			Scanner lectorRegistro = new Scanner(arch);
			if (lectorRegistro.hasNextLine()) {
				// leer datos
				String linea = lectorRegistro.nextLine();
				String[] partes = linea.split(";");
				//Se crea un jugador con el nombre en Registros.txt
				Jugador player = new Jugador(partes[0]);

				for (int i = 1; i < partes.length; i++) {//Se guarda los gimnasios derrotados y alto mando derrotado
					player.getDerrotados().add(partes[i]);//Se agregan a una lista para luego contarlos para conseguir las medallas como un int
					if(partes[partes.length-1] == "Alto Mando") {//Para cuando llegue a leer el alto mando derrotado no llegue a leer lo de abajo y me de un error de index out of bounds
						break;
					}
					if (lideresGym.get(i - 1).getLider().equals(player.getDerrotados().get(i - 1))) {//Cambiamos el estado de los gimnasios segun si estan en Registros.txt
						lideresGym.get(i - 1).setEstado("Derrotado");
					}
				}
				player.setMedallas(player.getDerrotados().size());// Arraylist = size | Lista[] = lenght
				// Vemos que pokemons estan guardados
				while (lectorRegistro.hasNextLine()) {
					String lineaPokemon = lectorRegistro.nextLine();
					if (lineaPokemon.trim().isEmpty()) continue;//Si al leer no hay pokemons se sale del while
					String[] partesPokemon = lineaPokemon.split(";");
					String nombrePokemon = partesPokemon[0];
					String estadoPokemon = partesPokemon[1];
					//Info del pokemon
					Pokemon poke = clonarPokemonPokedex(nombrePokemon);//Clonamos el pokemon buscando su nombre en la pokedex

					if (poke != null) {//Si pokemon existe
						poke.setEstado(estadoPokemon);//Se le pone el estado con el que quedó guardado
						if (player.getEquipo().size() < 6) {//Añadimos los primeros 6 pokemons al equipo
							player.getEquipo().add(poke);
						} else {
							player.getPc().add(poke);//Y si ya se agregaron 6 agregamos el resto a la lista pc
						}
					}
				}
				lectorRegistro.close();//Se cierra el lector
				return player; // Retornamos objeto jugador armado
			}
			lectorRegistro.close();//Se cierra el lector
		} catch (Exception e) {
			System.out.println("Error al intentar leer el archivo Registros.txt");
		}
		return null;
	}

	private static Pokemon clonarPokemonPokedex(String nombrePokemon) {
		for (Pokemon p : pokedexGlobal) {//Buscamos los pokemons en la pokedex 
			if (p.getNombre().equalsIgnoreCase(nombrePokemon)) {//Si el nombre del pokemon de la pokedex es igual al que se buscó, se crea un objeto pokemon con los atributos guardados en la pokedex
				// Creamos el pokemon
				return new Pokemon(p.getNombre(), p.getHabitat(), p.getPorcentajeAparicion(), p.getVida(),
						p.getAtaque(), p.getDefensa(), p.getAtaqueEspecial(), p.getDefensaEspecial(), p.getVelocidad(),
						p.getTipo());
			}
		}
		return null;// Si el pokemon no existe en la pokedex
	}
	private static int obtenerIndiceTipo(String tipo) {
		for (int i = 0; i < tipos.length; i++) {
			if (tipos[i].equalsIgnoreCase(tipo.trim())) {
				return i;
			}
		}
		return 0;// Por error de tipeo en algun txt o lista
	}

	private static void guardarPartida(Jugador player) {
		try {
			// Archivo
			File arch = new File("datos/Registros.txt");
			// El FileWriter abre el txt y el false hace que sobrescriba en vez de añadir
			// texto
			FileWriter fw = new FileWriter(arch, false);
			BufferedWriter writer = new BufferedWriter(fw);
			// Escribimos el jugador y sus medallas en el txt
			writer.write(player.getNombre());
			for (String s : player.getDerrotados()) {// Me printea mi lista con los lideres de gimnasio derrotados
				writer.write(";" + s);
			}
			writer.newLine();// Salto de linea
			// Escribimos los Pokemons del equipo
			for (Pokemon p : player.getEquipo()) {
				writer.write(p.getNombre() + ";" + p.getEstado());
				writer.newLine();
			}
			// Escribimos los Pokemons del PC
			for (Pokemon p : player.getPc()) {
				writer.write(p.getNombre() + ";" + p.getEstado());
				writer.newLine();
			}
			writer.close();
			System.out.println("\nPartida guardada con exito!");
		} catch (IOException e) {
			System.out.println("Error: Hubo un problema de escritura al intentar guardar la partida.");
		}
	}

	private static Pokemon encontrarPokemon(String habitat) {
		ArrayList<Pokemon> posiblesEncuentros = new ArrayList<>();// Lista en donde guardamos los pokemon del habitat
																	// seleccionado
		for (Pokemon p : pokedexGlobal) {
			if (p.getHabitat().equalsIgnoreCase(habitat)) {
				posiblesEncuentros.add(p);
			}
		}
		Random random = new Random();
		double roll = random.nextDouble();// Un decimal aleatorio para buscar el pokemon

		double probTotal = 0.0;
		for (Pokemon p : posiblesEncuentros) {// Recorremos la lista hasta que la suma sea igual roll
			probTotal += p.getPorcentajeAparicion();

			if (roll <= probTotal) {
				return clonarPokemonPokedex(p.getNombre());// Volvemos a usar el metodo para que nos de una pokemon
															// copia
			}
		}
		return clonarPokemonPokedex(posiblesEncuentros.get(posiblesEncuentros.size() - 1).getNombre());// Por si falla
																										// buscarlo con
																										// los decimales
	}
}
