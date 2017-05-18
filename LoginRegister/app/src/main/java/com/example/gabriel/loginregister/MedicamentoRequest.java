package com.example.gabriel.loginregister;

import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gabriel on 08-05-2017.
 */

public class MedicamentoRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL ="http://humbertowhite.cl/medicapp/Medicamento.php";
    private Map<String, String> params;

    public MedicamentoRequest(String nombre, String pastilla, String hora, String ingerida, String inicio, String fin,
                              Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombre", nombre);
        params.put("pastilla", pastilla);
        params.put("hora", hora);
        params.put("ingerida", ingerida);
        params.put("inicio", inicio);
        params.put("fin", fin);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
