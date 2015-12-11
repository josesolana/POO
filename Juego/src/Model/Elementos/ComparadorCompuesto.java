package Model.Elementos;

import Model.Personajes.Personaje;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class ComparadorCompuesto implements Comparator<Personaje> {

    private ArrayList<Comparator<Personaje>> comparadores;

    public ComparadorCompuesto() {
        this.comparadores = new ArrayList<>();
    }

    public ComparadorCompuesto(ArrayList<Comparator<Personaje>> comparadores) {
        this.comparadores = new ArrayList<>(comparadores);
    }

    public void setComparadores(ArrayList<Comparator<Personaje>> comparadores) {
        this.comparadores.clear();
        this.comparadores.addAll(comparadores);
    }

    @Override
    public int compare(Personaje p1, Personaje p2) {

        for (Comparator<Personaje> c : comparadores) {

            if (c.compare(p1, p2) != 0) {
                return c.compare(p1, p2);
            }
        }
        return 0;
    }
}
