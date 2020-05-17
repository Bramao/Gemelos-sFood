package co.edu.unab.gemelosapp.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import co.edu.unab.gemelosapp.R;
import co.edu.unab.gemelosapp.model.bd.local.BaseDatosC;
import co.edu.unab.gemelosapp.model.bd.local.CarritoDAO;
import co.edu.unab.gemelosapp.view.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsuarioMenuFragment extends Fragment {


    private Button btnMproductos, btnMcarrito, btnMcerrarS;
    private ImageView imvLogoM;
    private CarritoDAO carritoDAO;
    public UsuarioMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BaseDatosC bd = BaseDatosC.obtenerInstancia(getContext());
        carritoDAO = bd.carritoDAO();

        btnMproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(UsuarioMenuFragmentDirections.actionUsuarioMenuFragmentToUsuarioListadoFragment());
            }
        });

        btnMcarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(UsuarioMenuFragmentDirections.actionUsuarioMenuFragmentToUsuarioCarritoFragment(null));
            }
        });

        btnMcerrarS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences misPreferencias = getActivity().getSharedPreferences(getString(R.string.misDatos), 0);
                SharedPreferences.Editor miEditor = misPreferencias.edit();
                miEditor.clear();
                miEditor.apply();

                carritoDAO.borrarTodo();

                Intent in = new Intent(getContext(), LoginActivity.class);
                startActivity(in);
                //finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usuario_menu, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void asociarElementos(View view){
        btnMproductos = view.findViewById(R.id.btn_mproductos);
        btnMcarrito = view.findViewById(R.id.btn_mcarrito);
        imvLogoM = view.findViewById(R.id.imv_logoM);
        btnMcerrarS = view.findViewById(R.id.btn_mcerrars);
    }
}
