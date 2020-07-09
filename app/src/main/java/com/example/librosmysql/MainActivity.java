package com.example.librosmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton Agregar, Eliminar, Modificar, Consultar;
    Bundle PasarDatos = new Bundle();//pasar datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Agregar = findViewById(R.id.Agregar);
        Eliminar = findViewById(R.id.Eliminar);
        Modificar = findViewById(R.id.Actualizar);
        Consultar = findViewById(R.id.Buscar);
        EventosBotones();

    }

    public void EventosBotones() {

        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasarDatos.putString("Accion","Agregar");
                startActivity(new Intent(MainActivity.this,ModificarActivity.class).putExtras(PasarDatos));

            }
        });
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PasarDatos.putString("Accion","Eliminar");
                startActivity(new Intent(MainActivity.this,ModificarActivity.class).putExtras(PasarDatos));
            }
        });
        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasarDatos.putString("Accion","Modificar");
                startActivity(new Intent(MainActivity.this,ModificarActivity.class).putExtras(PasarDatos));

            }
        });
        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,VisualizarActivity.class).putExtras(PasarDatos));

            }
        });
    }
}
