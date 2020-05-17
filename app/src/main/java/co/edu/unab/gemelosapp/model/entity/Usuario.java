package co.edu.unab.gemelosapp.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String nombre, pass;
    private boolean admin;

    public Usuario(String nombre, String pass ) {
        this.nombre = nombre;
        this.pass = pass;
    }

    public Usuario(String nombre, String pass, boolean admin) {
        this.nombre = nombre;
        this.pass = pass;
        this.admin = admin;
    }

    public Usuario(){}

    public boolean isAdmin() {
        return admin;
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
