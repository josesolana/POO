package Model.Elementos;

import Model.Personajes.Grupo;

public class EValorFijo implements Estrategia {

    private double valor;

    public EValorFijo(double valor) {
        this.valor = valor;
    }

    @Override
    public double getValor(Grupo referencia, String nombre) {
        return valor;
    }

}
