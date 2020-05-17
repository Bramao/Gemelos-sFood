package co.edu.unab.gemelosapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosC;
import co.edu.unab.gemelosapp.model.bd.local.CarritoDAO;
import co.edu.unab.gemelosapp.model.entity.Producto;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioDescripcionFragment extends Fragment {

    private TextView txvDproducto, txvDdescripcion, txvDprecio;
    private Button btnDagregar;
    private ImageView imvLogoD, imvDproducto;
    private EditText edtMcantidad;
    private CarritoDAO carritoDAO;
    private Double cantidad ;

    public UsuarioDescripcionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseDatosC db = BaseDatosC.obtenerInstancia(getContext());
        carritoDAO = db.carritoDAO();

        final Producto miProducto = UsuarioDescripcionFragmentArgs.fromBundle(getArguments()).getProducto();

        txvDproducto.setText(miProducto.getNombre());
        txvDdescripcion.setText(miProducto.getDescripcion());
        txvDprecio.setText("$"+miProducto.getPrecio());
        Picasso.get().load(miProducto.getFoto()).resize(300,300).into(imvDproducto);


        btnDagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad = Double.valueOf(edtMcantidad.getText().toString());
                if(cantidad != 0 && cantidad > 0 ) {
                    miProducto.setCantidad(Double.parseDouble(edtMcantidad.getText().toString()));
                    carritoDAO.actualizar(miProducto);
                    Producto producto_tmp = carritoDAO.verificar(miProducto.getNombre());

                    if (producto_tmp == null) {
                        Navigation.findNavController(getView()).navigate(UsuarioDescripcionFragmentDirections.actionUsuarioDescripcionFragmentToUsuarioCarritoFragment(miProducto));
                        //finish();
                    } else {
                        Toast.makeText(getContext(), "Ese producto ya está en el carrito", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Ingrese una cantidad valida.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario_descripcion, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void asociarElementos(View view){
        txvDproducto = view.findViewById(R.id.txv_dproducto);
        txvDdescripcion = view.findViewById(R.id.txv_ddescripcion);
        txvDprecio = view.findViewById(R.id.txv_dprecio);
        btnDagregar = view.findViewById(R.id.btn_dagregar);
        edtMcantidad = view.findViewById(R.id.edt_dcantidad);
        imvLogoD = view.findViewById(R.id.imv_logoD);
        imvDproducto = view.findViewById(R.id.imv_dproducto);
    }
}
