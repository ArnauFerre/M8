package com.example.tasca12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Button button = (Button) findViewById(R.id.button2);
        Button button2 = (Button)  findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirAltaInciar();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                abrirIncidenciasSinResolver();
            }
        });
        ;

    }

    public void abrirIncidenciasSinResolver(){
        Intent intent = new Intent(this, mostrarincidenciano.class);
        startActivity(intent);
    }

    public void abrirAltaInciar(){
        Intent intent2 = new Intent(this, AltaIniciar.class);
        startActivity(intent2);
    }

    public void onBackPressed(){
        AlertDialog.Builder myBuild = new AlertDialog.Builder(this);
        myBuild.setMessage("Vols sortir de l'aplicació");
        myBuild.setTitle("Sortir");
        myBuild.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int wich) {
                finish();
            }
        });

        myBuild.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int wich) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = myBuild.create();
        dialog.show();

    }


}