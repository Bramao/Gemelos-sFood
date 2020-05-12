package co.edu.unab.gemelosapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.unab.gemelosapp.model.bd.local.BaseDatosC;
import co.edu.unab.gemelosapp.view.adapter.CarritoAdapter;
import co.edu.unab.gemelosapp.model.bd.local.CarritoDAO;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.R;

public class CarritoActivity extends AppCompatActivity {

    private ImageView imvLogoC;
    private Button btnCcomprar;
    private TextView txvCarritoVacio, txvValorTotal;
    private RecyclerView rcvListadoC;
    private List<Producto> productosC;
    private CarritoAdapter miAdaptador;
    private CarritoDAO carritoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        BaseDatosC bd = BaseDatosC.obtenerInstancia(this);
        carritoDAO = bd.carritoDAO();

        this.asociarElementos();

        btnCcomprar.setVisibility(View.INVISIBLE);
        txvValorTotal.setVisibility(View.INVISIBLE);


        final Producto miProducto = (Producto) getIntent().getSerializableExtra("producto");
        if(miProducto!=null){
            productosC = carritoDAO.obtenerTodos();
            //Producto productoTmp = carritoDAO.verificar(miProducto.getNombre(), miProducto.getPrecio());
            //if(productoTmp!=null){
                carritoDAO.agregar(miProducto);
                productosC = carritoDAO.obtenerTodos();


        }else {
            productosC = carritoDAO.obtenerTodos();
        }

        verificarCarrito();




        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());

        miAdaptador = new CarritoAdapter(productosC, new CarritoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                carritoDAO.borrar(producto);
                productosC = carritoDAO.obtenerTodos();
                verificarCarrito();
                miAdaptador.setProductosC(productosC);
                miAdaptador.notifyDataSetChanged();
                txvValorTotal.setText("Total: $"+(carritoDAO.getTotal()));
                //miAdaptador.notifyItemRemoved(position);
            }
        });

        rcvListadoC.setLayoutManager(manager);
        rcvListadoC.setAdapter(miAdaptador);

    }

    private void verificarCarrito(){
        if(productosC.size()!=0){
            btnCcomprar.setVisibility(View.VISIBLE);
            txvValorTotal.setVisibility(View.VISIBLE);
            txvCarritoVacio.setVisibility(View.INVISIBLE);
            txvValorTotal.setText("Total: $"+(carritoDAO.getTotal()));

        }
    }


    private void asociarElementos(){
        imvLogoC = findViewById(R.id.imv_logoC);
        btnCcomprar = findViewById(R.id.btn_ccomprar);
        rcvListadoC = findViewById(R.id.rcv_listadoC);
        txvCarritoVacio = findViewById(R.id.txv_carritoVacio);
        txvValorTotal = findViewById(R.id.txv_valorTotal);
    }
}
