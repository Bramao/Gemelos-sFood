package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RegistroActivity extends AppCompatActivity {

    private TextView txvRusuario, txvRnumero, txvRdireccion, txvRcontra;
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
                Intent in = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });


    }

    private void asociarElementos(){
        txvRusuario = findViewById(R.id.edt_rusuario);
        txvRnumero = findViewById(R.id.edt_rnumero);
        txvRdireccion = findViewById(R.id.edt_rdireccion);
        txvRcontra = findViewById(R.id.edt_rcontra);
        btnRregistrar = findViewById(R.id.btn_rregistrar);
        imvLogoR = findViewById(R.id.imv_logoR);
    }
}
