import Model.Elementos.Comparador;
import javafx.scene.control.ChoiceBox;

public class Manual implements ModoJuego {
    
    private String stComparador;
    
    public Manual(ChoiceBox<String> atributos){
        stComparador=atributos.getValue();
       atributos.valueProperty().addListener((a,b,nuevo) -> {
            stComparador=nuevo;
        });
    }
    
    @Override
    public Comparador getEstrategia() {
        return new Comparador(stComparador);
    }
}