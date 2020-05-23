package co.edu.unab.gemelosapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.entity.Pedido;

public class AdminPedidosAdapter extends RecyclerView.Adapter {
    List<Pedido> pedidos;
    AdminPedidosAdapter.OnItemClickListener miEscuchador;

    public AdminPedidosAdapter(List<Pedido> pedidos, AdminPedidosAdapter.OnItemClickListener miEscuchador){
        this.pedidos = pedidos;
        this.miEscuchador =  miEscuchador;
    }

    public void setPedidos(List<Pedido> pedidos){
        this.pedidos = pedidos;
    }

    class AdminPedidosViewHolder extends RecyclerView.ViewHolder{
        TextView txvAPnombreu, txvAPcantidad, txvAPestado, txvAPdomicilio;

        public AdminPedidosViewHolder(View itemView){
            super(itemView);

            txvAPnombreu = itemView.findViewById(R.id.txv_apnombreu);
            txvAPcantidad = itemView.findViewById(R.id.txv_apcantidad);
            txvAPestado = itemView.findViewById(R.id.txv_apestado);
            txvAPdomicilio = itemView.findViewById(R.id.txv_apdomicilio);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_pedidos_layout, parent, false);

        return new AdminPedidosViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        AdminPedidosViewHolder miHolder = (AdminPedidosViewHolder) holder;
        final Pedido miPedido = pedidos.get(position);
        miHolder.txvAPnombreu.setText("Cliente: "+miPedido.getNombreu());
        miHolder.txvAPcantidad.setText("Cantidad de productos: "+miPedido.getCantidad());
        miHolder.txvAPestado.setText("Estado: "+miPedido.isFinalizado());
        miHolder.txvAPdomicilio.setText("Domicilio: "+miPedido.isDomicilio());

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.onItemClick(miPedido,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Pedido pedido, int position);
    }
}
