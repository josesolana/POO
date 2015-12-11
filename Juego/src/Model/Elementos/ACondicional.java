package Model.Elementos;

import Model.Personajes.Individuo;
import java.util.ArrayList;

public class ACondicional extends ACompuesto {

    public ACondicional(ArrayList<Atributo> listaAtributos) {
        super(listaAtributos);
    }

    @Override
    public double getValor(Individuo referencia) {
        if (listaAtributos.get(0).getValor(referencia) > listaAtributos.get(1).getValor(referencia)) {
            return listaAtributos.get(2).getValor(referencia);
        } else {
            return listaAtributos.get(3).getValor(referencia);
        }
    }

}
