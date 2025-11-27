package prognova_safeclick.juego;

/**
 *
 * @author domtr
 */
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Laberinto {

    private char[][] mapa;
    private int jugadorX, jugadorY;
    private List<int[]> enemigos;
    private int cantidadEnemigos;

    public Laberinto() {
        crearMapa();
        crearMapaD();
        colocarJugador();
        colocarEnemigo();
    }

    public Laberinto(String dificultad) {

        if (dificultad.equalsIgnoreCase("facil")) {
            crearMapa();
        } else {
            crearMapaD();  // el mapa grande
        }

        colocarJugador();
        enemigos = new ArrayList<>();
        if (dificultad.equalsIgnoreCase("facil")) {
            cantidadEnemigos = 6;
        } else {
            cantidadEnemigos = 8;
        }

        colocarEnemigo();

    }

    private void crearMapa() {
        mapa = new char[][]{
            "################".toCharArray(),//16x15
            "#    ## #      #".toCharArray(),
            "## # ##   #### #".toCharArray(),
            "## # ## #####  #".toCharArray(),
            "#  # ## ##### ##".toCharArray(),
            "## #    #####  #".toCharArray(),
            "## ######### # #".toCharArray(),
            "## #     ### # #".toCharArray(),
            "##   # ####### #".toCharArray(),
            "## ###   ### # #".toCharArray(),
            "#    ###     # #".toCharArray(),
            "# ## ####### # #".toCharArray(),
            "# ## ##      # #".toCharArray(),
            "##    #F########".toCharArray(),
            "################".toCharArray(),};
    }

    private void crearMapaD() {
        mapa = new char[][]{
            "#####################".toCharArray(),
            "#     ##     #     ##".toCharArray(),
            "# ### ## ### # ### ##".toCharArray(),
            "# #      #     #   ##".toCharArray(),
            "# # #### # ####### ##".toCharArray(),
            "# # #    #       # ##".toCharArray(),
            "# # # #### ##### # ##".toCharArray(),
            "#   #    #   #   # ##".toCharArray(),
            "##### ####### # ### #".toCharArray(),
            "#   #       # #     #".toCharArray(),
            "# # ### ### # ##### #".toCharArray(),
            "# #       # #     # #".toCharArray(),
            "# ####### # ##### # #".toCharArray(),
            "#       # #     #   #".toCharArray(),
            "### ### # ####### ###".toCharArray(),
            "#   #   #       #   #".toCharArray(),
            "# # # ######### # # #".toCharArray(),
            "# #   #       # # # #".toCharArray(),
            "# ### ### ### ### # #".toCharArray(),
            "#     #F            #".toCharArray(),
            "#####################".toCharArray()
        };
    }

    private void colocarJugador() {
        jugadorX = 1;
        jugadorY = 1;
    }

    public void colocarEnemigo() {
        Random rand = new Random();
        enemigos.clear();
        for (int i = 0; i < cantidadEnemigos; i++) {
            int enemigoX, enemigoY;

            while (true) {
                enemigoX = rand.nextInt(13) + 1;  // entre 1 y 13 (filas dentro de los muros)
                enemigoY = rand.nextInt(14) + 1;  // entre 1 y 14 (columnas dentro de los muros)

                if (mapa[enemigoX][enemigoY] != '#' && mapa[enemigoX][enemigoY] != 'F' && !(enemigoX == jugadorX && enemigoY == jugadorY)) {
                    enemigos.add(new int[]{enemigoX, enemigoY});
                    break;
                }
            }
        }
    }

    public void mostrar() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {

                // Jugador
                if (i == jugadorX && j == jugadorY) {
                    System.out.print('J');
                    continue;
                }

                // Enemigos invisibles
                boolean esEnemigo = false;
                for (int[] e : enemigos) {
                    if (i == e[0] && j == e[1]) {
                        System.out.print(' ');
                        esEnemigo = true;
                        break;
                    }
                }
                if (esEnemigo) {
                    continue;
                }

                // Cualquier otra cosa
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    public boolean mover(char direccion) {
        int nuevoX = jugadorX;
        int nuevoY = jugadorY;

        switch (direccion) {
            case 'w':
                nuevoX--;
                break; // arriba
            case 's':
                nuevoX++;
                break; // abajo
            case 'a':
                nuevoY--;
                break; // izquierda
            case 'd':
                nuevoY++;
                break; // derecha
            default:
                return false;
        }

        if (mapa[nuevoX][nuevoY] != '#') {
            jugadorX = nuevoX;
            jugadorY = nuevoY;
            return true;
        }
        return false;
    }

    public boolean hayChoque() {
        for (int[] e : enemigos) {
            if (jugadorX == e[0] && jugadorY == e[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean llegoMeta() {
        return mapa[jugadorX][jugadorY] == 'F';
    }

    // =======================
//        GETTERS
// =======================
    public char[][] getMapa() {
        return mapa;
    }

    public int getJugadorX() {
        return jugadorX;
    }

    public int getJugadorY() {
        return jugadorY;
    }

    public List<int[]> getEnemigos() {
        return enemigos;
    }

}
