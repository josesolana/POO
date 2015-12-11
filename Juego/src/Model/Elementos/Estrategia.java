package Model.Elementos;

import Model.Personajes.Grupo;

public interface Estrategia {

    public abstract double getValor(Grupo referencia, String nombre);
}
