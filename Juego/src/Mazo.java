
import Model.Personajes.Personaje;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class Mazo {
    //TODO:Que se pueda seleccionar Imagen del Mazo
    private static String IMAGEURL = "/Resources/Images/Cards/card_back.png";
    private final List<Personaje> cartas;
    HashSet<String> atributos;
    private StringBuilder nombre;
    
    public Mazo(List<Personaje> cartas) {
        this.cartas = cartas;
    }
    
    public Mazo(String nombre, ArrayList<Personaje> cartas,HashSet<String> atributos) {
        this.nombre = new StringBuilder( nombre);
        this.cartas = cartas;
        this.atributos=atributos;
    }
    
    
 /*   public Mazo(){
        cartas = new ArrayList<>(ModelLoader
                                    .getInstance()
                                        .getListaIndividuos());
    }
    */
    
    public ArrayList<Mazo> repartir(int participantes){
        ArrayList<Mazo> aux=new ArrayList<>();
        if((participantes > 0) && (participantes <= cartas.size())){
            Collections.shuffle(cartas);
            int cantidad=cartas.size()/participantes;
            for( int p=0 ; p<participantes ; p++ ){
                List<Personaje> temp=new ArrayList<>();
                for( int i=cantidad*p ; i < (cantidad*(p+1)) ; i++ ){
                        temp.add(cartas.get(i));
                }
                aux.add(new Mazo(temp));
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
    
    public StringBuilder getNombre(){
        return nombre;
    }
    
    public String getEspalda(){
        return IMAGEURL;
    }
    public HashSet<String> getAtributo(){
        return atributos;
    }
    
    public boolean isEmpty(){
        return cartas.isEmpty();
    }
    
    public Personaje getCarta(String nombre){
        return cartas.parallelStream().filter(personaje -> // Filtro de la lista de personajes
            (personaje.getNombre().equals(nombre))).findFirst().orElse(null);//Y si no existe, retorno null
    }
    
    
}
