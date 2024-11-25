package com.batalla_naval.modelo.jugador;
import com.batalla_naval.modelo.barcos.ModeloBarco;
import com.batalla_naval.modelo.recursos.ResultadoDisparo;
import com.batalla_naval.observer.Observable;
import com.batalla_naval.observer.Observador;
import java.util.ArrayList;


public class Jugador extends Usuario implements Observable{
    
    private ArrayList<Observador> observador;
    private String[][] disparosRecibidos;
    private int xRecibido;
    private int yRecibido;
    private ResultadoDisparo ultimoRecibido;
    private boolean recibioDisparo = false;
    
    
    public Jugador(String nombre){
        super(nombre);
        this.observador = new ArrayList<>();
        this.disparosRecibidos = new String[10][10];
    }
    
    
    @Override
    public void notificar(){
        for(Observador obs: observador){
            if(recibioDisparo){
                obs.actualizarDisparoRecibido(ultimoRecibido, xRecibido, yRecibido, this.disparosRecibidos);
            }
            else{
                obs.actualizarDisparo(this.getUltimoDisparo(), this.getXUltimo(), this.getYUltimo(), this.getTableroEnemigo());
            }
        }
    }
    
    public void addObservador(Observador obs){
        this.observador.add(obs);
    }
    
    @Override
    //numeroBarco se corresponde al indice del array ModeloBarco[] barcos del jugador.
    public boolean colocarBarcoJugador(int x, int y, boolean vertical, int numeroBarco){
        if(this.getBarcos().length == 0) return false;
        
        ModeloBarco barco = this.getBarcos()[numeroBarco];
        
        if(barco == null) return false;
        
        barco.setPosicion(x, y, vertical);
        
        boolean colocar = getTableroUsuario().colocarBarco(barco);
        
        if(colocar) this.getBarcos()[numeroBarco] = null;
        
        this.reorganizarBarcos();
        return colocar;
    }
    
    
    @Override
    public ResultadoDisparo disparar(int x, int y, Usuario destino){
        ResultadoDisparo resultado = destino.getTableroUsuario().disparo(x, y);
        this.setUltimoDisparo(resultado);
        this.setXUltimo(x);
        this.setYUltimo(y);
        
        if(resultado == ResultadoDisparo.HUNDIDO){
            destino.barcoHundido();
        }
        
        if (destino instanceof Jugador) {
            ((Jugador) destino).registrarDisparoRecibido(resultado, x, y);
        }
        
        this.notificar();
        
        return resultado;
    }
    
    
    public void inicializarDisparosRecibidos(){
        ModeloBarco[][] t = this.getTableroUsuario().getTablero();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(t[i][j] == null){
                    this.disparosRecibidos[i][j] = " ~ ";
                }
                else{
                    this.disparosRecibidos[i][j] = " B ";
                }
            }
        }
    }
    
    
    public void registrarDisparoRecibido(ResultadoDisparo r, int x, int y){
        this.ultimoRecibido = r;
        this.xRecibido = x;
        this.yRecibido = y;
        this.recibioDisparo = true;
        this.notificar();
        this.recibioDisparo = false;
    }
    
    
    public void reiniciarJugador(){
        this.getTableroUsuario().reiniciarTablero();
    
        this.cargarBarcos();

        this.inicializarTableroEnemigo();
        this.inicializarDisparosRecibidos();

        this.setUltimoDisparo(null);
        this.setXUltimo(-1);
        this.setYUltimo(-1);
        this.recibioDisparo = false;
        this.xRecibido = -1;
        this.yRecibido = -1;
        this.ultimoRecibido = null;
    }
    
}
