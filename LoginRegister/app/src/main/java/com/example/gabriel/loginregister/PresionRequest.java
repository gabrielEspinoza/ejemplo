package com.example.gabriel.loginregister;

import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gabriel on 07-05-2017.
 */

public class PresionRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL ="http://humbertowhite.cl/medicapp/presion.php";
    private Map<String, String> params;

    public PresionRequest(int edad, int diastolica, int sistolica, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("edad", edad + "");
        params.put("diastolica", diastolica + "");
        params.put("sistolica", sistolica + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}



