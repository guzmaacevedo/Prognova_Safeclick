/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prognova_safeclick.juego;

/**
 *
 * @author domtr
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BancoPreguntas {

    private List<Pregunta> preguntas;
    private Random random;

    public BancoPreguntas() {
        preguntas = new ArrayList<>(); //se crea la lista de preguntas, suaveee
        random = new Random(); //pa seleccionar preguntas random cada vez

        cargarPreguntas();
    }

    private void cargarPreguntas() {

        preguntas.add(new Pregunta(
                "Un correo desconocido aparece en tu bandeja con un archivo adjunto inesperado."
                        + "\nElige la acción más segura:",
                new String[]{
                        "Descargarlo para revisarlo luego",
                        "Responder agradeciendo el envío",
                        "Ignorar o reportar el mensaje",
                        "Compartir el archivo con amigos"
                },
                2
        ));

        preguntas.add(new Pregunta(
                "Alguien te envía un enlace acortado por redes sociales sin explicación."
                        + "\nActúa de forma segura:",
                new String[]{
                        "Abrir el enlace para ver que es",
                        "Preguntar qué contiene antes de abrir",
                        "Hacer click rapido para no perder la oferta",
                        "Reenviar el enlance a otros"
                },
                1
        ));

        preguntas.add(new Pregunta(
                "En un café público, encuentras una red Wi-Fi abierta sin contraseña."
                        + "\nSelecciona la acción más segura:",
                new String[]{
                        "Conectarte para revisar tus cuentas bancaria",
                        "Usarla solo para navegar sitios de confianza",
                        "Evitar conectarte a redes desconocidas",
                        "Usarla y compartir archivos"
                },
                2
        ));

        preguntas.add(new Pregunta(
                "Tu amigo te pide tu contraseña para ‘resolver un problema en tu cuenta’."
                        + "\nDecide correctamente:",
                new String[]{
                        "Compartirla en persona para que te ayude",
                        "Cambiar la contraseña y darsela mientras te ayuda",
                        "Enviar la contraseña por alguna red social",
                        "Negarte y que mejor te ayude soporte oficial"
                },
                3
        ));
        
        preguntas.add(new Pregunta(
                "Ves un mensaje que dice: ‘Tu dispositivo está infectado. Descarga esta app para limpiar el virus’."
                        + "\nPara mantenerte seguro:",
                new String[]{
                    "Instalar la app recomendada",
                    "Ignorar el mensaje y usar tiendas oficiales",
                    "Compartir el mensaje para alertar amigos y familiares",
                    "Abrir varios enlances para confirmar"
                },
                1
        ));
    }

    public Pregunta obtenerAleatoria() {
        return preguntas.get(random.nextInt(preguntas.size()));
    }
}
