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

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminAgregarFragment extends Fragment {

    private ImageView imvLogoAA;
    private EditText edtAAnombre, edtAAdescripcion, edtAAfoto, edtAAprecio;
    private Button btnAAagregar;
    private ProductoRepository productoRepository;

    public AdminAgregarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productoRepository = new ProductoRepository(getContext());
        btnAAagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto nuevoProducto = new Producto(edtAAnombre.getText().toString(), edtAAdescripcion.getText().toString(), edtAAfoto.getText().toString(), Double.parseDouble(edtAAprecio.getText().toString()));
                productoRepository.agregarFirestore(nuevoProducto, new FirestoreCallBack<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        Navigation.findNavController(getView()).navigate(AdminAgregarFragmentDirections.actionAdminAgregarFragmentToAdminListadoFragment());
                        //finish();
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_agregar, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void asociarElementos(View view){
        imvLogoAA = view.findViewById(R.id.imv_logoAA);
        edtAAnombre = view.findViewById(R.id.edt_aanombre);
        edtAAdescripcion = view.findViewById(R.id.edt_aadescripcion);
        edtAAfoto = view.findViewById(R.id.edt_aafoto);
        edtAAprecio =  view.findViewById(R.id.edt_aaprecio);
        btnAAagregar =  view.findViewById(R.id.btn_aaagregar);
    }
}
