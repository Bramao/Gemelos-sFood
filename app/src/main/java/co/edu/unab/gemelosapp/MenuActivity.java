package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    private Button btnMproductos, btnMcarrito, btnMcerrarS;
    private ImageView imvLogoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.asociarElementos();

        btnMproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MenuActivity.this, ListadoActivity.class);
                startActivity(in);
            }
        });

        btnMcarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =  new Intent(MenuActivity.this, CarritoActivity.class);
                startActivity(in);
            }
        });

        btnMcerrarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
                SharedPreferences.Editor miEditor = misPreferencias.edit();
                miEditor.clear();
                miEditor.apply();

                Intent in = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private void asociarElementos(){
        btnMproductos = findViewById(R.id.btn_mproductos);
        btnMcarrito = findViewById(R.id.btn_mcarrito);
        imvLogoM = findViewById(R.id.imv_logoM);
        btnMcerrarS = findViewById(R.id.btn_mcerrars);
    }
}
