package frontend;

import javax.swing.*;
import java.awt.*;
import prognova_safeclick.juego.Jugador;
import prognova_safeclick.juego.Laberinto;

public class GamePanel extends JPanel {

    private Laberinto laberinto;
    private final int TILE = 32;
    private String dificultad;
    private Jugador jugador;

    private Image imgJugador;
    private Image imgMuro;
    private Image imgPiso;
    private Image imgMeta;
    private Image imgEnemigo;

    public Laberinto getLaberinto() {
        return laberinto;
    }

    public GamePanel(String dificultad) {
        laberinto = new Laberinto(dificultad);
        jugador = new Jugador("Player");

        setFocusable(true);
        addKeyListener(new KeyController(laberinto, jugador, this));
        cargarImagenes();
    }

    private void cargarImagenes() {
        try {
            imgJugador = new ImageIcon(getClass().getResource("/frontend/img/per44.png")).getImage();
            imgMuro = new ImageIcon(getClass().getResource("/frontend/img/muro.png")).getImage();
            imgPiso = new ImageIcon(getClass().getResource("/frontend/img/piso1.png")).getImage();
            imgMeta = new ImageIcon(getClass().getResource("/frontend/img/meta.png")).getImage();
           imgEnemigo = new ImageIcon(getClass().getResource("/frontend/img/ene.jpg")).getImage();

        } catch (Exception e) {
            System.out.println("Error cargando imágenes: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        char[][] mapa = laberinto.getMapa();

        int tileSize = 30; // tamaño de los cuadros que tenemos

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {

                char c = mapa[i][j];

                // Dibuja piso siempre primero
                g.drawImage(imgPiso, j * tileSize, i * tileSize, tileSize, tileSize, null);

                if (c == '#') {
                    g.drawImage(imgMuro, j * tileSize, i * tileSize, tileSize, tileSize, null);
                }

                if (c == 'F') {
                    g.drawImage(imgMeta, j * tileSize, i * tileSize, tileSize, tileSize, null);
                }

            }
        }
        // Dibujar enemigos visibles
        for (int[] e : laberinto.getEnemigos()) {
            g.drawImage(
                    imgEnemigo,
                    e[1] * tileSize,
                    e[0] * tileSize,
                    tileSize,
                    tileSize,
                    null
          );
      }

        // jugador
        g.drawImage(imgJugador,
                laberinto.getJugadorY() * tileSize,
                laberinto.getJugadorX() * tileSize, tileSize, tileSize,
                null
        );

        // Mostrar vidas en pantalla
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Vidas: " + jugador.getVidas(), 10, getHeight() - 10);

    }
}
