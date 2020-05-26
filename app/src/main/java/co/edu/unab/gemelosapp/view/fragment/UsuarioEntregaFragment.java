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
import android.widget.Toast;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosC;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosP;
import co.edu.unab.gemelosapp.model.bd.local.CarritoDAO;
import co.edu.unab.gemelosapp.model.bd.local.PedidoDAO;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Pedido;
import co.edu.unab.gemelosapp.model.repository.PedidoRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioEntregaFragment extends Fragment {

    private Button btnUEdomicilio, btnUErecogida;
    private PedidoDAO pedidoDAO;
    private CarritoDAO carritoDAO;
    PedidoRepository pedidoRepository;


    public UsuarioEntregaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Pedido pedido = UsuarioEntregaFragmentArgs.fromBundle(getArguments()).getPedido();
        final Pedido miPedido = new Pedido(pedido.getNombreu(), pedido.getCantidad(), false, false, pedido.getPrecio());
        pedidoRepository = new PedidoRepository(getContext());

        BaseDatosP baseDatosP = BaseDatosP.obtenerInstancia(getContext());
        pedidoDAO = baseDatosP.pedidoDAO();
        BaseDatosC baseDatosC = BaseDatosC.obtenerInstancia(getContext());
        carritoDAO = baseDatosC.carritoDAO();

        btnUEdomicilio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(UsuarioEntregaFragmentDirections.actionUsuarioEntregaFragmentToUsuarioDomicilioFragment(pedido));
            }
        });

        btnUErecogida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.setDomicilio(false);
                miPedido.setDomicilio(false);
                pedidoRepository.agregarFirestore(pedido, new FirestoreCallBack<Pedido>() {
                    @Override
                    public void correcto(Pedido respuesta) {
                        miPedido.setId(respuesta.getId());
                        pedidoDAO.agregar(miPedido);
                        carritoDAO.borrarTodo();
                        Toast.makeText(getContext(), "Pedido realizado.", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getView()).navigate(UsuarioEntregaFragmentDirections.actionUsuarioEntregaFragmentToUsuarioMenuFragment());

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
        View view = inflater.inflate(R.layout.fragment_usuario_entrega, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void asociarElementos(View view){
        btnUEdomicilio = view.findViewById(R.id.btn_uedomicilo);
        btnUErecogida = view.findViewById(R.id.btn_uerecogida);
    }
}
