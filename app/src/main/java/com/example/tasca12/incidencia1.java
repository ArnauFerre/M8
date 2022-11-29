package com.example.tasca12;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class incidencia1 extends AppCompatActivity {
    String newString;
    boolean ayuda = false;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.incidencia1);

        new Task().execute();
        Button button = (Button) findViewById(R.id.button6);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ayuda = true;
                new Task().execute();
            }
        });


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= String.valueOf(extras.getInt("i"));
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("i");
        }
        System.out.println(newString);
            }
    class Task extends AsyncTask<Void, Void, Void> {
        String records = "", error = "";

        protected Void doInBackground(Void... voids) {


            TextView afectat2 = (TextView) findViewById(R.id.afectado1);
            TextView tipuselement = (TextView) findViewById(R.id.tipoelemento2);
            TextView ubicacio = (TextView) findViewById(R.id.ubicacion2);
            TextView descripcionincidencia = (TextView) findViewById(R.id.descripcion2);
            TextView fecha = (TextView) findViewById(R.id.fecha2);
            TextView resuelta = (TextView) findViewById(R.id.resuelta);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/database", "administrador", "admin");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM incidencias WHERE id =" + newString);

                while(resultSet.next()){
                    afectat2.setText(resultSet.getString(2));
                    tipuselement.setText(resultSet.getString(3));
                    ubicacio.setText(resultSet.getString(4));
                    descripcionincidencia.setText(resultSet.getString(5));
                    fecha.setText(resultSet.getString(6));
                    if (resultSet.getString(7).equals("0")){
                        resuelta.setText("No Resolta");
                    }
                }
            if (ayuda){
                String sql = "UPDATE incidencias SET resuelta = 1 WHERE id =" + newString;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            }
            }
            catch (Exception e) {
                String error = e.toString();
            }
            return null;
        }

        }}