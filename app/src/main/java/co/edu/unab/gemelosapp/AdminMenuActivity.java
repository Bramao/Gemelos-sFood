package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AdminMenuActivity extends AppCompatActivity {

    private ImageView imvLogoAM;
    private Button btnAMproductos, btnAMpedidos, btnAMcerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        this.asociarElementos();

        btnAMproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminMenuActivity.this, AdminListadoActivity.class);
                startActivity(in);
            }
        });

        btnAMpedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(AdminMenuActivity.this, AdminPedidosActivity.class);
                startActivity(in);
            }
        });

        btnAMcerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences misPreferencias = getSharedPreferences(getString(R.string.misDatos), MODE_PRIVATE);
                SharedPreferences.Editor miEditor = misPreferencias.edit();
                miEditor.clear();
                miEditor.apply();

                Intent in = new Intent(AdminMenuActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private void asociarElementos(){
        imvLogoAM =  findViewById(R.id.imv_logoAM);
        btnAMproductos =  findViewById(R.id.btn_amproductos);
        btnAMpedidos =  findViewById(R.id.btn_ampedidos);
        btnAMcerrar =  findViewById(R.id.btn_amcerrar);
    }
}
