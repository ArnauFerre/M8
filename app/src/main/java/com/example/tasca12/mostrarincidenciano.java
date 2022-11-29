package com.example.tasca12;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Result;

public class mostrarincidenciano extends AppCompatActivity {
    int contador = 0;
    int[] listviewImage = new int[]{
            R.drawable.mezi, R.drawable.speed, R.drawable.cristianoronaldo,
    };
    List<HashMap<String, String>>aList;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarincidencias);
        new Task().execute();
    }


    class Task extends AsyncTask<Void, Void, Void> {
        String records = "", error = "";
        Intent inmuk = new Intent(mostrarincidenciano.this, incidencia1.class);

        protected Void doInBackground(Void... voids) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.1:3306/database", "administrador", "admin");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM incidencias");

                aList = new ArrayList<HashMap<String, String>>();

                while(resultSet.next()){
                    if (resultSet.getString(7).equals("0")){
                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("listview_title", resultSet.getString("afectado"));
                        hm.put("listview_image", Integer.toString(listviewImage[contador]));
                        aList.add(hm);
                        String strName = resultSet.getString("afectado");
                        inmuk.putExtra("strName", strName);
                        contador = contador + 1;
                }}
                contador = 0;

            }
            catch (Exception e) {
                String error = e.toString();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            String[] from = {"listview_image","listview_title"};
            int[] to = {R.id.listview_image,R.id.listview_item_title};
            SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_view_incidencias, from, to);
            ListView androidListView = (ListView) findViewById(R.id.list_view);
            androidListView.setAdapter(simpleAdapter);

            androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    inmuk.putExtra("i", i);
                    if(i == 0){
                        startActivity(inmuk);
                    }
                    else if(i == 1){
                        startActivity(inmuk);
                    }
                    else if(i == 2){
                        startActivity(inmuk);
                    }
            }});
        }

    }
}