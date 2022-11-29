package com.example.tasca12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class AltaIniciar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.volverAtras);
        Button button1 = (Button) findViewById(R.id.mostrar);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                new Task().execute();


            }});

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VolverAtras();
            }
        });
    }
    public void VolverAtras(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class Task extends AsyncTask<Void, Void, Void>{
        String records = "", error = "";

        protected Void doInBackground(Void... voids) {

            EditText afectat = (EditText) findViewById(R.id.afectado);
            EditText tipuselement = (EditText) findViewById(R.id.tipoelemento);
            EditText ubicacio = (EditText) findViewById(R.id.ubicacion);
            EditText descripcionincidencia = (EditText) findViewById(R.id.descripcionincidencia);
            EditText fecha = (EditText) findViewById(R.id.fecha);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/database", "administrador", "admin,");
                String sql = "INSERT INTO incidencias (afectado, tipoelemento, ubicacion, descripcionincidencia, fecha) VALUES ('"+ afectat.getText() +"','"+ tipuselement.getText() +"','"+ ubicacio.getText() +"','"+ descripcionincidencia.getText() +"','" + fecha.getText() +"')";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();

            }
            catch (Exception e) {
                String error = e.toString();
            }
            return null;
        }


        }
    }
