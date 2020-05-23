package co.edu.unab.gemelosapp.model.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Pedido;

public class PedidoRepository {
    FirebaseFirestore firebaseFirestore;

    public PedidoRepository(Context context){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void escucharTodos(final FirestoreCallBack<List<Pedido>> firestoreCallBack){
        firebaseFirestore.collection("pedidos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                List<Pedido> pedidos = new ArrayList<>();
                if(queryDocumentSnapshots != null){
                    for(QueryDocumentSnapshot documento : queryDocumentSnapshots){
                        Pedido miPedido = documento.toObject(Pedido.class);
                        miPedido.setId(documento.getId());
                        pedidos.add(miPedido);
                    }
                }
                firestoreCallBack.correcto(pedidos);
            }
        });
    }

    public void obtenerPedido(final Pedido pedido, final FirestoreCallBack<Pedido> firestoreCallBack){
        DocumentReference documentReference = firebaseFirestore.collection("pedidos").document(pedido.getId());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               if (task.isComplete()){
                   firestoreCallBack.correcto(pedido);
               }
            }
        });
    }

    public void agregarFirestore(final Pedido pedido, final FirestoreCallBack<Pedido> firestoreCallBack){
        firebaseFirestore.collection("pedidos").add(pedido).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isComplete()){
                    firestoreCallBack.correcto(pedido);
                }
            }
        });
    }

    public void eliminarFirestore(final Pedido pedido, final FirestoreCallBack<Pedido> firestoreCallBack){
        firebaseFirestore.collection("pedidos").document(pedido.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isComplete()){
                    firestoreCallBack.correcto(pedido);
                }
            }
        });
    }
}
