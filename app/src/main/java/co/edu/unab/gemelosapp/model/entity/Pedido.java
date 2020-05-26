package co.edu.unab.gemelosapp.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "pedidos")
public class Pedido implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;
    private String nombreu, token;
    private int cantidad;
    private double precio;
    private boolean confirmacion, finalizado, domicilio;
    @Ignore
    private List<Producto> misProductos;

    @Ignore
    public Pedido(String nombreu, List<Producto> misProductos, int cantidad, boolean finalizado, boolean domicilio, double precio) {
        this.nombreu = nombreu;
        this.misProductos = misProductos;
        this.cantidad = cantidad;
        this.finalizado = false;
        this.domicilio = domicilio;
        this.precio = precio;
    }

    public Pedido(String nombreu, int cantidad, boolean finalizado, boolean domicilio, double precio){
        this.nombreu = nombreu;
        this.cantidad = cantidad;
        this.finalizado = finalizado;
        this.domicilio = domicilio;
        this.precio = precio;
    }

    @Ignore
    public Pedido(boolean confirmacion, boolean finalizado){
        this.confirmacion = confirmacion;
        this.finalizado = finalizado;
    }

    @Ignore
    public Pedido(){

    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
