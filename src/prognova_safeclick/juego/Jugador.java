package prognova_safeclick.juego;

/**
 *
 * @author domtr
 */
public class Jugador {
      
    private String nombre;
    private int vidas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.vidas = 3; // Lo normal, casi siempre se empieza con 3 vidas
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidas() {
        return vidas;
    }

    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    public boolean estaVivo() {
        return vidas > 0;
    }
}