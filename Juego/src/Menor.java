import Model.Elementos.Comparador;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

public class Menor implements ModoJuego {

    private final ObservableList<String> atributos;
    
    public Menor(ChoiceBox<String> atributos){
        this.atributos=atributos.getItems();
    }

    @Override
    public Comparador getEstrategia() {
        return new Comparador(atributos.get((int)(Math.random() * atributos.size())));
    }
}