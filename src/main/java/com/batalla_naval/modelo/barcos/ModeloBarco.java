package com.batalla_naval.modelo.barcos;


public abstract class ModeloBarco {
    private int x;
    private int y; 
    private final int longitud;
    private int piezasRestantes; //Cantidad de partes del barco que no fueron impactadas
    private boolean esVertical; //True = esta en vertical, False = horizontal
    
    
    public ModeloBarco(int longitud){
        this.longitud = longitud;
        this.piezasRestantes = longitud;
    }
    
    /*
        GETTERS
    */
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getLongitud(){
        return this.longitud;
    }
    
    public boolean getVertical(){
        return this.esVertical;
    }
    
    public int getPiezasRestantes(){
        return this.piezasRestantes;
    }
    
    public abstract String getNombre();
    
    /*
        SETTERS
    */
    
    public void setPosicion(int x, int y, boolean vertical){
        this.x = x;
        this.y = y;
        this.esVertical = vertical;
    }
    
    /*
        FUNCIONES
    */
    
    public boolean estaHundido(){
        return this.piezasRestantes == 0;
    }
    
    public void impactar(){
        if(this.piezasRestantes > 0){
            --this.piezasRestantes;
        }
    }
}
