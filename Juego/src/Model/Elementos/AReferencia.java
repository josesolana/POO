package Model.Elementos;

import Model.Personajes.Individuo;

public class AReferencia implements Atributo {

    private String nombre;

    public AReferencia(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public double getValor(Individuo referencia) {
        return referencia.getValorAtributo(nombre);
    }
}
