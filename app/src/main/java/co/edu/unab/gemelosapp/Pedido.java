package co.edu.unab.gemelosapp;

import java.io.Serializable;

public class Pedido implements Serializable {

    private int id;
    private String nombreu, nombrep, descripcion;
    private double cantidad;

    public Pedido(String nombreu, String nombrep, String descripcion, double cantidad) {
        this.nombreu = nombreu;
        this.nombrep = nombrep;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreu() {
        return nombreu;
    }

    public void setNombreu(String nombreu) {
        this.nombreu = nombreu;
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
