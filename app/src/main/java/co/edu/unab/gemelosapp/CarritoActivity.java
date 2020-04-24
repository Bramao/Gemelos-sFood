package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class CarritoActivity extends AppCompatActivity {

    private ImageView imvLogoC;
    private Button btnCcomprar;
    private RecyclerView rcvListadoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        this.asociarElementos();
    }

    private void asociarElementos(){
        imvLogoC = findViewById(R.id.imv_logoC);
        btnCcomprar = findViewById(R.id.btn_ccomprar);
        rcvListadoC = findViewById(R.id.rcv_listadoC);
    }
}
