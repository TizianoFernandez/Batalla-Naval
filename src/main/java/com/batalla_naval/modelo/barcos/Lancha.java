package com.batalla_naval.modelo.barcos;


public class Lancha extends ModeloBarco{
    private String nombre = "Lancha";
    private static final int TAMANIO_LANCHA = 1;
    
    public Lancha(){
        super(TAMANIO_LANCHA);
    }
    
    @Override
    public String getNombre(){
        return this.nombre;
    }
}
