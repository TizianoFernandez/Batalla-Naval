package com.batalla_naval.modelo.tablero;

import com.batalla_naval.modelo.barcos.ModeloBarco;
import com.batalla_naval.modelo.barcos.BarcoRoto;
import java.util.ArrayList;
import com.batalla_naval.observer.*;
import com.batalla_naval.modelo.recursos.ResultadoDisparo;


public class Tablero implements Observable{
    private ModeloBarco[][] tablero; //10x10 tipo ModeloBarco. Null = agua.
    private BarcoRoto piezaRota;
    private ArrayList<Observador> observador;
    
    
    public Tablero(){
        this.observador = new ArrayList<>();
        this.tablero = new ModeloBarco[10][10];
        this.piezaRota = new BarcoRoto();
    }
    
    
    @Override
    public void notificar(){
        for(Observador obs: observador){
            obs.actualizarTablero(this.tablero);
        }
    }
    
    public void addObservador(Observador obs){
        this.observador.add(obs);
    }
    
    public boolean verificarBarco(ModeloBarco b){
        int x = b.getX();
        int y = b.getY();
        int longitud = b.getLongitud();
        boolean esVertical = b.getVertical();
        
        //VERIFICAR QUE NO EXCEDA LOS LIMITES DEL TABLERO
        
        if(esVertical){
            if(x + longitud > 10){
                return false;
            }
        }
        else{
            if(y + longitud > 10){
                return false;
            }
        }
        
        
        //VERIFICAR QUE NO HAYA UN BARCO COLOCADO EN ESA POSICION
        
        for(int i = 0; i < longitud; i++){
            if(esVertical){
                if(tablero[x + i][y] != null){
                    return false;
                }
            }
            else{
                if(tablero[x][y + i] != null){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean colocarBarco(ModeloBarco b){
        if(this.verificarBarco(b) == false) return false; //VERIFICAR SI PUEDO COLOCAR EL BARCO
        
        int x = b.getX();
        int y = b.getY();
        boolean esVertical = b.getVertical();
        
        for(int i = 0; i < b.getLongitud(); i++){
            if(esVertical){
                tablero[x + i][y] = b;
            }
            else{
                tablero[x][y + i] = b;
            }
        }
        this.notificar();
        return true;
    }
    
    
    public ResultadoDisparo verificarDisparo(int x, int y){
        if(tablero[x][y] == null){
            return ResultadoDisparo.AGUA;
        }
        if(tablero[x][y] == piezaRota){
            return ResultadoDisparo.PIEZA_ROTA;
        }
        if(!tablero[x][y].estaHundido()){
            return ResultadoDisparo.TOCADO;
        }
        return ResultadoDisparo.HUNDIDO;
    }
    
    
    public ResultadoDisparo disparo(int x, int y){
       
        ResultadoDisparo resultado = this.verificarDisparo(x, y);

        switch (resultado) {
            case AGUA -> {
                return ResultadoDisparo.AGUA; 
            }
            case PIEZA_ROTA -> {
                return ResultadoDisparo.PIEZA_ROTA; 
            }
            case TOCADO -> {
                ModeloBarco b = tablero[x][y];
                b.impactar();
                tablero[x][y] = piezaRota; 
                if (b.estaHundido()) {
                    return ResultadoDisparo.HUNDIDO;
                }
                return ResultadoDisparo.TOCADO; 
            }
            default -> {
                return ResultadoDisparo.AGUA;
            }
        }
        
    }
    
    public void reiniciarTablero(){
        this.tablero = new ModeloBarco[10][10];
    }
    
    
    public ModeloBarco[][] getTablero(){
        return this.tablero;
    }
}
