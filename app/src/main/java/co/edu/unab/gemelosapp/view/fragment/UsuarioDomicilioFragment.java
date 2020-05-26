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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosC;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosP;
import co.edu.unab.gemelosapp.model.bd.local.CarritoDAO;
import co.edu.unab.gemelosapp.model.bd.local.PedidoDAO;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Pedido;
import co.edu.unab.gemelosapp.model.entity.Usuario;
import co.edu.unab.gemelosapp.model.repository.PedidoRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioDomicilioFragment extends Fragment {

    private EditText edtUDdireccion, edtUDnumero;
    private Button btnUDrealizar;
    private PedidoRepository pedidoRepository;
    private PedidoDAO pedidoDAO;
    private CarritoDAO carritoDAO;

    public UsuarioDomicilioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Pedido pedido = UsuarioDomicilioFragmentArgs.fromBundle(getArguments()).getPedido();
        final Pedido miPedido = new Pedido(pedido.getNombreu(), pedido.getCantidad(), false, false, pedido.getPrecio());


        BaseDatosP baseDatosP = BaseDatosP.obtenerInstancia(getContext());
        pedidoDAO = baseDatosP.pedidoDAO();
        BaseDatosC baseDatosC = BaseDatosC.obtenerInstancia(getContext());
        carritoDAO = baseDatosC.carritoDAO();

        btnUDrealizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedido.setDomicilio(true);
                miPedido.setDomicilio(true);
                pedidoRepository = new PedidoRepository(getActivity());
                final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot documento: task.getResult()){
                            Usuario usuarioTmp = documento.toObject(Usuario.class);
                            if(usuarioTmp.getNombre().equals(pedido.getNombreu())){
                                usuarioTmp.setDireccion(edtUDdireccion.getText().toString());
                                usuarioTmp.setNumero(Integer.parseInt(edtUDnumero.getText().toString()));
                                firebaseFirestore.collection("usuarios").document(usuarioTmp.getId()).set(usuarioTmp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        pedidoRepository.agregarFirestore(pedido, new FirestoreCallBack<Pedido>() {
                                            @Override
                                            public void correcto(Pedido respuesta) {
                                                miPedido.setId(respuesta.getId());
                                                pedidoDAO.agregar(miPedido);
                                                carritoDAO.borrarTodo();
                                                Toast.makeText(getContext(), "Pedido realizado", Toast.LENGTH_LONG).show();
                                                Navigation.findNavController(getView()).navigate(UsuarioDomicilioFragmentDirections.actionUsuarioDomicilioFragmentToUsuarioMenuFragment());
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario_domicilio, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void asociarElementos(View view){
        edtUDdireccion = view.findViewById(R.id.edt_uddireccion);
        edtUDnumero = view.findViewById(R.id.edt_udnumero);
        btnUDrealizar = view.findViewById(R.id.btn_udrealizar);
    }
}
