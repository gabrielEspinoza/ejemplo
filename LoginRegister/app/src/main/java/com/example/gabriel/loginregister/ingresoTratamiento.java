package com.example.gabriel.loginregister;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class ingresoTratamiento extends AppCompatActivity implements View.OnClickListener {



    EditText etNombre;
    EditText etPastilla;
    EditText etHora;

    EditText etIngerida, etInicio, etFin;
    Button bHora, bInicio, bFinal;

    private int dia,mes,ano,hora,minutos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_tratamiento);

        final EditText etNombre = (EditText) findViewById(R.id.etNombre);
        final EditText etPastilla = (EditText) findViewById(R.id.etPastilla);
        final EditText etHora = (EditText) findViewById(R.id.etHora);




        final Button bAceptar = (Button) findViewById(R.id.bAceptar);


        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etNombre.getText().toString().isEmpty() ||
                        etPastilla.getText().toString().isEmpty() ||
                        etHora.getText().toString().isEmpty() ||
                        etIngerida.getText().toString().isEmpty() ||
                        etInicio.getText().toString().isEmpty() ||
                        etFin.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Uno o mas campos estan vacios", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {

                    final String nombre = etNombre.getText().toString();
                    final String pastilla = etPastilla.getText().toString();
                    final String hora = etHora.getText().toString();
                    final String ingerida = etIngerida.getText().toString();
                    final String inicio = etInicio.getText().toString();
                    final String fin = etFin.getText().toString();


                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {

                                    Toast toast = Toast.makeText(getApplicationContext(), "Medicamento Ingresado Correctamente", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                                    toast.show();

                                    Intent intent = new Intent(ingresoTratamiento.this, MenuActivity.class);
                                    ingresoTratamiento.this.startActivity(intent);

                                } else {

                                    Toast toast = Toast.makeText(getApplicationContext(), "Fallo en el ingreso del Medicamento", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                                    toast.show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    MedicamentoRequest medicamentoRequest = new MedicamentoRequest(nombre, pastilla, hora, ingerida ,inicio, fin, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ingresoTratamiento.this);
                    queue.add(medicamentoRequest);
                }
            }
        });


        bHora = (Button) findViewById(R.id.bHora);
        bInicio = (Button) findViewById(R.id.bInicio);
        bFinal = (Button) findViewById(R.id.bFinal);

        etIngerida = (EditText) findViewById(R.id.etIngerida);
        etInicio = (EditText) findViewById(R.id.etInicio);
        etFin = (EditText) findViewById(R.id.etFin);

        bHora.setOnClickListener(this);
        bInicio.setOnClickListener(this);
        bFinal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==bInicio){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etInicio.setText(dayOfMonth+"/"+ monthOfYear+1 +"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }


        if(v==bFinal){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etFin.setText(dayOfMonth+"/"+ monthOfYear+1 +"/"+year);
                }
            }
                    ,dia,mes,ano);
            datePickerDialog.show();
        }



        if (v==bHora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etIngerida.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }
}
