package Model.Elementos;

import Model.Personajes.Grupo;
import Model.Personajes.Personaje;
import java.util.ArrayList;

public class EMayorValor implements Estrategia {

    @Override
    public double getValor(Grupo referencia, String nombre) {
        double valor = 0;
        ArrayList<Personaje> listaPersonajes = referencia.getListaDePersonajes();

        for (Personaje p : listaPersonajes) {
            if (p.getValorAtributo(nombre) > valor) {
                valor = p.getValorAtributo(nombre);
            }
        }
        return valor;
    }

}
