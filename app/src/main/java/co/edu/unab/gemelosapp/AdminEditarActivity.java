package co.edu.unab.gemelosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminEditarActivity extends AppCompatActivity {

    private TextView txvAEnombrep;
    private EditText edtAEnombre, edtAEdescripcion, edtAEfoto, edtAEprecio;
    private Button btnAEeditar, btnAEeliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_editar);

        this.asociarElementos();

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

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("productos").document(producto.getId()).set(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });
            }
        });

        btnAEeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("productos").document(producto.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
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
