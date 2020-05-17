package co.edu.unab.gemelosapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.entity.Usuario;
import co.edu.unab.gemelosapp.view.fragment.UsuarioMenuFragment;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtContra;
    private Button btnIngresar, btnRegistrar;
    private ImageView imvLogoL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        this.asociarElementos();

        final SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), 0);
        final Boolean logueado = misPreferencias.getBoolean("logueado", false);

        if(logueado){
            Intent in = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(in);
            finish();
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();
                firestoreDB.collection("usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {//Traer usuarios de la base de datos y compararlos con los datos ingresados
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot documento: task.getResult()){
                            Usuario usuarioTmp = documento.toObject(Usuario.class);
                            if(usuarioTmp.getNombre().equals(edtUsuario.getText().toString()) & usuarioTmp.getPass().equals(edtContra.getText().toString()) ){
                                SharedPreferences.Editor miEditor = misPreferencias.edit();
                                miEditor.putBoolean("logueado", true);
                                miEditor.putString("usuario",edtUsuario.getText().toString());
                                miEditor.apply();

                                Toast.makeText(LoginActivity.this,"Bienvenido "+edtUsuario.getText(), Toast.LENGTH_SHORT).show();

                                if(usuarioTmp.isAdmin()){
                                    Intent in =  new Intent(LoginActivity.this, AdminMenuActivity.class);
                                    startActivity(in);
                                    finish();
                                }else{
                                    Intent in =  new Intent(LoginActivity.this, MenuActivity.class);
                                    startActivity(in);
                                    finish();
                                }


                            }else{
                                //Toast.makeText(LoginActivity.this, "Datos incorrectos.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(in);
            }
        });
    }



    private void asociarElementos(){
        edtUsuario = findViewById(R.id.edt_usuario);
        edtContra = findViewById(R.id.edt_contra);
        btnIngresar = findViewById(R.id.btn_ingresar);
        btnRegistrar = findViewById(R.id.btn_registrar);
        imvLogoL = findViewById(R.id.imv_logoL);
    }
}
