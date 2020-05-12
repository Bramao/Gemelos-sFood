package co.edu.unab.gemelosapp.model.bd.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.edu.unab.gemelosapp.model.entity.Producto;

@Database(entities = {Producto.class}, version = 1, exportSchema = false)
public abstract class BaseDatosC extends RoomDatabase {

    public abstract CarritoDAO carritoDAO();
    private static BaseDatosC instancia;

    public static BaseDatosC obtenerInstancia(Context context){
        if(instancia==null){
            instancia = Room.databaseBuilder(context.getApplicationContext(), BaseDatosC.class, "carrito.db").allowMainThreadQueries().build();
        }
        return instancia;
    }
}
