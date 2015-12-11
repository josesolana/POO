package Model.Elementos;

import Model.Personajes.Individuo;

public class APropio implements Atributo {

    private double valor;

    public APropio(double valor) {
        this.valor = valor;
    }

    public APropio() {
        valor = 0;
    }

    @Override
    public double getValor(Individuo referencia) {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
