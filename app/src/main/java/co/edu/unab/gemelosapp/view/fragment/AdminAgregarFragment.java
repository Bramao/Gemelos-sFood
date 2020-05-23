package co.edu.unab.gemelosapp.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.network.FirestoreCallBack;
import co.edu.unab.gemelosapp.model.entity.Producto;
import co.edu.unab.gemelosapp.model.repository.ProductoRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminAgregarFragment extends Fragment {

    private static final int CODIGO_FOTO_CAMARA = 333;
    private static final int CODIGO_FOTO_GALERIA = 332;
    private ImageView imvLogoAA, imv_AAfoto;
    private EditText edtAAnombre, edtAAdescripcion, edtAAprecio;
    private ImageButton biCamara, biGaleria;
    private Button btnAAagregar;
    private Uri fotoURI;
    String pathFoto;
    private StorageReference storageReference;
    private ProductoRepository productoRepository;

    public AdminAgregarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final boolean isExtra = AdminAgregarFragmentArgs.fromBundle(getArguments()).getIsExtra();
        storageReference = FirebaseStorage.getInstance().getReference();

        biCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inCamara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (inCamara.resolveActivity(getActivity().getPackageManager())!=null){
                    File foto = null;
                    try {
                        foto = crearFoto();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(foto!=null){
                        Uri fotoURI = FileProvider.getUriForFile(getActivity(), "co.edu.unab.gemelosapp.fileprovider", foto);
                        inCamara.putExtra(MediaStore.EXTRA_OUTPUT, fotoURI);
                        startActivityForResult(inCamara, CODIGO_FOTO_CAMARA);
                    }
                }
            }
        });

        biGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(inGaleria.resolveActivity(getActivity().getPackageManager())!=null){
                    startActivityForResult(inGaleria, CODIGO_FOTO_GALERIA);
                }
            }
        });

        productoRepository = new ProductoRepository(getContext());
        btnAAagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Producto nuevoProducto = new Producto(edtAAnombre.getText().toString(), edtAAdescripcion.getText().toString(), Double.parseDouble(edtAAprecio.getText().toString()), isExtra);
                String nombreFoto =fotoURI.getPath().substring(fotoURI.getPath().lastIndexOf("/"));
                final StorageReference misFotos = storageReference.child("fotos/"+fotoURI.getLastPathSegment());
                misFotos.putFile(fotoURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            misFotos.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String urlFoto = uri.toString();
                                    nuevoProducto.setFoto(urlFoto);

                                    productoRepository.agregarFirestore(nuevoProducto, new FirestoreCallBack<Producto>() {
                                        @Override
                                        public void correcto(Producto respuesta) {
                                            Navigation.findNavController(getView()).navigate(AdminAgregarFragmentDirections.actionAdminAgregarFragmentToAdminListadoFragment(isExtra));
                                }
                            });
                                    //finish();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CODIGO_FOTO_GALERIA&&resultCode==getActivity().RESULT_OK){
            fotoURI = data.getData();
            imv_AAfoto.setImageURI(fotoURI);
        }

        if(requestCode==CODIGO_FOTO_CAMARA&&resultCode==getActivity().RESULT_OK){
            fotoURI = Uri.fromFile(new File(pathFoto));
            imv_AAfoto.setImageURI(fotoURI);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_agregar, container, false);
        this.asociarElementos(view);
        return view;
    }

    private File crearFoto() throws IOException {
        File directorioTmp = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File foto = File.createTempFile("JPEG_", ".jpg", directorioTmp);
        pathFoto = foto.getAbsolutePath();

        return foto;
    }

    private void asociarElementos(View view){
        imvLogoAA = view.findViewById(R.id.imv_logoAA);
        imv_AAfoto = view.findViewById(R.id.imv_aafoto);
        edtAAnombre = view.findViewById(R.id.edt_aanombre);
        edtAAdescripcion = view.findViewById(R.id.edt_aadescripcion);
        edtAAprecio =  view.findViewById(R.id.edt_aaprecio);
        btnAAagregar =  view.findViewById(R.id.btn_aaagregar);
        biCamara = view.findViewById(R.id.bi_camara);
        biGaleria = view.findViewById(R.id.bi_galeria);
    }
}
