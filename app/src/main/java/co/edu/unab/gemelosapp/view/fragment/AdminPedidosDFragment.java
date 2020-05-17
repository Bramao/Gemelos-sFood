package co.edu.unab.gemelosapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.entity.Pedido;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminPedidosDFragment extends Fragment {

    private ImageView imvLogoAPD;
    private TextView txvAPDnombreu;
    private RecyclerView rcvAPDpedido;
    private Button btnAPDterminar;

    public AdminPedidosDFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Pedido pedido = AdminPedidosDFragmentArgs.fromBundle(getArguments()).getPedido();

        txvAPDnombreu.setText("PEDIDO DE: "+pedido.getNombreu());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_pedidos_d, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void asociarElementos(View view){
        imvLogoAPD = view.findViewById(R.id.imv_logoAPD);
        txvAPDnombreu =  view.findViewById(R.id.txv_apdnombreu);
        rcvAPDpedido = view.findViewById(R.id.rcv_apdpedido);
        btnAPDterminar = view.findViewById(R.id.btn_apdterminar);
    }
}
