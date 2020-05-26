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
import co.edu.unab.gemelosapp.view.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminMenuFragment extends Fragment {

    private ImageView imvLogoAM;
    private Button btnAMproductos, btnAMextras, btnAMpedidos,  btnAMcerrar;

    public AdminMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAMproductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(AdminMenuFragmentDirections.actionAdminMenuFragmentToAdminListadoFragment(false));
            }
        });

        btnAMextras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(AdminMenuFragmentDirections.actionAdminMenuFragmentToAdminListadoFragment(true));
            }
        });

        btnAMpedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(AdminMenuFragmentDirections.actionAdminMenuFragmentToAdminPedidosFragment());
            }
        });

        btnAMcerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences misPreferencias = getActivity().getSharedPreferences(getString(R.string.misDatos), 0);
                SharedPreferences.Editor miEditor = misPreferencias.edit();
                miEditor.remove("admin");
                miEditor.remove("logueado");
                miEditor.remove("usuario");
                miEditor.remove("id");
                miEditor.apply();

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
        View view = inflater.inflate(R.layout.fragment_admin_menu, container, false);
        this.asociarElementos(view);
        return view;
    }

    private void asociarElementos(View view){
        imvLogoAM =  view.findViewById(R.id.imv_logoAM);
        btnAMproductos =  view.findViewById(R.id.btn_amproductos);
        btnAMpedidos =  view.findViewById(R.id.btn_ampedidos);
        btnAMcerrar =  view.findViewById(R.id.btn_amcerrar);
        btnAMextras = view.findViewById(R.id.btn_amextras);
    }
}
