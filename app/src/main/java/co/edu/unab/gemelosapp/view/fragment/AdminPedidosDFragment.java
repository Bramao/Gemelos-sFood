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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Pedido;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.PedidoRepository;
import co.edu.unab.gemelosapp.view.adapter.AdminPedidosDAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPedidosDFragment extends Fragment {

    private ImageView imvLogoAPD;
    private TextView txvAPDnombreu;
    private RecyclerView rcvAPDpedido;
    private Button btnAPDterminar;
    private List<Producto> productosP;
    private PedidoRepository pedidoRepository;
    private AdminPedidosDAdapter miAdaptador;

    public AdminPedidosDFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Pedido pedido = AdminPedidosDFragmentArgs.fromBundle(getArguments()).getPedido();

        txvAPDnombreu.setText("PEDIDO DE: "+pedido.getNombreu());

        pedidoRepository = new PedidoRepository(getContext());
        productosP = new ArrayList<>();
        this.controlAdaptador();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcvAPDpedido.setLayoutManager(manager);
        rcvAPDpedido.setAdapter(miAdaptador);
        rcvAPDpedido.setHasFixedSize(true);
        this.getData(pedido);

        btnAPDterminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedidoRepository.eliminarFirestore(pedido, new FirestoreCallBack<Pedido>() {
                    @Override
                    public void correcto(Pedido respuesta) {
                        pedido.setFinalizado(true);
                        Toast.makeText(getContext(), "Pedido terminado", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(AdminPedidosDFragmentDirections.actionAdminPedidosDFragmentToAdminPedidosFragment());
                    }
                });
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_pedidos_d, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void controlAdaptador(){
        miAdaptador = new AdminPedidosDAdapter(productosP, new AdminPedidosDAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                Toast.makeText(getContext(), "Producto: "+producto.getCantidad()+"x "+producto.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(final Pedido pedido){
        pedidoRepository.obtenerPedido(pedido, new FirestoreCallBack<Pedido>() {
            @Override
            public void correcto(Pedido respuesta) {
                productosP = pedido.getMisProductos();
                miAdaptador.setProductosP(productosP);
                miAdaptador.notifyDataSetChanged();
            }
        });
    }



    private void asociarElementos(View view){
        imvLogoAPD = view.findViewById(R.id.imv_logoAPD);
        txvAPDnombreu =  view.findViewById(R.id.txv_apdnombreu);
        rcvAPDpedido = view.findViewById(R.id.rcv_apdpedido);
        btnAPDterminar = view.findViewById(R.id.btn_apdterminar);
    }
}
