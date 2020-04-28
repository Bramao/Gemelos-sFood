package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DescripcionActivity extends AppCompatActivity {

    private TextView txvDproducto, txvDdescripcion, txvDprecio;
    private Button btnDagregar;
    private ImageView imvLogoD, imvDproducto;
    private CarritoDAO carritoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        BaseDatosC db = BaseDatosC.obtenerInstancia(this);
        carritoDAO = db.carritoDAO();

        this.asociarElementos();

        final Producto miProducto = (Producto) getIntent().getSerializableExtra("producto");

        txvDproducto.setText(miProducto.getNombre());
        txvDdescripcion.setText(miProducto.getDescripcion());
        txvDprecio.setText(String.valueOf(miProducto.getPrecio()));
        Picasso.get().load(miProducto.getFoto()).resize(300,300).into(imvDproducto);


        btnDagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto_tmp = carritoDAO.verificar(miProducto.getNombre());

                if(producto_tmp==null){
                    Intent in = new Intent(DescripcionActivity.this, CarritoActivity.class);
                    in.putExtra("producto", miProducto);
                    startActivity(in);
                    finish();
                }else {
                    Toast.makeText(DescripcionActivity.this, "Ese producto ya está en el carrito", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void asociarElementos(){
        txvDproducto = findViewById(R.id.txv_dproducto);
        txvDdescripcion = findViewById(R.id.txv_ddescripcion);
        txvDprecio = findViewById(R.id.txv_dprecio);
        btnDagregar = findViewById(R.id.btn_dagregar);
        imvLogoD = findViewById(R.id.imv_logoD);
        imvDproducto = findViewById(R.id.imv_dproducto);
    }
}
