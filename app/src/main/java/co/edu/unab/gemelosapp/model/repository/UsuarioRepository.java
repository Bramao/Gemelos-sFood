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

import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Usuario;

public class UsuarioRepository {
    FirebaseFirestore firebaseFirestore;
    String path = "usuarios";

    public UsuarioRepository(Context context) {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public  void agregarUsuario(final Usuario usuario, final FirestoreCallBack<Usuario> firestoreCallBack){
        firebaseFirestore.collection(path).add(usuario).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                if(task.isSuccessful()){
                    usuario.setId(task.getResult().getId());
                    editarUsuario(usuario, new FirestoreCallBack<Usuario>() {
                        @Override
                        public void correcto(Usuario respuesta) {
                            firestoreCallBack.correcto(usuario);
                        }
                    });
                }
            }
        });
    }

    public void editarUsuario(final Usuario usuario, final FirestoreCallBack<Usuario> firestoreCallBack){
        firebaseFirestore.collection(path).document(usuario.getId()).set(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    firestoreCallBack.correcto(usuario);
                }
            }
        });
    }

}
