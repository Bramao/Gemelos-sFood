package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    private Button btnBproductos, btnBcarrito;
    private ImageView imvLogoM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.asociarElementos();

        btnBproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MenuActivity.this, ListadoActivity.class);
                startActivity(in);
            }
        });

        btnBcarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in =  new Intent(MenuActivity.this, CarritoActivity.class);
                startActivity(in);
            }
        });
    }

    private void asociarElementos(){
        btnBproductos = findViewById(R.id.btn_bproductos);
        btnBcarrito = findViewById(R.id.btn_bcarrito);
        imvLogoM = findViewById(R.id.imv_logoM);
    }
}
