package co.edu.unab.gemelosapp.model.bd.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.edu.unab.gemelosapp.model.entity.Pedido;

@Dao
public interface PedidoDAO {

    @Insert
    void agregar(Pedido miPedido);

    @Query("SELECT*FROM pedidos")
    List<Pedido> obtenerTodos();

    @Update
    void actualizar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Query("DELETE FROM pedidos")
    void borrarTodo();
}
