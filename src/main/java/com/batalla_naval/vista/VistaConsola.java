package com.batalla_naval.vista;

import com.batalla_naval.modelo.barcos.*;
import com.batalla_naval.modelo.jugador.Usuario;
import com.batalla_naval.modelo.recursos.ResultadoDisparo;
import java.util.Scanner;

public class VistaConsola implements VistaInt{
    private Scanner sc = new Scanner(System.in);;
    private final String[] filas = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    
    
    @Override
    public void actualizarTablero(ModeloBarco[][] tablero){
        this.mostrarTablero(tablero);
    }
    
    
    @Override
    public void actualizarDisparoRecibido(ResultadoDisparo ultimoRecibido, int xRecibido, int yRecibido, String[][] tableroDisparos){
        this.actualizarRecibido(ultimoRecibido, xRecibido, yRecibido, tableroDisparos);
    }
    
    
    @Override
    public void actualizarDisparo(ResultadoDisparo resultado, int x, int y, String[][] tablero){
        this.actualizarTableroEnemigo(resultado, x, y, tablero);
    }
    
    
    @Override
    public void mostrarTablero(ModeloBarco[][] tablero) {
        System.out.print("   ");
        for (int col = 1; col <= 10; col++) {
            System.out.printf("% d ", col);
        }
        System.out.println();

        String filaLetra;
        for (int i = 0; i < 10; i++) {
            filaLetra = this.filas[i];
            System.out.print(filaLetra + "  ");
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == null) {
                    System.out.print(" ~ ");
                } else if (tablero[i][j] instanceof BarcoRoto) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" B ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    
    @Override
    public void mostrarTableroEnemigo(String[][] tablero) {
        System.out.print("   ");
        for (int col = 1; col <= 10; col++) {
            System.out.printf("% d ", col);
        }
        System.out.println();
        
        String filaLetra;
        for(int i = 0; i < 10; i++){
            filaLetra = this.filas[i];
            System.out.print(filaLetra + "  ");
            for(int j = 0; j < 10; j++){
                System.out.printf("%s", tablero[i][j]);
            }
            System.out.println("");
        }
        System.out.println();
    }
    
    
    @Override
    public void actualizarTableroEnemigo(ResultadoDisparo resultado, int x, int y, String[][] tablero){
        
        switch (resultado){
            case AGUA -> {
                tablero[x][y] = " ~ ";
                this.mostrarTableroEnemigo(tablero);
                System.out.println("Resultado: AGUA");
                this.esperar(2000);
                
            }
            case TOCADO -> {
                tablero[x][y] = " X ";
                this.mostrarTableroEnemigo(tablero);
                System.out.println("Resultado: TOCADO");
                this.esperar(2000);
            }
            case PIEZA_ROTA -> {
                tablero[x][y] = " X ";
                this.mostrarTableroEnemigo(tablero);
                System.out.println("Resultado: PIEZA ROTA");
                this.esperar(2000);
            }
            case HUNDIDO -> {
                tablero[x][y] = " X ";
                this.mostrarTableroEnemigo(tablero);
                System.out.println("Resultado: HUNDIDO");
            }
            default -> {
                tablero[x][y] = " ~ ";
                this.mostrarTableroEnemigo(tablero);
                System.out.println("Resultado: AGUA");
                this.esperar(2000);
            }
        }
        
    }
    
    @Override
    public int pedirX(){
        System.out.println();
        System.out.print("Ingrese la coordenada de la fila (A-J): ");
        
        String respuesta;
        while(true){
            respuesta = sc.next().toUpperCase();
            
            for(int i = 0; i < this.filas.length; i++){
                if(respuesta.equals(this.filas[i])){
                    return i;
                }
            }
            
            System.out.println("Coordenada invalida, ingresar una letra entre A y J. ");
        }
        
    }
    
    @Override
    public int pedirY(){
        int numero;
        System.out.println();
        System.out.print("Ingrese la coordenada de la columna (1-10): ");
        while(true){
            while(!sc.hasNextInt()){
                System.out.println("Ingrese un numero correcto: ");
                sc.next();
            }
            
            numero = sc.nextInt();
            
            if(numero < 1 || numero > 10){
                System.out.println("Numero invalido, ingresar un numero entre 1 y 10. ");
            }
            else{
                break;
            }
        }
        return numero - 1;
    }
    
    
    @Override
    public boolean pedirVertical() {
        System.out.println();
        System.out.print("El barco va en vertical? (s/n): ");
        String respuesta;
        while(true){
            respuesta = sc.next().toLowerCase();
            
            if(respuesta.equals("s")){
                return true;
            }
            else if(respuesta.equals("n")){
                return false;
            }
            else{
                System.out.println("Respuesta incorrecta, ingrese 's' o 'n'.");
            }
        }
    }
    
    
    @Override
    public int pedirBarcoAColocar(Usuario usuario){
        this.mostrarBarcosJugador(usuario.getBarcos());
        System.out.println();
        System.out.print("Ingrese el numero del barco que desea colocar: ");
        
        while(true){
            while(!sc.hasNextInt()){
                System.out.print("\nIngrese un numero correcto: ");
                sc.next();
            }
            
            int numero = sc.nextInt();
            
            if(numero < 1 || numero > usuario.cuantosBarcos()){
                System.out.println("\nNumero invalido, ingresar un numero entre 1 y " + usuario.cuantosBarcos());
            }
            else{
                return numero;
            }
        }
    }
    
    
    @Override
    public void mostrarBarcosJugador(ModeloBarco[] barcos){
        int ind = 1;
        System.out.println();
        System.out.println("BARCOS DISPONIBLES:");
        for (ModeloBarco barco : barcos) {
            if (barco != null) {
                System.out.printf("%d- %s (%d casillas)\n", ind, barco.getNombre(), barco.getLongitud());
                ind++;
            }
        }
    }
    
    
    @Override
    public String pedirNombreJugador(){
        System.out.print("Ingrese el nombre del jugador: ");
        String respuesta = sc.next();
        return respuesta;
    }

    
    @Override
    public void bienvenida(){
        System.out.println("\t\t~~~~~~~~~~~~~");
        System.out.println("\t\tBATALLA NAVAL");
        System.out.println("\t\t~~~~~~~~~~~~~");
        System.out.println("");
    }
    
    
    @Override
    public int mostrarMenu(){
        System.out.println();
        System.out.println("1- JUGAR VS IA");
        System.out.println("2- SALIR");
        System.out.print("Elija una opcion: ");
        while(!this.sc.hasNextInt()){
            System.out.print("Opcion incorrecta");
            System.out.println("1- JUGAR VS IA");
            System.out.println("2- SALIR");
            System.out.print("Elija una opcion: ");
            sc.next();
        }
        int opcion = sc.nextInt();
        return opcion;
    }
    
    
    @Override
    public void opcionIncorrecta(){
        System.out.println("Opcion incorrecta. Debe elegir un numero entre 1 y 2.");
        System.out.println();
    }
    
    
    @Override
    public void mostrarTurno(Usuario turno){
        System.out.println();
        System.out.printf("Turno de %s para disparar...", turno.getNombre());
        System.out.println();
    }
    
    
    @Override
    public void errorAlColocar(){
        System.out.println("Coordenadas invalidas. Ingrese nuevamente...");
        System.out.println();
    }
    
    
    @Override
    public void mostrarTableroVacio(){
        System.out.println();
        System.out.println("Tablero: ");
        System.out.println();
        System.out.print("   ");
        for (int col = 1; col <= 10; col++) {
            System.out.printf("% d ", col);
        }
        System.out.println();

        String filaLetra;
        for (int i = 0; i < 10; i++) {
            filaLetra = this.filas[i];
            System.out.print(filaLetra + "  ");
            for (int j = 0; j < 10; j++) {
                System.out.print(" ~ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    
    @Override
    public void actualizarRecibido(ResultadoDisparo ultimoRecibido, int xRecibido, int yRecibido, String[][] tableroDisparos) {
        System.out.println("Disparo recibido en " + this.filas[xRecibido] + " - " + (yRecibido+1));
        tableroDisparos[xRecibido][yRecibido] = " X ";
        this.mostrarTableroEnemigo(tableroDisparos);
        
        switch (ultimoRecibido){
            case AGUA -> System.out.println("Resultado: AGUA");
            case TOCADO -> System.out.println("Resultado: BARCO TOCADO");
            case PIEZA_ROTA -> System.out.println("Resultado: PIEZA ROTA");
            case HUNDIDO -> System.out.println("Resultado: BARCO HUNDIDO");
            default -> System.out.println("Resultado: AGUA");
        }
        
        this.esperar(2000);
    }
    
    @Override
    public void mostrarSalir(){
        System.out.println();
        System.out.println("Partida Terminada...");
    }
    
    
    @Override
    public void mostrarGanador(Usuario ganador){
        System.out.println();
        System.out.println(ganador.getNombre() + " ha ganado.");
    }
    
    
    public void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
