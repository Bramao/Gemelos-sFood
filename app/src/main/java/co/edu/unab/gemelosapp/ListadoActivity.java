package co.edu.unab.gemelosapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListadoActivity extends AppCompatActivity {

    private ImageView imvLogoL;
    private RecyclerView rcvListadoP;
    private ProductoAdapter miAdaptador;
    private List<Producto> productos;
    private ProductoDAO productoDAO;
    private CarritoDAO carritoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        BaseDatos bd = BaseDatos.obtenerInstancia(this);
        productoDAO = bd.productoDAO();

        //this.getData();
        this.asociarElementos();

        FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
        firestoreDB.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                productos = new ArrayList<>();
                if(queryDocumentSnapshots != null){
                    for(QueryDocumentSnapshot documento: queryDocumentSnapshots){
                        Producto miProducto = documento.toObject(Producto.class);
                        miProducto.setId(documento.getId());
                        productos.add(miProducto);
                    }
                }
                controlAdaptador();
                if(miAdaptador != null){
                    miAdaptador.setProductos(productos);
                    miAdaptador.notifyDataSetChanged();
                }
            }
        });

    }

    private void getData(){
        productos = productoDAO.obtenerTodos();
        if(productos.size()==0){
            productoDAO.agregar(new Producto("Hambuerguesa", "Hamburguesa doble queso.", "https://www.faststation.net/wp-content/uploads/2018/10/Hamburguesa-Doble.jpg", 10000, 0));
            productoDAO.agregar(new Producto("Perro Caliente", "Perro caliente sencillo.", "https://placeralplato.com/files/2015/11/Pan-para-hot-dogs.jpg", 8000, 0));
            productoDAO.agregar(new Producto("Picada", "Picada de carnes", "https://img-global.cpcdn.com/recipes/bad8f40e8c33f7b7/751x532cq70/picada-sencilla-de-carnes-foto-principal.jpg", 15000, 0));

            productos = productoDAO.obtenerTodos();
        }

    }

    private void controlAdaptador(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {

                Intent in = new Intent(ListadoActivity.this, DescripcionActivity.class);
                in.putExtra("producto", producto);
                startActivity(in);
            }
        });

        rcvListadoP.setLayoutManager(manager);
        rcvListadoP.setAdapter(miAdaptador);
    }

    private void asociarElementos(){
        imvLogoL = findViewById(R.id.imv_logoList);
        rcvListadoP = findViewById(R.id.rcv_listadoP);
    }
}
