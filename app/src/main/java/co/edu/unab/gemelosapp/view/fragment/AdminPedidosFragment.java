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
import co.edu.unab.gemelosapp.model.entity.Pedido;
import co.edu.unab.gemelosapp.model.repository.PedidoRepository;
import co.edu.unab.gemelosapp.view.adapter.AdminPedidosAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPedidosFragment extends Fragment {

    private ImageView imvLogoAP;
    private RecyclerView rcvAPlistado;
    private AdminPedidosAdapter adminPedidosAdapter;
    private List<Pedido> pedidos;
    private PedidoRepository pedidoRepository;

    public AdminPedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pedidoRepository = new PedidoRepository(getContext());
        pedidos = new ArrayList<>();
        this.controlAdaptador();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvAPlistado.setLayoutManager(manager);
        rcvAPlistado.setAdapter(adminPedidosAdapter);
        rcvAPlistado.setHasFixedSize(true);
        this.getDataFirestore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_pedidos, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void getDataFirestore(){
        pedidoRepository.escucharTodos(new FirestoreCallBack<List<Pedido>>() {
            @Override
            public void correcto(List<Pedido> respuesta) {
                adminPedidosAdapter.setPedidos(respuesta);
                adminPedidosAdapter.notifyDataSetChanged();
            }
        });
    }

    private void controlAdaptador(){
        adminPedidosAdapter = new AdminPedidosAdapter(pedidos, new AdminPedidosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pedido pedido, int position) {
                Navigation.findNavController(getView()).navigate(AdminPedidosFragmentDirections.actionAdminPedidosFragmentToAdminPedidosDFragment(pedido));
            }
        });


    }

    private void asociarElementos(View view){
        imvLogoAP = view.findViewById(R.id.imv_logoAP);
        rcvAPlistado =  view.findViewById(R.id.rcv_aplistado);
    }
}
