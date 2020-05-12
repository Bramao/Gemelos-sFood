package co.edu.unab.gemelosapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;

public class AdminEditarActivity extends AppCompatActivity {

    private TextView txvAEnombrep;
    private EditText edtAEnombre, edtAEdescripcion, edtAEfoto, edtAEprecio;
    private Button btnAEeditar, btnAEeliminar;
    private ProductoRepository productoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_editar);

        this.asociarElementos();
        productoRepository = new ProductoRepository(this);
        final Producto producto = (Producto) getIntent().getSerializableExtra("producto");

        txvAEnombrep.setText("Editar: "+producto.getNombre());
        edtAEnombre.setText(producto.getNombre());
        edtAEdescripcion.setText(producto.getDescripcion());
        edtAEfoto.setText(producto.getFoto());
        edtAEprecio.setText(String.valueOf(producto.getPrecio()));

        btnAEeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setNombre(edtAEnombre.getText().toString());
                producto.setDescripcion(edtAEdescripcion.getText().toString());
                producto.setFoto(edtAEfoto.getText().toString());
                producto.setPrecio(Double.parseDouble(edtAEprecio.getText().toString()));

                productoRepository.editarFirestore(producto, new FirestoreCallBack<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
        });

        btnAEeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoRepository.eliminarFirestore(producto, new FirestoreCallBack<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
        });
    }

    private void asociarElementos(){
        txvAEnombrep = findViewById(R.id.txv_aenombrep);
        edtAEnombre = findViewById(R.id.edt_aenombre);
        edtAEdescripcion = findViewById(R.id.edt_aedescripcion);
        edtAEfoto =  findViewById(R.id.edt_aefoto);
        edtAEprecio =  findViewById(R.id.edt_aeprecio);
        btnAEeditar = findViewById(R.id.btn_aeeditar);
        btnAEeliminar =  findViewById(R.id.btn_aeeliminar);
    }
}
