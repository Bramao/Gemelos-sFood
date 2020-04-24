package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor miEditor = misPreferencias.edit();
                miEditor.putBoolean("logueado", true);
                miEditor.apply();

                Toast.makeText(LoginActivity.this,"Datos correctos", Toast.LENGTH_LONG).show();

                Intent in = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(in);
                finish();
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
