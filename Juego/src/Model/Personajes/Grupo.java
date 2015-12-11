package Model.Personajes;

import Model.Elementos.Estrategia;
import java.util.ArrayList;
import java.util.HashMap;

public class Grupo extends Personaje {

    private ArrayList<Personaje> listaPersonajes;
    private HashMap<String, Estrategia> listaEstrategias;
    public static String nombreEstrategiaPorDefecto = "Estrategia Por Defecto";

    public Grupo(String nombre) {
        super(nombre);
        listaPersonajes = new ArrayList<>();
        listaEstrategias = new HashMap<>();
    }

    public Grupo(String nombre, String bando) {
        super(nombre, bando);
        listaPersonajes = new ArrayList<>();
        listaEstrategias = new HashMap<>();
    }

    public Grupo(String nombre, ArrayList<Personaje> lista) {
        super(nombre);
        listaPersonajes = new ArrayList<>(lista);
        listaEstrategias = new HashMap<>();
    }

    public Grupo(String nombre, String bando, ArrayList<Personaje> lista) {
        super(nombre, bando);
        listaPersonajes = new ArrayList<>(lista);
        listaEstrategias = new HashMap<>();
    }

    @Override
    public double getValorAtributo(String nombre) {
        if (listaEstrategias.containsKey(nombre)) {
            return listaEstrategias.get(nombre).getValor(this, nombre);
        } else {
            return listaEstrategias.get(nombreEstrategiaPorDefecto).getValor(this, nombre);
        }
    }

    public void addPersonaje(Personaje personaje) {
        listaPersonajes.add(personaje);
    }

    public Boolean deletePersonaje(String nombre) {
        for (int i = 0; i < listaPersonajes.size(); i++) {
            if (listaPersonajes.get(i).getNombre().equals(nombre)) {
                listaPersonajes.remove(i);
                return true;
            }
        }
        return false;
    }

    public Personaje getPersonaje(String nombre) {
        int i = 0;
        for (Personaje p : listaPersonajes) {
            if (p.getNombre().equals(nombre)) {
                return listaPersonajes.get(i);
            }
            i++;
        }
        return null;
    }

    public ArrayList<Personaje> getListaDePersonajes() {
        return listaPersonajes;
    }

    public void addEstrategia(String nombre, Estrategia e) {
        listaEstrategias.put(nombre, e);
    }

    public void deleteEstrategia(String nombre) {
        listaEstrategias.remove(nombre);
    }

    public Estrategia getEstrategiaPorDefecto() {
        return listaEstrategias.get(nombreEstrategiaPorDefecto);
    }

    public void setEstrategiaPorDefecto(Estrategia e) {
        listaEstrategias.remove(nombreEstrategiaPorDefecto);
        listaEstrategias.put(nombreEstrategiaPorDefecto, e);
    }

}
