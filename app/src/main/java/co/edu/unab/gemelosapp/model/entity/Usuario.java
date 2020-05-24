package co.edu.unab.gemelosapp.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String id;
    private String nombre, pass, direccion, token;
    private int numero;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setAdmin(boolean admin) {
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
