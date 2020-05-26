package co.edu.unab.gemelosapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosP;
import co.edu.unab.gemelosapp.model.bd.local.PedidoDAO;
import co.edu.unab.gemelosapp.model.entity.Pedido;
import co.edu.unab.gemelosapp.view.adapter.UsuarioPedidosAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioPedidosFragment extends Fragment {

    private RecyclerView rcv_UPlistado;
    private PedidoDAO pedidoDAO;
    private List<Pedido> pedidos;
    private UsuarioPedidosAdapter usuarioPedidosAdapter;
    public UsuarioPedidosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseDatosP baseDatosP = BaseDatosP.obtenerInstancia(getContext());
        pedidoDAO = baseDatosP.pedidoDAO();
        pedidos =  new ArrayList<>();
        this.controlAdaptador();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rcv_UPlistado.setLayoutManager(manager);
        rcv_UPlistado.setAdapter(usuarioPedidosAdapter);
        rcv_UPlistado.setHasFixedSize(true);
        this.getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario_pedidos, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void controlAdaptador(){
        usuarioPedidosAdapter = new UsuarioPedidosAdapter(pedidos, new UsuarioPedidosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pedido pedido, int position) {
                Toast.makeText(getContext(), "Pedido "+pedido.isFinalizado(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getData(){
        pedidos = pedidoDAO.obtenerTodos();
        usuarioPedidosAdapter.setPedidos(pedidos);
        usuarioPedidosAdapter.notifyDataSetChanged();
    }

    private void asociarElementos(View view){
        rcv_UPlistado = view.findViewById(R.id.rcv_uplistado);
    }
}
