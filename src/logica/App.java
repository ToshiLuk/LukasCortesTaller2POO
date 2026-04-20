package logica;

import java.util.InputMismatchException;
import java.util.Scanner;

import dominio.Jugador;

public class App {
	public static Scanner sc = new Scanner(System.in);
	public static Scanner lector;
	public static void main(String[] args) {
		int opcion = 0;
		boolean opcionValida = false; //Controla el bucle
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
		switch(opcion) {
		case 1:
			break;
		case 2:
			System.out.print("Ingrese su Apodo: ");
			String nombre = sc.nextLine();
			Jugador player = new Jugador(nombre);
			//Algo para crear al jugador como objeto y asi
			menuDeJuego(player);
			break;
		case 3:
			break;
		default:
			break;
		}
		}while(opcion != 3);
	}
	private static void menuDeJuego(Jugador player) {
		int opcion = 0;
		do {
			System.out.println(player + ", que deseas hacer?");
		}while(opcion != 7 || opcion != 8);
		
	}

}
