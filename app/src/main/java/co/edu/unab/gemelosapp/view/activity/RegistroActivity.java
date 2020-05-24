package co.edu.unab.gemelosapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Usuario;
import co.edu.unab.gemelosapp.model.repository.UsuarioRepository;

public class RegistroActivity extends AppCompatActivity {

    private EditText edtRusuario, edtRcontra, edtRconfirmar;
    private Button btnRregistrar;
    private ImageView imvLogoR;
    private UsuarioRepository usuarioRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.asociarElementos();
        usuarioRepository = new UsuarioRepository(getApplicationContext());

        btnRregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtRcontra.getText().toString().equals(edtRconfirmar.getText().toString())){
                    Usuario nuevoUsuario = new Usuario(edtRusuario.getText().toString(), edtRcontra.getText().toString());

                    usuarioRepository.agregarUsuario(nuevoUsuario, new FirestoreCallBack<Usuario>() {
                        @Override
                        public void correcto(Usuario respuesta) {
                            Toast.makeText(RegistroActivity.this, "Usuario registrado exitosamente.", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });

                }else{
                    Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden.", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void asociarElementos(){
        edtRusuario = findViewById(R.id.edt_rusuario);
        edtRcontra = findViewById(R.id.edt_rcontra);
        edtRconfirmar = findViewById(R.id.edt_rconfirmar);
        btnRregistrar = findViewById(R.id.btn_rregistrar);
        imvLogoR = findViewById(R.id.imv_logoR);
    }
}
