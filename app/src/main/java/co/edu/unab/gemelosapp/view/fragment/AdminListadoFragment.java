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
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;
import co.edu.unab.gemelosapp.view.adapter.AdminListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminListadoFragment extends Fragment {

    private ImageView imvLogoAL;
    private RecyclerView rcvALlistadoP;
    private Button btn_alagregar;
    private AdminListAdapter miAdaptador;
    private List<Producto> productos;
    private ProductoRepository productoRepository;

    public AdminListadoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productoRepository = new ProductoRepository(getContext());
        productos = new ArrayList<>();
        this.controlAdaptador();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvALlistadoP.setLayoutManager(manager);
        rcvALlistadoP.setAdapter(miAdaptador);
        rcvALlistadoP.setHasFixedSize(true);
        this.getDataFirestore();

        btn_alagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(AdminListadoFragmentDirections.actionAdminListadoFragmentToAdminAgregarFragment());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_listado, container, false);
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
        miAdaptador = new AdminListAdapter(productos, new AdminListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {

                Navigation.findNavController(getView()).navigate(AdminListadoFragmentDirections.actionAdminListadoFragmentToAdminEditarFragment(producto));
            }
        });
    }

    private void asociarElementos(View view){
        imvLogoAL = view.findViewById(R.id.imv_logoAL);
        rcvALlistadoP = view.findViewById(R.id.rcv_allistadoP);
        btn_alagregar =  view.findViewById(R.id.btn_alagregar);
    }
}
