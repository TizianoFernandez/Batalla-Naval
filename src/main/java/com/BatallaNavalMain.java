package com;
import com.batalla_naval.controlador.Controlador;
import com.batalla_naval.modelo.partida.Juego;
import com.batalla_naval.vista.VistaConsola;
import com.batalla_naval.vista.VistaInt;

public class BatallaNavalMain {

    public static void main(String[] args) {

        Juego j = new Juego();
        VistaInt vista = new VistaConsola();
        Controlador c = new Controlador(j, vista);
        
        c.partidaIA();
   
    }
}
