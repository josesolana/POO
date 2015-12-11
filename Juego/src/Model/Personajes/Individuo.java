package Model.Personajes;

import Model.Elementos.Atributo;
import java.util.HashMap;

public class Individuo extends Personaje {

    private String nombreDePila;
    private String compania;
    private HashMap<String, Atributo> listaAtributos;

    public Individuo(String nombre) {
        super(nombre);
        listaAtributos = new HashMap<>();
    }

    public Individuo(String nombre, String bando, String compania) {
        super(nombre, bando);
        this.compania = compania;
        listaAtributos = new HashMap<>();
    }

    public Individuo(String nombre, HashMap<String, Atributo> lista) {
        super(nombre);
        listaAtributos = new HashMap<>(lista);
    }

    public Individuo(String nombre, String bando, String compania, HashMap<String, Atributo> lista) {
        super(nombre, bando);
        this.compania = compania;
        listaAtributos = new HashMap<>(lista);
    }

    public String getNombreDePila() {
        return nombreDePila;
    }

    public void setNombreDePila(String nombreDePila) {
        this.nombreDePila = nombreDePila;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    @Override
    public double getValorAtributo(String nombre) {
        if (listaAtributos.containsKey(nombre)) {
            return listaAtributos.get(nombre).getValor(this);
        } else {
            return 0;
        }
    }

    public void addAtributo(String nombre, Atributo a) {
        listaAtributos.put(nombre, a);
    }

    public void deleteAtributo(String nombre) {
        listaAtributos.remove(nombre);
    }

}
