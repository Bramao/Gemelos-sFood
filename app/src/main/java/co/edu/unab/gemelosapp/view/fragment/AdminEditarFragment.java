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
import android.widget.TextView;
import android.widget.Toast;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminEditarFragment extends Fragment {

    private TextView txvAEnombrep;
    private EditText edtAEnombre, edtAEdescripcion, edtAEfoto, edtAEprecio;
    private Button btnAEeditar, btnAEeliminar;
    private ProductoRepository productoRepository;

    public AdminEditarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productoRepository = new ProductoRepository(getContext());
        final Producto producto = AdminEditarFragmentArgs.fromBundle(getArguments()).getProducto();

        txvAEnombrep.setText("Editar: "+producto.getNombre());
        edtAEnombre.setText(producto.getNombre());
        edtAEdescripcion.setText(producto.getDescripcion());
        edtAEfoto.setText(producto.getFoto());
        edtAEprecio.setText(String.valueOf(producto.getPrecio()));

        btnAEeditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto.setNombre(edtAEnombre.getText().toString());
                producto.setDescripcion(edtAEdescripcion.getText().toString());
                producto.setFoto(edtAEfoto.getText().toString());
                producto.setPrecio(Double.parseDouble(edtAEprecio.getText().toString()));

                productoRepository.editarFirestore(producto, new FirestoreCallBack<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        Navigation.findNavController(getView()).navigate(AdminEditarFragmentDirections.actionAdminEditarFragmentToAdminListadoFragment());
                        //finish();
                    }
                });
            }
        });

        btnAEeliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoRepository.eliminarFirestore(producto, new FirestoreCallBack<Producto>() {
                    @Override
                    public void correcto(Producto respuesta) {
                        Toast.makeText(getContext(), "Se eliminó el producto: "+producto.getNombre(), Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(AdminEditarFragmentDirections.actionAdminEditarFragmentToAdminListadoFragment());
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
        View view = inflater.inflate(R.layout.fragment_admin_editar, container, false);
        this.asociarElementos(view);
        return  view;
    }

    private void asociarElementos(View view){
        txvAEnombrep = view.findViewById(R.id.txv_aenombrep);
        edtAEnombre = view.findViewById(R.id.edt_aenombre);
        edtAEdescripcion = view.findViewById(R.id.edt_aedescripcion);
        edtAEfoto =  view.findViewById(R.id.edt_aefoto);
        edtAEprecio =  view.findViewById(R.id.edt_aeprecio);
        btnAEeditar = view.findViewById(R.id.btn_aeeditar);
        btnAEeliminar =  view.findViewById(R.id.btn_aeeliminar);
    }
}
