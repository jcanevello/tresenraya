package main;

import javax.swing.*;
import java.awt.*;

public class Competidor {

    /*Atributos del jugador.*/
    String nombre;
    public int GANADOS, PERDIDOS, EMPATADOS;

    /*Imagen de la ficha del jugador.*/
    private ImageIcon ficha;

    /** Crea un nuevo Jugador. */
    public Competidor(String nombre, String ruta) {

        /*Nombre del jugador.*/
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;

        /*Imagen por defecto.*/
        miFicha( ruta );
    }


    /** Crea un nuevo Jugador. */
    public Competidor(String nombre, ImageIcon imagen ) {

        /*Nombre del jugador.*/
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;

        /*Imagen por defecto.*/
        this.ficha = imagen;
    }

    /*Método que incrementa los juegos ganados.*/
    public void gano(){
        JOptionPane.showMessageDialog(null, "Ganaste!!!");
        GANADOS ++;
    }
    public void perdio(){
        JOptionPane.showMessageDialog(null, "Perdiste... :(");
        PERDIDOS ++;
    }
    public void empato(){
        JOptionPane.showMessageDialog(null, "Empate");
        EMPATADOS ++;
    }

    /*Método que devuelve la imagen de la ficha.*/
    public ImageIcon obtenFicha(){
        return ficha;
    }

    /*Método que establace la ficha.*/
    public void miFicha(String ruta){
        this.ficha = new ImageIcon ( this.getClass().getResource(ruta) );
    }

}
