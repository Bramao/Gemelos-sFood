package co.edu.unab.gemelosapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter {
    List<Producto> productosC;
    OnItemClickListener miEscuchador;

    public CarritoAdapter(List<Producto> productosC, OnItemClickListener miEscuchador) {
        this.productosC = productosC;
        this.miEscuchador = miEscuchador;
    }

    public void setProductosC(List<Producto> productosC){
        this.productosC = productosC;
    }

    class CarritoViewHolder extends RecyclerView.ViewHolder{
        TextView txvCnombre, txvCprecio, txvCcantidad;
        ImageView imvFotoC;

        public CarritoViewHolder(View itemView){
            super(itemView);

            txvCnombre = itemView.findViewById(R.id.txv_cnombre);
            txvCprecio = itemView.findViewById(R.id.txv_cprecio);
            txvCcantidad = itemView.findViewById(R.id.txv_ccantidad);
            imvFotoC = itemView.findViewById(R.id.imv_fotoC);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_layout, parent, false);
        return new CarritoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        CarritoViewHolder miHolder = (CarritoViewHolder) holder;
        final Producto miProducto = productosC.get(position);
        miHolder.txvCnombre.setText(miProducto.getNombre());
        miHolder.txvCprecio.setText("$"+miProducto.getPrecio());
        miHolder.txvCcantidad.setText("x"+miProducto.getCantidad());
        Picasso.get().load(miProducto.getFoto()).resize(70,70).into(miHolder.imvFotoC);

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miEscuchador.onItemClick(miProducto, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productosC.size();
    }

    interface OnItemClickListener{
        void onItemClick(Producto producto, int position);
    }
}
