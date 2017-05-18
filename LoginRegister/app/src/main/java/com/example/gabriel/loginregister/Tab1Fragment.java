package com.example.gabriel.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Gabriel on 04-05-2017.
 */

public class Tab1Fragment extends Fragment {
    private static final String TAG ="Tab1Fragment";

    private Button bMedicamento;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        final Button bMedicamento = (Button) view.findViewById(R.id.bMedicamento);

        bMedicamento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent medicamentoIntent = new Intent(Tab1Fragment.this.getActivity(), ingresoTratamiento.class);
                Tab1Fragment.this.startActivity(medicamentoIntent);
            }});

        return view;

    }


}
