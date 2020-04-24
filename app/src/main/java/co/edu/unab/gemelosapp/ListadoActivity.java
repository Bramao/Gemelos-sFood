package co.edu.unab.gemelosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

public class ListadoActivity extends AppCompatActivity {

    private ImageView imvLogoL;
    private RecyclerView rcvListadoP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        this.asociarElementos();
    }

    private void asociarElementos(){
        imvLogoL = findViewById(R.id.imv_logoList);
        rcvListadoP = findViewById(R.id.rcv_listadoP);
    }
}
