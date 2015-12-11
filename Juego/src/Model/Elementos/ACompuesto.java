package Model.Elementos;

import Model.Personajes.Individuo;
import java.util.ArrayList;

public abstract class ACompuesto implements Atributo {

    protected ArrayList<Atributo> listaAtributos;

    public ACompuesto(ArrayList<Atributo> listaAtributos) {
        this.listaAtributos = new ArrayList<>(listaAtributos);
    }

    @Override
    public abstract double getValor(Individuo referencia);

}
