package prognova_safeclick.juego;

/**
 *
 * @author domtr
 */
public class Pregunta {

    private String enunciado;
    private String[] opciones;
    private int respuestaCorrecta; //Un # (0-3) indicando cuál es la correcta


    public Pregunta(String enunciado, String[] opciones, int respuestaCorrecta) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public boolean esCorrecta(int opcionUsuario) {
        return opcionUsuario == respuestaCorrecta;
    }
}
