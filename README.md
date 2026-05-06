# Taller 02: Simulador de Combates Pokémon (POO)

## Descripción del Proyecto
Este repositorio contiene la entrega del **Taller 02** para la asignatura de ITI - ICCI (I Semestre - 2026). 

El proyecto es un simulador interactivo basado en Pokémon. Permite a los usuarios crear una partida, capturar criaturas en diversos hábitats naturales respetando sus probabilidades de aparición, gestionar su equipo y almacenamiento en el PC, y enfrentarse a los 8 Líderes de Gimnasio. El objetivo final es superar el desafío consecutivo del Alto Mando para coronarse como Campeón.

El sistema fue desarrollado utilizando **Programación Orientada a Objetos (POO)** en Java, asegurando alta cohesión y bajo acoplamiento mediante la separación del código en paquetes de `logica` y `dominio`.

## Características Técnicas
* **Persistencia de Datos:** El juego guarda y carga el progreso del jugador (apodo, medallas, equipo y estado de los Pokémon) mediante la lectura y sobreescritura de archivos de texto plano (`Registros.txt`).
* **Colecciones Dinámicas:** Uso extensivo de `ArrayList` para manejar los equipos dinámicos de los personajes y el sistema de almacenamiento del PC.
* **Sistema de Combate:** Lógica algorítmica de batallas que suma las estadísticas base de los Pokémon y aplica multiplicadores de daño dinámicos ($x2.0$, $x1.0$, $x0.5$, $x0.0$) consultando una matriz bidimensional de Tabla de Tipos.
* **Probabilidad (RNG):** Algoritmo de "ruleta de probabilidades" para determinar los encuentros salvajes basados en porcentajes reales de aparición.

## Estructura del Proyecto
El código fuente está dividido en dos paquetes principales:
* `dominio/`: Contiene las clases que modelan las entidades del juego (`Jugador`, `Pokemon`, `Gimnasio`, `AltoMando` y `TablaTipos`).
* `logica/`: Contiene la clase `App.java` que maneja el ciclo de vida de la aplicación, los menús de usuario y la carga de archivos.

## Integrante
* **Lukas Cortes Alfaro** - [@ToshiLuk](https://github.com/ToshiLuk)
