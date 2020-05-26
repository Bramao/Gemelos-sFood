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

public class UsuarioPedidosAdapter extends RecyclerView.Adapter {

    List<Pedido> pedidos;
    UsuarioPedidosAdapter.OnItemClickListener miEscuchador;

    public UsuarioPedidosAdapter(List<Pedido> pedidos, UsuarioPedidosAdapter.OnItemClickListener miEscuchador){
        this.pedidos = pedidos;
        this.miEscuchador = miEscuchador;
    }

    public void setPedidos(List<Pedido> pedidos){
        this.pedidos = pedidos;
    }

    class UsuarioPedidosViewHolder extends RecyclerView.ViewHolder{
        TextView txvUPnombreu, txvUPcantidad, txvUPdomicilio, txvUPprecio;

        public UsuarioPedidosViewHolder(View itemView){
            super(itemView);

            txvUPnombreu = itemView.findViewById(R.id.txv_upnombreu);
            txvUPcantidad = itemView.findViewById(R.id.txv_upcantidad);
            txvUPdomicilio = itemView.findViewById(R.id.txv_updomicilio);
            txvUPprecio = itemView.findViewById(R.id.txv_upprecio);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_pedidos_layout, parent, false);

        return new UsuarioPedidosAdapter.UsuarioPedidosViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        UsuarioPedidosAdapter.UsuarioPedidosViewHolder miHolder = (UsuarioPedidosAdapter.UsuarioPedidosViewHolder) holder;
        final Pedido miPedido = pedidos.get(position);
        miHolder.txvUPnombreu.setText("Cliente: "+miPedido.getNombreu());
        miHolder.txvUPcantidad.setText("Cantidad de productos: "+miPedido.getCantidad());
        miHolder.txvUPdomicilio.setText("Domicilio: "+miPedido.isDomicilio());
        miHolder.txvUPprecio.setText("Precio: "+miPedido.getPrecio());

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
