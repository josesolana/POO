package Model.Elementos;

import Model.Personajes.Grupo;
import Model.Personajes.Personaje;
import java.util.ArrayList;

public class EPromedio implements Estrategia {

    @Override
    public double getValor(Grupo referencia, String nombre) {
        double suma = 0;
        ArrayList<Personaje> listaPersonajes = referencia.getListaDePersonajes();

        for (Personaje p : listaPersonajes) {
            suma += p.getValorAtributo(nombre);
        }
        return suma / listaPersonajes.size();
    }

}
