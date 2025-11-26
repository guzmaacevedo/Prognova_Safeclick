package frontend;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPrincipal extends JPanel {

    public MenuPrincipal(JFrame ventana) {
        
        setLayout(new GridLayout(3, 1, 15, 15));

        JButton facil = new JButton("Nivel Fácil");
        JButton dificil = new JButton("Nivel Difícil");
        JButton salir = new JButton("Salir");

        add(facil);
        add(dificil);
        add(salir);
        
        //acciones de estos botones
        facil.addActionListener(e -> iniciarJuego(ventana, "facil"));
        dificil.addActionListener(e -> iniciarJuego(ventana, "dificil"));
        salir.addActionListener(e -> System.exit(0));
    }

    private void iniciarJuego(JFrame ventana, String dificultad) {
        GamePanel panel = new GamePanel(dificultad);
        ventana.setContentPane(panel);
        ventana.revalidate();
        panel.requestFocus();
    }
}
