package co.edu.unab.gemelosapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;

public class AdminAgregarActivity extends AppCompatActivity {

    private ImageView imvLogoAA;
    private EditText edtAAnombre, edtAAdescripcion, edtAAfoto, edtAAprecio;
    private Button btnAAagregar;
    private ProductoRepository productoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar);

        this.asociarElementos();
        productoRepository = new ProductoRepository(this);
        btnAAagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto nuevoProducto = new Producto(edtAAnombre.getText().toString(), edtAAdescripcion.getText().toString(), edtAAfoto.getText().toString(), Double.parseDouble(edtAAprecio.getText().toString()));
                productoRepository.agregarFirestore(nuevoProducto, new FirestoreCallBack<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        finish();
                    }
                });
            }
        });
    }

    private void asociarElementos(){
        imvLogoAA = findViewById(R.id.imv_logoAA);
        edtAAnombre = findViewById(R.id.edt_aanombre);
        edtAAdescripcion = findViewById(R.id.edt_aadescripcion);
        edtAAfoto = findViewById(R.id.edt_aafoto);
        edtAAprecio =  findViewById(R.id.edt_aaprecio);
        btnAAagregar =  findViewById(R.id.btn_aaagregar);
    }
}
