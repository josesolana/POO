package Model.Elementos;

import Model.Personajes.Individuo;
import java.util.ArrayList;

public class ADivision extends ACompuesto {

    public ADivision(ArrayList<Atributo> listaAtributos) {
        super(listaAtributos);
    }

    @Override
    public double getValor(Individuo referencia) {
        return (listaAtributos.get(1).getValor(referencia) > 0) ? listaAtributos.get(0).getValor(referencia) / listaAtributos.get(1).getValor(referencia) : 0;
    }

}
