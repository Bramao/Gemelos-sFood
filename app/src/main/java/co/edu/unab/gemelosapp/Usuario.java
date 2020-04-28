package co.edu.unab.gemelosapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String nombre, pass;

    public Usuario(String nombre, String pass) {
        this.nombre = nombre;
        this.pass = pass;

    }

    public Usuario(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
