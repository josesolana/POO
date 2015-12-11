package Model.Elementos;

import Model.Personajes.Personaje;
import java.util.Comparator;

public class Comparador implements Comparator<Personaje> {

    private String atributo;

    public Comparador(String atributo) {
        this.atributo = atributo;
    }

    @Override
    public int compare(Personaje p1, Personaje p2) {
        if (p1.getValorAtributo(atributo) > p2.getValorAtributo(atributo)) {
            return 1;
        } else if (p1.getValorAtributo(atributo) < p2.getValorAtributo(atributo)) {
            return -1;
        } else {
            return 0;
        }
    }
    
}
