package co.edu.unab.gemelosapp.model.bd.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.edu.unab.gemelosapp.model.entity.Producto;

@Dao
public interface CarritoDAO {

    @Insert
    void agregar(Producto miProducto);

    @Query("SELECT * FROM productos")
    List<Producto> obtenerTodos();

    @Update
    void  actualizar(Producto miProducto);

    @Delete
    void borrar(Producto miProducto);

    @Query("DELETE FROM productos")
    void borrarTodo();

    @Query("SELECT * FROM productos WHERE id=:idProducto")
    Producto obtenerPorId(int idProducto);

    @Query("SELECT * FROM productos WHERE nombre=:nombre ")
    Producto verificar(String nombre);

    @Query("SELECT SUM(precio*cantidad) FROM productos")
    float getTotal();

    @Query("SELECT SUM(cantidad) FROM productos")
    int getCantidad();
}
