
import Model.Personajes.Personaje;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {
    
    private final List<Personaje> cartas;

    public Deck(List<Personaje> cartas) {
        this.cartas = cartas;
    }
    
    public Deck(){
        cartas = Collections.synchronizedList(
                    new ArrayList<>(ModelLoader
                        .getInstance()
                            .getListaIndividuos()));
    }
    
    public ArrayList<Deck> repartir(int participantes){
        ArrayList<Deck> aux=new ArrayList<>();
        if( (participantes > 0) && (participantes <= cartas.size()) ){
            List<Personaje> temp;
            Collections.shuffle(cartas);
            int cantidad=cartas.size()/participantes;
            for(int p=0;p<participantes;p++){
                temp=new ArrayList<>();
                for(int i=cantidad*p;i < (cantidad*(p+1)) ; i++){
                        temp.add(cartas.get(i));
                }
                aux.add(new Deck(temp));
            }
        }
        return aux;
    }
    
    public Personaje getFirst(){
        if(cartas != null && !isEmpty())
            return cartas.remove(0);
        else
            return null;
    }
    
    public void addCartas(Personaje nuevo){
        cartas.add(nuevo);
    }
    
    public boolean isEmpty(){
        return cartas.isEmpty();
    }
}
