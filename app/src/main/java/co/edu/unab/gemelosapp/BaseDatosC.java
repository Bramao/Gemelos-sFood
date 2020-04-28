package co.edu.unab.gemelosapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Producto.class}, version = 1, exportSchema = false)
public abstract class BaseDatosC extends RoomDatabase {

    abstract CarritoDAO carritoDAO();
    private static BaseDatosC instancia;

    public static BaseDatosC obtenerInstancia(Context context){
        if(instancia==null){
            instancia = Room.databaseBuilder(context.getApplicationContext(), BaseDatosC.class, "carrito.db").allowMainThreadQueries().build();
        }
        return instancia;
    }
}
