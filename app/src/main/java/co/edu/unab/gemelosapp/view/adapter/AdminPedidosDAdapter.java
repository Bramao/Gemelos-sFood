package co.edu.unab.gemelosapp.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.entity.Producto;

public class AdminPedidosDAdapter extends RecyclerView.Adapter {

    List<Producto> productosP;
    OnItemClickListener miEscuchador;

    public AdminPedidosDAdapter(List<Producto> productosP, OnItemClickListener miEscuchador) {
        this.productosP = productosP;
        this.miEscuchador = miEscuchador;
    }

    public void setProductosP(List<Producto> productosP){
        this.productosP = productosP;
    }

    class ProductoPViewHolder extends RecyclerView.ViewHolder{
        ImageView imvAPDfoto;
        TextView txvAPDnombre, txvAPDcantidad, txvAPDdescripcion;
        Switch aSwitch;

        public ProductoPViewHolder(View view){
            super(view);

            imvAPDfoto = view.findViewById(R.id.imv_apdfoto);
            txvAPDnombre = view.findViewById(R.id.txv_apdnombre);
            txvAPDcantidad = view.findViewById(R.id.txv_apdcantidad);
            txvAPDdescripcion = view.findViewById(R.id.txv_apddescripcion);
            aSwitch = view.findViewById(R.id.switch1);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_pedido_d_layout, parent, false);
        return new ProductoPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ProductoPViewHolder miHolder = (ProductoPViewHolder) holder;
        final Producto miProducto = productosP.get(position);
        miHolder.txvAPDnombre.setText(miProducto.getNombre());
        miHolder.txvAPDcantidad.setText(String.valueOf(miProducto.getCantidad()));
        miHolder.txvAPDdescripcion.setText(miProducto.getDescripcion());
        Picasso.get().load(miProducto.getFoto()).resize(70,70).into(miHolder.imvAPDfoto);

            if(miHolder.aSwitch.isChecked()){
                Log.d("Switch", "Activado");
            }


        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v.getId()==R.id.switch1){
                    Log.d("Switch", "Activado");
                }
                miEscuchador.onItemClick(miProducto, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productosP.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Producto producto, int position);
    }
}
