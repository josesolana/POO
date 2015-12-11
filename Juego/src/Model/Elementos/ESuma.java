package Model.Elementos;

import Model.Personajes.Grupo;
import Model.Personajes.Personaje;
import java.util.ArrayList;

public class ESuma implements Estrategia {

    @Override
    public double getValor(Grupo referencia, String nombre) {
        double suma = 0;
        ArrayList<Personaje> listaPersonajes = referencia.getListaDePersonajes();

        suma = listaPersonajes.stream().map((p) -> p.getValorAtributo(nombre)).reduce(suma, (accumulator, _item) -> accumulator + _item);
        return suma;
    }

}
