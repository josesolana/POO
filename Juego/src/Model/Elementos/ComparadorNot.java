package Model.Elementos;

import Model.Personajes.Personaje;
import java.io.Serializable;
import java.util.Comparator;

public class ComparadorNot implements Comparator<Personaje> {

    private Comparador c;

    public ComparadorNot() {

    }

    public void setComparador(Comparador c) {
        this.c = c;
    }

    public ComparadorNot(Comparador c) {
        this.c = c;
    }

    @Override
    public int compare(Personaje p1, Personaje p2) {
        return -1 * c.compare(p1, p2);
    }
}
