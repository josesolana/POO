package Model.Personajes;

import java.util.Objects;
import javafx.scene.image.Image;

public abstract class Personaje {

    protected String nombre;
    protected String bando;
    protected Image imgUrl;

    public Personaje() {
    }

    public Personaje(String nombre) {
        this.nombre = nombre;
        imgUrl = new Image(getClass().getResourceAsStream("/Resources/Images/Cards/" + nombre + ".png"));
    }

    public Personaje(String nombre, String bando) {
        this.nombre = nombre;
        this.bando = bando;
        imgUrl = new Image(getClass().getResourceAsStream("/Resources/Images/Cards/" + nombre + ".png"));

    }

    public abstract double getValorAtributo(String nombre);

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        imgUrl = new Image(getClass().getResourceAsStream("/Resources/Images/Cards/" + nombre + ".png"));

    }

    public String getBando() {
        return bando;
    }

    public void setBando(String bando) {
        this.bando = bando;
    }

    /* public void setImageUrl(String url){
        imgUrl = url;
    }*/
    public Image getImage() {
        //return new Image(getClass().getResourceAsStream("/Resources/Images/Cards/" + getNombre() + ".png"));
        return imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (!(o instanceof Personaje))) {
            return false;
        } else {
            return getNombre().equals(((Personaje) o).getNombre());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
}
