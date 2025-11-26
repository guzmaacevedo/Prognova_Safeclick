package prognova_safeclick.juego;

import java.util.Scanner;

public class Juego {

    private Jugador jugador;
    private BancoPreguntas banco;
    private Laberinto laberinto;

    public Juego(String nombreJugador) {
        this.jugador = new Jugador(nombreJugador);
        this.banco = new BancoPreguntas();
        this.laberinto = new Laberinto();
    }

    public void iniciar() {
        Scanner leer = new Scanner(System.in);
        System.out.println("--------------------------------------------------------");
        System.out.println("Tu posicion es 'J' y te deseo suerte para llegar a la meta 'F'");
        System.out.println("Muevete usando W A S D (y luego Enter)");
        System.out.println("Y cuidado, el laberinto esta lleno de enemigos");
        System.out.println("----------------------------------------------------");

        while (jugador.estaVivo()) {

            laberinto.mostrar();
            System.out.println("\nVidas: " + jugador.getVidas());
            System.out.print("Movimiento: ");

            String input = leer.nextLine().toLowerCase();

            if (input.isEmpty()) {
                continue;
            }

            char mov = input.charAt(0);

            boolean seMovio = laberinto.mover(mov);

            if (!seMovio) {
                System.out.println("NO PUEDES PASAR, ES UN MURO.");
                continue;
            }

            // -------------------------
            // Si encuentra enemigo → pregunta
            // -------------------------
            if (laberinto.hayChoque()) {
                System.out.println("\nUn enemigo te encontró, TERRIBLE");

                Pregunta p = banco.obtenerAleatoria();

                System.out.println("\nRETO:");
                System.out.println(p.getEnunciado());

                String[] ops = p.getOpciones();
                for (int i = 0; i < ops.length; i++) {
                    System.out.println((i + 1) + ". " + ops[i]);
                }

                int respuesta = -1;

                while (true) {
                    System.out.print("Tu respuesta (1-4): ");

                    try {
                        respuesta = Integer.parseInt(leer.nextLine()) - 1;

                        if (respuesta >= 0 && respuesta < 4) {
                            break; // respuesta válida
                        } else {
                            System.out.println("Opción invalida. Escribe un numero entre 1 y 4.");
                        }

                    } catch (Exception e) {
                        System.out.println("Ingresa un número valido.");
                    }
                }
                if (p.esCorrecta(respuesta)) {
                    System.out.println("Correcto. Superaste al enemigo.\n");
                } else {
                    System.out.println("Incorrecto. El enemigo te hirio.\n");
                    jugador.perderVida();
                }
                // Mover enemigo a un nuevo lugar aleatorio
                laberinto.colocarEnemigo();
            }

            // -------------------------
            // Si llega a la meta
            // -------------------------
            if (laberinto.llegoMeta()) {
                System.out.println("\nLLEGASTE A LA META");
                break;
            }
        }

        if (!jugador.estaVivo()) {
            System.out.println("\nTe quedaste sin vidas. Fin del juego.");
        }

        System.out.println("\nGracias por jugar SafeClick");
        leer.close();
    }
}
