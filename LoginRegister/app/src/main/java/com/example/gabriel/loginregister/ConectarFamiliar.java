package com.example.gabriel.loginregister;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConectarFamiliar extends AppCompatActivity {


    public static final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectar_familiar);

        final EditText etUserfamiliar = (EditText) findViewById(R.id.etUserfamiliar);
        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bConect = (Button) findViewById(R.id.bConect);


        bConect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(etUserfamiliar.getText().toString().isEmpty() || etUser.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty())
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Uno o mas campos estan vacios", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                    toast.show();
                }
                else{


                    final String userfamiliar = etUserfamiliar.getText().toString();
                    final String user = etUser.getText().toString();
                    final String password = etPassword.getText().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {

                                    Intent intent = new Intent(ConectarFamiliar.this, MenuFamiliarActivity.class);
                                    ConectarFamiliar.this.startActivity(intent);


                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ConectarFamiliar.this);
                                    builder.setMessage("Fallo en el ingreso")
                                            .setNegativeButton("Reintentar", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }
                    };

                    ConectFaRequest ConectFaRequest = new ConectFaRequest(userfamiliar, user, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ConectarFamiliar.this);
                    queue.add(ConectFaRequest);
                }
            }


        });


    }
}
