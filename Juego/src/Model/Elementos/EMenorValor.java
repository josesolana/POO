package Model.Elementos;

import Model.Personajes.Grupo;
import Model.Personajes.Personaje;
import java.util.ArrayList;

public class EMenorValor implements Estrategia {

    @Override
    public double getValor(Grupo referencia, String nombre) {
        ArrayList<Personaje> listaPersonajes = referencia.getListaDePersonajes();
        double valor = referencia.getListaDePersonajes().get(0).getValorAtributo(nombre);

        for (Personaje p : listaPersonajes) {
            if (p.getValorAtributo(nombre) < valor) {
                valor = p.getValorAtributo(nombre);
            }
        }
        return valor;
    }

}
