package co.edu.unab.gemelosapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegistroActivity extends AppCompatActivity {

    private EditText edtRusuario, edtRcontra, edtRconfirmar;
    private Button btnRregistrar;
    private ImageView imvLogoR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.asociarElementos();

        btnRregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtRcontra.getText().toString().equals(edtRconfirmar.getText().toString())){
                    Usuario nuevoUsuario = new Usuario(edtRusuario.getText().toString(), edtRcontra.getText().toString());

                    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                    firestoreDB.collection("usuarios").add(nuevoUsuario).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
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
