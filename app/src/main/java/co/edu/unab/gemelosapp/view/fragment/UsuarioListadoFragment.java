package co.edu.unab.gemelosapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;
import co.edu.unab.gemelosapp.view.adapter.ProductoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioListadoFragment extends Fragment {

    private ImageView imvLogoL;
    private RecyclerView rcvListadoP;
    private ProductoAdapter miAdaptador;
    private List<Producto> productos;
    private ProductoRepository productoRepository;

    public UsuarioListadoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productoRepository = new ProductoRepository(getContext());
        productos =new ArrayList<>();
        this.controlAdaptador();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvListadoP.setLayoutManager(manager);
        rcvListadoP.setAdapter(miAdaptador);
        rcvListadoP.setHasFixedSize(true);
        this.getDataFirestore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_usuario_listado, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void getDataFirestore(){
        productoRepository.escucharTodos(new FirestoreCallBack<List<Producto>>() {
            @Override
            public void correcto(List<Producto> respuesta) {
                miAdaptador.setProductos(respuesta);
                miAdaptador.notifyDataSetChanged();
            }
        });
    }

    private void controlAdaptador(){
        miAdaptador = new ProductoAdapter(productos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {

                Navigation.findNavController(getView()).navigate(UsuarioListadoFragmentDirections.actionUsuarioListadoFragmentToUsuarioDescripcionFragment(producto));

            }
        });
    }

    private void asociarElementos(View view){
        imvLogoL = view.findViewById(R.id.imv_logoList);
        rcvListadoP = view.findViewById(R.id.rcv_listadoP);
    }
}
