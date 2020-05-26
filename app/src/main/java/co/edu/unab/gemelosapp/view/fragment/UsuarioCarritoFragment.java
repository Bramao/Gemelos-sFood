package co.edu.unab.gemelosapp.view.fragment;

import android.content.SharedPreferences;
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

import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosC;
import co.edu.unab.gemelosapp.model.bd.local.CarritoDAO;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Pedido;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.PedidoRepository;
import co.edu.unab.gemelosapp.view.adapter.CarritoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioCarritoFragment extends Fragment {

    private ImageView imvLogoC;
    private Button btnCcomprar, btnUCextras, btnCCproductos;
    private TextView txvCarritoVacio, txvValorTotal;
    private RecyclerView rcvListadoC;
    private List<Producto> productosC;
    private CarritoAdapter miAdaptador;
    private CarritoDAO carritoDAO;
    private PedidoRepository pedidoRepository;

    public UsuarioCarritoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences misPreferencias = getActivity().getSharedPreferences(getString(R.string.misDatos), 0);
        final String nombreu = misPreferencias.getString("usuario", "");
        final String token = misPreferencias.getString("token", "");

        BaseDatosC bd = BaseDatosC.obtenerInstancia(getContext());
        carritoDAO = bd.carritoDAO();

        pedidoRepository = new PedidoRepository(getContext());

        btnCcomprar.setVisibility(View.INVISIBLE);
        txvValorTotal.setVisibility(View.INVISIBLE);


        final Producto miProducto = UsuarioCarritoFragmentArgs.fromBundle(getArguments()).getProducto();
        if(miProducto!=null){
            productosC = carritoDAO.obtenerTodos();
            //Producto productoTmp = carritoDAO.verificar(miProducto.getNombre(), miProducto.getPrecio());
            //if(productoTmp!=null){
            carritoDAO.agregar(miProducto);
            productosC = carritoDAO.obtenerTodos();
        }else {
            productosC = carritoDAO.obtenerTodos();
        }

        verificarCarrito();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());

        miAdaptador = new CarritoAdapter(productosC, new CarritoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto, int position) {
                carritoDAO.borrar(producto);
                productosC = carritoDAO.obtenerTodos();
                verificarCarrito();
                miAdaptador.setProductosC(productosC);
                miAdaptador.notifyDataSetChanged();
                txvValorTotal.setText("Total: $"+(carritoDAO.getTotal()));
            }
        });

        rcvListadoC.setLayoutManager(manager);
        rcvListadoC.setAdapter(miAdaptador);

        btnUCextras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(UsuarioCarritoFragmentDirections.actionUsuarioCarritoFragmentToUsuarioListadoEFragment());
            }
        });

        btnCCproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(UsuarioCarritoFragmentDirections.actionUsuarioCarritoFragmentToUsuarioListadoFragment());
            }
        });

        btnCcomprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pedido nuevoPedido = new Pedido(nombreu, productosC, carritoDAO.getCantidad(),false,false, carritoDAO.getTotal());
                nuevoPedido.setToken(token);
                Navigation.findNavController(getView()).navigate(UsuarioCarritoFragmentDirections.actionUsuarioCarritoFragmentToUsuarioEntregaFragment(nuevoPedido));
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario_carrito, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void verificarCarrito(){
        if(productosC.size()!=0){
            btnCcomprar.setVisibility(View.VISIBLE);
            txvValorTotal.setVisibility(View.VISIBLE);
            txvCarritoVacio.setVisibility(View.INVISIBLE);
            txvValorTotal.setText("Total: $"+(carritoDAO.getTotal()));

        }
    }

    private void asociarElementos(View view){
        imvLogoC = view.findViewById(R.id.imv_logoC);
        btnCcomprar = view.findViewById(R.id.btn_ccomprar);
        rcvListadoC = view.findViewById(R.id.rcv_listadoC);
        txvCarritoVacio = view.findViewById(R.id.txv_carritoVacio);
        txvValorTotal = view.findViewById(R.id.txv_valorTotal);
        btnUCextras = view.findViewById(R.id.btn_ucextras);
        btnCCproductos = view.findViewById(R.id.btn_ccproductos);
    }
}
