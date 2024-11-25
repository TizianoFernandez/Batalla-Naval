package com.batalla_naval.modelo.barcos;


public class Portaaviones extends ModeloBarco{
    private String nombre = "Portaaviones";
    private static final int TAMANIO_PORTAAVIONES = 5;
    
    public Portaaviones(){
        super(TAMANIO_PORTAAVIONES);
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }
}
