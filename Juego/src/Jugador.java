import Model.Personajes.Personaje;
import java.util.Objects;

public class Jugador implements Comparable<Jugador> {
    private String nombre;
    private int puntajePlayer;
    private Mazo cartas;
    private ModoJuego modoJuego;
    //private String comparador;
    private Personaje cartaActual;

    public Jugador(String name) {
        nombre=name;
        puntajePlayer = 0;
        this.cartas = null;
        cartaActual=null;
    }
    
    public Jugador(Mazo cartas) {
        nombre=null;
        puntajePlayer = 0;
        this.cartas = cartas;
        cartaActual=cartas.getFirst();
    }

    public void winner(Personaje cartaGanada){
        cartas.addCartas(cartaActual);
        cartas.addCartas(cartaGanada);
        puntajePlayer++;
        cartaActual=null;
    }
    
    public Personaje perdio(){
        Personaje cartaPerdida=cartaActual;
        cartaActual=null;
        return cartaPerdida;
    }
    
    public int getPuntaje() {
        return puntajePlayer;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setCartas(Mazo playerDeck) {
        this.cartas = playerDeck;
        cartaActual=cartas.getFirst();
    }

    public Personaje getCarta(){
        return cartaActual;
    }
    
    public void next() {
        cartaActual = cartas.getFirst();
    }

    public void setCartaActual(Personaje cartaActual) {
        this.cartaActual = cartaActual;
    }

    public void setModoJuego(ModoJuego modoJuego) {
        this.modoJuego = modoJuego;
    }
/*
    public void setComparador(ObjectProperty<String> proveedor) {
        proveedor.addListener((a,b,nuevo) -> {
            comparador=nuevo;
        });
    }
*/
    @Override
    public int compareTo(Jugador contrario) {
        return modoJuego.getEstrategia().compare(cartaActual, contrario.getCarta());
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj == null)||(getClass() != obj.getClass()))
            return false;
        return(compareTo((Jugador)obj) == 0); 
    } 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
    public boolean sinCartas(){
        return cartas.isEmpty();
    }
}