package co.edu.unab.gemelosapp.model.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;

public class ProductoRepository {
    FirebaseFirestore firebaseFirestore;

    public ProductoRepository(Context context){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void escucharTodosP(final FirestoreCallBack<List<Producto>> firestoreCallBack){
        firebaseFirestore.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<Producto> productos = new ArrayList<>();
                if(queryDocumentSnapshots != null){
                    for(QueryDocumentSnapshot documento: queryDocumentSnapshots){
                        Producto miProducto = documento.toObject(Producto.class);
                        if(!miProducto.isExtra()){
                            miProducto.setId(documento.getId());
                            productos.add(miProducto);
                            Log.d("Productos", miProducto.getNombre()+" es producto.");
                        }else {
                            Log.d("Productos", miProducto.getNombre()+" es extra.");
                        }
                    }
                }
                firestoreCallBack.correcto(productos);
            }
        });
    }

    public void escucharTodosE(final FirestoreCallBack<List<Producto>> firestoreCallBack){
        firebaseFirestore.collection("productos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<Producto> productos = new ArrayList<>();
                if(queryDocumentSnapshots != null){
                    for(QueryDocumentSnapshot documento: queryDocumentSnapshots){
                        Producto miProducto = documento.toObject(Producto.class);
                        if(miProducto.isExtra()){
                            miProducto.setId(documento.getId());
                            productos.add(miProducto);
                            Log.d("Productos", miProducto.getNombre()+" es extra.");
                        }else {
                            Log.d("Productos", miProducto.getNombre()+" es producto.");
                        }
                    }
                }
                firestoreCallBack.correcto(productos);
            }
        });
    }

    public void agregarFirestore(final Producto producto, final FirestoreCallBack<Producto> firestoreCallBack){
        firebaseFirestore.collection("productos").add(producto).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isComplete()){
                    producto.setId(task.getResult().getId());
                    editarFirestore(producto, new FirestoreCallBack<Producto>() {
                        @Override
                        public void correcto(Producto respuesta) {
                            firestoreCallBack.correcto(producto);
                        }
                    });
                }
            }
        });
    }

    public void editarFirestore(final Producto producto, final FirestoreCallBack<Producto> firestoreCallBack){
        firebaseFirestore.collection("productos").document(producto.getId()).set(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    firestoreCallBack.correcto(producto);
                }
            }
        });
    }

    public void eliminarFirestore(final Producto producto, final FirestoreCallBack<Producto> firestoreCallBack){
        firebaseFirestore.collection("productos").document(producto.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    firestoreCallBack.correcto(producto);
                }
            }
        });
    }
}
