package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DescripcionActivity extends AppCompatActivity {

    private TextView txvDproducto, txvDdescripcion, txvDprecio;
    private Button btnDagregar;
    private ImageView imvLogoD, imvDproducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        this.asociarElementos();

        btnDagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(DescripcionActivity.this, CarritoActivity.class);
                startActivity(in);
                finish();
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
