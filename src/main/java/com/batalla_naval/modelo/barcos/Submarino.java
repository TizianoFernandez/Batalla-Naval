package com.batalla_naval.modelo.barcos;


public class Submarino extends ModeloBarco{
    private String nombre = "Submarino";
    private static final int TAMANIO_SUBMARINO = 3;
    
    public Submarino(){
        super(TAMANIO_SUBMARINO);
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }
}
