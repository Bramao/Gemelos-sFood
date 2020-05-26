package co.edu.unab.gemelosapp.model.bd.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.edu.unab.gemelosapp.model.entity.Pedido;

@Database(entities = {Pedido.class}, version = 1, exportSchema = false)
public abstract class BaseDatosP extends RoomDatabase {

    public abstract PedidoDAO pedidoDAO();
    private static BaseDatosP instancia;

    public static BaseDatosP obtenerInstancia(Context context){
        if(instancia==null){
            instancia= Room.databaseBuilder(context.getApplicationContext(), BaseDatosP.class, "pedidos.db").allowMainThreadQueries().build();
        }
        return instancia;
    }
}
