package com.batalla_naval.controlador;

import com.batalla_naval.modelo.partida.Juego;
import com.batalla_naval.vista.*;


public class Controlador {
    private Juego juego;
    private VistaInt vista;
    
    public Controlador(Juego juego, VistaInt vista){
        this.juego = juego;
        this.vista = vista;
    }
    
    public void partidaIA(){
        this.vista.bienvenida();
        String nombre = this.vista.pedirNombreJugador();
        juego.setPartidaIA(nombre);
        juego.getJugador1().addObservador(vista);
        juego.getJugador1().getTableroUsuario().addObservador(vista);
        boolean termino = false;
        int opcion;
        while(!termino){
            opcion = vista.mostrarMenu();
            
            while(opcion != 1 && opcion != 2){
                vista.opcionIncorrecta();
                opcion = vista.mostrarMenu();
            }
            
            switch(opcion){
                case 1:
                    vista.mostrarTableroVacio();
                    int barcoSeleccionado, x, y;
                    boolean vertical, colocado;
                    
                    while(juego.getJugador1().cuantosBarcos() != 0){
                        barcoSeleccionado = vista.pedirBarcoAColocar(juego.getJugador1());
                        x = vista.pedirX();
                        y = vista.pedirY();
                        vertical = vista.pedirVertical();
                        colocado = juego.getJugador1().colocarBarcoJugador(x, y, vertical, barcoSeleccionado-1);
                        if(!colocado){
                            vista.errorAlColocar();
                        }
                    }
                    
                    juego.getIA().colocarBarcoJugador(0, 0, false, 0);
                    juego.getJugador1().inicializarDisparosRecibidos();
                    
                    while(juego.getJugador1().getCantidadBarcos() > 0 && juego.getIA().getCantidadBarcos() > 0){
                        vista.mostrarTurno(juego.getTurnoActual());
                        
                        if(juego.getTurnoActual() == juego.getJugador1()){
                            x = vista.pedirX();
                            y = vista.pedirY();
                            juego.getTurnoActual().disparar(x, y, juego.getIA());
                            
                        }
                        else{
                            juego.getTurnoActual().disparar(0, 0, juego.getJugador1());
                        }
                        
                        juego.determinarTurno();
                    }
                    vista.mostrarGanador(juego.determinarGanador());
                    this.juego.reiniciarPartidaIA();
                    
                    break;
                case 2:
                    vista.mostrarSalir();
                    termino = true;
                    break;
                default:
                    break;
            }
        }
       
    }
}

