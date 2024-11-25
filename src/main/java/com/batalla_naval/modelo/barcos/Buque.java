package com.batalla_naval.modelo.barcos;


public class Buque extends ModeloBarco {
    private String nombre = "Buque";
    private static final int TAMANIO_BUQUE = 4;
    
    public Buque(){
        super(TAMANIO_BUQUE);
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }
}
