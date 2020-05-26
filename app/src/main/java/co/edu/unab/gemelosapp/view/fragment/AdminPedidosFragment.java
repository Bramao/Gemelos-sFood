package co.edu.unab.gemelosapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                notificar(pedido);
                Navigation.findNavController(getView()).navigate(AdminPedidosFragmentDirections.actionAdminPedidosFragmentToAdminPedidosDFragment(pedido));
            }
        });


    }

    private void notificar(Pedido pedido){
        Log.d("notify", "notificado1");
        RequestQueue myrequest = Volley.newRequestQueue(getContext());
        JSONObject json = new JSONObject();
        try {
            String token = pedido.getToken();
            json.put("to", token);
            JSONObject notificacion = new JSONObject();
            notificacion.put("titulo", "Peidido confirmado.");
            notificacion.put("detalle", "Tu pedido de "+pedido.getCantidad()+" prodcutos ha sido confirmado!");

            json.put("data", notificacion);

            String URL = "https://fcm.googleapis.com/fcm/send";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, json, null, null){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String,String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization", "key=AAAAoDZ9ykU:APA91bFCepcSYSqU0npuZYPRDFrp-rOm2rHFMwhNAxxhE_PRzsR06A2lUkldmnSk5DDYp046yiloLjjVG52FFPikQBvn7I4-JQ8sV49giEB1_ZhBT9orumlbcWqgwBa_GV-ZkfA7pEL6");

                    return header;
                }
            };
            Log.d("notify", "notificado2");
            myrequest.add(request);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void asociarElementos(View view){
        imvLogoAP = view.findViewById(R.id.imv_logoAP);
        rcvAPlistado =  view.findViewById(R.id.rcv_aplistado);
    }
}
