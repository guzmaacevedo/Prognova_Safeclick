package frontend;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import prognova_safeclick.juego.BancoPreguntas;
import prognova_safeclick.juego.Jugador;
import prognova_safeclick.juego.Laberinto;
import prognova_safeclick.juego.Pregunta;

public class KeyController extends KeyAdapter {

    private Laberinto laberinto;
    private GamePanel panel;
    private BancoPreguntas banco = new BancoPreguntas();
    private Jugador jugador;

    public KeyController(Laberinto laberinto, Jugador jugador, GamePanel panel) {
        this.laberinto = laberinto;
        this.jugador = jugador;
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                laberinto.mover('w');
                break;
            case KeyEvent.VK_S:
                laberinto.mover('s');
                break;
            case KeyEvent.VK_A:
                laberinto.mover('a');
                break;
            case KeyEvent.VK_D:
                laberinto.mover('d');
                break;
        }
        if (laberinto.llegoMeta()) {
            JOptionPane.showMessageDialog(
                    null,
                    "🎉 ¡Felicidades! Has llegado a la meta y completado el laberinto.",
                    "¡Has ganado!",
                    JOptionPane.INFORMATION_MESSAGE
            );

            volverAlMenu(); // regresa al menú principal
            return;
        }

        panel.repaint();
        if (laberinto.hayChoque()) {
            mostrarPregunta();
            laberinto.colocarEnemigo(); // enemigo se mueve a otra parte
        }

        panel.repaint();
    }

    private void mostrarPregunta() {
        Pregunta p = banco.obtenerAleatoria();

        boolean respuestaValida = false;
        int r = -1;

        while (!respuestaValida) {

            // Construir mensaje
            String mensaje = "Vidas: " + jugador.getVidas() + "\n\n";
            mensaje += p.getEnunciado() + "\n\n";

            for (int i = 0; i < p.getOpciones().length; i++) {
                mensaje += (i + 1) + ". " + p.getOpciones()[i] + "\n";
            }

            String respuesta = JOptionPane.showInputDialog(
                    null,
                    mensaje,
                    "Reto de Ciberseguridad",
                    JOptionPane.QUESTION_MESSAGE
            );

            // Si presiona Cancelar o la x, pues no permitir escapar
            if (respuesta == null) {
                JOptionPane.showMessageDialog(null,
                        "Debes responder para continuar.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                continue;
            }

            try {
                r = Integer.parseInt(respuesta) - 1;

                if (r < 0 || r > 3) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Opción no válida. Introduce un número entre 1 y 4.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    continue;
                }

                respuestaValida = true;

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Entrada inválida. Debes escribir un número.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // Evaluar respuesta
        if (r == p.getRespuestaCorrecta()) {
            JOptionPane.showMessageDialog(
                    null,
                    "✔️ Respuesta correcta. ¡Bien hecho!",
                    "Correcto",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            jugador.perderVida();

            if (jugador.getVidas() <= 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "💀 Te has quedado sin vidas.\nHas perdido el juego.",
                        "Derrota",
                        JOptionPane.ERROR_MESSAGE
                );
                System.exit(0);
            }

            JOptionPane.showMessageDialog(
                    null,
                    "❌ Respuesta incorrecta.\nVidas restantes: " + jugador.getVidas(),
                    "Incorrecto",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void volverAlMenu() {
        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(panel);
        ventana.setContentPane(new MenuPrincipal(ventana));
        ventana.revalidate();
        ventana.repaint();
    }

}
