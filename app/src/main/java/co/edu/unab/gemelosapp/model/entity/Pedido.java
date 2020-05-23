package co.edu.unab.gemelosapp.model.entity;

import java.io.Serializable;
import java.util.List;

public class Pedido implements Serializable {

    private String id;
    private String nombreu;
    private int cantidad;
    private boolean confirmacion, finalizado, domicilio;
    private List<Producto> misProductos;

    public Pedido(String nombreu, List<Producto> misProductos, int cantidad) {
        this.nombreu = nombreu;
        this.misProductos = misProductos;
        this.cantidad = cantidad;
    }

    public Pedido(boolean confirmacion, boolean finalizado){
        this.confirmacion = confirmacion;
        this.finalizado = finalizado;
    }

    public Pedido(){

    }

    public boolean isDomicilio() {
        return domicilio;
    }

    public void setDomicilio(boolean domicilio) {
        this.domicilio = domicilio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public List<Producto> getMisProductos() {
        return misProductos;
    }

    public void setMisProductos(List<Producto> misProductos) {
        this.misProductos = misProductos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreu() {
        return nombreu;
    }

    public void setNombreu(String nombreu) {
        this.nombreu = nombreu;
    }


}
