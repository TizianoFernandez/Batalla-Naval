package com.batalla_naval.modelo.partida;
import com.batalla_naval.modelo.jugador.*;
import com.batalla_naval.modelo.recursos.ResultadoDisparo;


public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private IA ia;
    private Usuario turnoActual;
    
    
    public Juego(){
        this.jugador1 = null;
        this.jugador2 = null;
        this.turnoActual = null;
    }
    
    
    public void setPartidaIA(String nombre){
        this.jugador1 = new Jugador(nombre);
        this.ia = new IA();
        
        this.jugador1.reiniciarJugador();
        this.turnoActual = this.jugador1;

        this.ia.reiniciarIA();
    }
    
    public void reiniciarPartidaIA(){
        this.jugador1.reiniciarJugador();
        this.turnoActual = this.jugador1;

        this.ia.reiniciarIA();
    }
    
    public void cargarBarcosJugadores(Usuario jugador){
        jugador.cargarBarcos();
    }
    
    
    public Usuario determinarGanador(){
        if(this.ia == null){
            if(this.jugador1.getCantidadBarcos() == 0){
                return this.jugador2;
            }
            else{
                return this.jugador1;
            }
        }else{
            if(this.jugador1.getCantidadBarcos() == 0){
                return this.ia;
            }
            else{
                return this.jugador1;
            }
        }
    }
    
    
    public void determinarTurno(){
        if(this.ia == null){
            if(this.turnoActual.getUltimoDisparo() == ResultadoDisparo.AGUA || this.turnoActual.getUltimoDisparo() == ResultadoDisparo.PIEZA_ROTA){
                if(this.turnoActual == this.jugador1){
                    this.turnoActual = jugador2;
                }
                else{
                    this.turnoActual = jugador1;
                }
            }
        }else{
            if(this.turnoActual.getUltimoDisparo() == ResultadoDisparo.AGUA || this.turnoActual.getUltimoDisparo() == ResultadoDisparo.PIEZA_ROTA){
                if(this.turnoActual == this.jugador1){
                    this.turnoActual = ia;
                }
                else{
                    this.turnoActual = jugador1;
                }
            }
        }
    }
    
    
    public boolean verificarCoordenandas(int x, int y){
        return x < 0 || x >= 10 || y < 0 || y >= 10;
    }
    
    
    
    public Jugador getJugador1(){
        return this.jugador1;
    }
    
    public Jugador getJugador2(){
        return this.jugador2;
    }
    
    public IA getIA(){
        return this.ia;
    }
    
    public Usuario getTurnoActual(){
        return this.turnoActual;
    }
}
