package co.edu.unab.gemelosapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;
import co.edu.unab.gemelosapp.view.adapter.ProductoAdapter;
import co.edu.unab.gemelosapp.R;

public class ListadoActivity extends AppCompatActivity {

    private ImageView imvLogoL;
    private RecyclerView rcvListadoP;
    private ProductoAdapter miAdaptador;
    private List<Producto> productos;
    private ProductoRepository productoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.asociarElementos();
        productoRepository = new ProductoRepository(this);
        productos =new ArrayList<>();
        this.controlAdaptador();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        rcvListadoP.setLayoutManager(manager);
        rcvListadoP.setAdapter(miAdaptador);
        rcvListadoP.setHasFixedSize(true);
        this.getDataFirestore();

    }

    private void getDataFirestore(){
        productoRepository.escucharTodos(new FirestoreCallBack<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                miAdaptador.setProductos(respuesta);
                miAdaptador.notifyDataSetChanged();
            }
        });
    }

    private void controlAdaptador(){
        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {

                Intent in = new Intent(ListadoActivity.this, DescripcionActivity.class);
                in.putExtra("producto", producto);
                startActivity(in);
            }
        });
    }

    private void asociarElementos(){
        imvLogoL = findViewById(R.id.imv_logoList);
        rcvListadoP = findViewById(R.id.rcv_listadoP);
    }
}
