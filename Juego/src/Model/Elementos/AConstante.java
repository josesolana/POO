package Model.Elementos;

import Model.Personajes.Individuo;

public class AConstante implements Atributo {

    private double valor;

    public AConstante(double valor) {
        this.valor = valor;
    }

    @Override
    public double getValor(Individuo referencia) {
        return valor;
    }
}
