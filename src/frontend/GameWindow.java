/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import javax.swing.*;

/**
 *
 * @author domtr
 */
public class GameWindow extends JFrame {

    public GameWindow(String dificultad) {
        
        // Crear el panel del juego con la dificultad elegida
        GamePanel panel = new GamePanel(dificultad);

        
        int tile = 32;
        int width = panel.getLaberinto().getMapa()[0].length * tile + 50;
        int height = panel.getLaberinto().getMapa().length * tile + 100;

        setTitle("SafeClick ");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        add(panel);

        setVisible(true);

        JOptionPane.showMessageDialog(
                this,
                "¡Bienvenido a SafeClick!\n\n"
                + "Instrucciones:\n"
                + "• Usa W, A, S, D para moverte por el laberinto.\n"
                + "• Cuidado, hay enemigos invisibles y puedes encontrarte con alguno.\n"
                + "• Si los tocas, responderás un reto.\n"
                + "• Llega a la meta (F) para ganar.\n\n"
                + "¡Buena suerte!",
                "Instrucciones del Juego",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
