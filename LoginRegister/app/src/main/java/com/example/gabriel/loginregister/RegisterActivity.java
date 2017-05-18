package com.example.gabriel.loginregister;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    EditText etAge;
    EditText etName;
    EditText etUsername;
    EditText etPassword;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        final TextView tvResultado = (TextView)findViewById(R.id.tvResultado) ;


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(etAge.getText().toString().isEmpty() ||
                       etName.getText().toString().isEmpty() ||
                       etUsername.getText().toString().isEmpty() ||
                       etPassword.getText().toString().isEmpty())
               {
                   Toast toast = Toast.makeText(getApplicationContext(),"Uno o mas campos estan vacios", Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.CENTER|Gravity.CENTER,0,0);
                   toast.show();
               }
               else
               {

                   final String name = etName.getText().toString();
                   final String username = etUsername.getText().toString();
                   final int age = Integer.parseInt(etAge.getText().toString());
                   final String password = etPassword.getText().toString();

                   Response.Listener<String> responseListener = new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           try {
                               JSONObject jsonResponse = new JSONObject(response);
                               boolean success = jsonResponse.getBoolean("success");

                               if (success) {
                                   AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                   builder.setMessage("Registrado Correctamente")
                                           .setNegativeButton("Aceptar", null)
                                           .create()
                                           .show();
                                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                   RegisterActivity.this.startActivity(intent);

                               } else {
                                   AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                   builder.setMessage("Fallo en el Registro")
                                           .setNegativeButton("Reintentar", null)
                                           .create()
                                           .show();
                               }

                           } catch (JSONException e) {
                               e.printStackTrace();
                           }

                       }
                   };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
               }
            }
        });

    }
}
