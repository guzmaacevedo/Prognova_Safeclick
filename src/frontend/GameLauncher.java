
package frontend;

import javax.swing.*;

public class GameLauncher {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("SafeClick");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(700, 700);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);

        // INSTRUCCIONES 
        JOptionPane.showMessageDialog(
                null,
                "¡Bienvenido a SafeClick!\n\n" +
                        "Instrucciones:\n" +
                        "• Usa W, A, S y D para moverte por el laberinto.\n" +
                        "• Cuidado, hay enemigos ocultos.\n" +
                        "• Si chocas con uno, deberás responder un reto de ciberseguridad.\n" +
                        "• Llegar a la meta (F) para ganar.\n\n" +
                        "¡Buena suerte!",
                "Instrucciones del Juego",
                JOptionPane.INFORMATION_MESSAGE
        );

        ventana.setContentPane(new MenuPrincipal(ventana));

        ventana.setVisible(true);
    }
}

