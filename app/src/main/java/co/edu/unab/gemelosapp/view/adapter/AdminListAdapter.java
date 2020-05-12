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

public class AdminListAdapter extends RecyclerView.Adapter {
    List<Producto> productos;
    AdminListAdapter.OnItemClickListener miEscuchador;

    public AdminListAdapter(List<Producto> productos, AdminListAdapter.OnItemClickListener miEscuchador) {
        this.productos = productos;
        this.miEscuchador = miEscuchador;
    }

    public void setProductos(List<Producto> productos){
        this.productos = productos;
    }

    class AdminListViewHolder extends RecyclerView.ViewHolder{
        TextView txvALnombre, txvALprecio, txvALdescripcion;
        ImageView imvFotoAL;

        public AdminListViewHolder(View itemView){
            super(itemView);

            txvALnombre = itemView.findViewById(R.id.txv_alnombre);
            txvALprecio = itemView.findViewById(R.id.txv_alprecio);
            txvALdescripcion = itemView.findViewById(R.id.txv_aldescripcion);
            imvFotoAL = itemView.findViewById(R.id.imv_fotoAL);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_listado_layout, parent, false);

        return new AdminListViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        AdminListViewHolder miHolder = (AdminListViewHolder) holder;
        final Producto miProducto = productos.get(position);
        miHolder.txvALnombre.setText(miProducto.getNombre());
        miHolder.txvALprecio.setText("$"+miProducto.getPrecio());
        miHolder.txvALdescripcion.setText(miProducto.getDescripcion());
        Picasso.get().load(miProducto.getFoto()).resize(70,70).into(miHolder.imvFotoAL);


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
