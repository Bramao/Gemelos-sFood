package co.edu.unab.gemelosapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.R;

public class ProductoAdapter extends RecyclerView.Adapter {

    List<Producto> productos;
    OnItemClickListener miEscuchador;

    public ProductoAdapter(List<Producto> productos, OnItemClickListener miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder{
        TextView txvLNombre, txvLPrecio;
        ImageView imvFoto;

        public ProductoViewHolder(View itemView){
            super(itemView);

            txvLNombre = itemView.findViewById(R.id.txv_lnombre);
            txvLPrecio = itemView.findViewById(R.id.txv_lprecio);
            imvFoto = itemView.findViewById(R.id.imv_foto);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_layout, parent, false);

        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProductoViewHolder miHolder = (ProductoViewHolder) holder;
        final Producto miProducto = productos.get(position);
        miHolder.txvLNombre.setText(miProducto.getNombre());
        miHolder.txvLPrecio.setText("$"+miProducto.getPrecio());
        Picasso.get().load(miProducto.getFoto()).resize(70,70).into(miHolder.imvFoto);


        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    miEscuchador.onItemClick(miProducto,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Producto producto, int position);
    }
}
