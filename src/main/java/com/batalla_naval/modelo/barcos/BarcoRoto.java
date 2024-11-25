package com.batalla_naval.modelo.barcos;


public class BarcoRoto extends ModeloBarco{
    private String nombre = "Barco Roto";
    private static final int TAMANIO_PIEZA = 1;

    public BarcoRoto(){
        super(TAMANIO_PIEZA);
    }

    @Override
    public String getNombre(){
        return this.nombre;
    }
    
}
