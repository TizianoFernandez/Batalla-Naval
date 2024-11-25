package com.batalla_naval.modelo.barcos;


public class Crucero extends ModeloBarco{
    private String nombre = "Crucero";
    private static final int TAMANIO_CRUCERO = 2;
    
    public Crucero(){
        super(TAMANIO_CRUCERO);
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }
}
