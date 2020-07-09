package com.example.librosmysql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.librosmysql.Recursos.AdaptadorLibro;
import com.example.librosmysql.Recursos.Libro;
import com.example.librosmysql.Recursos.Recursos;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VisualizarActivity extends AppCompatActivity {
ListView listView;
AdaptadorLibro Adaptador;

    Libro libro;
   Button btnRegresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        listView=findViewById(R.id.idListVista);
        btnRegresa=findViewById(R.id.idRegresar);
        Recursos.InicializarRequets(this);
        MostrarDatos();

        btnRegresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new Intent(getApplicationContext(),MainActivity.class)));
                finish();
            }
        });
    }
    public void MostrarDatos() {
        String url = Recursos.DireccionHtt + "Consultar.php";
        Recursos.stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Recursos.ListaLibros.clear();
                System.out.println(response);

                int i=0;
               try {
                   JSONObject jsonObject=new JSONObject(response);
                   JSONArray jsonArray=jsonObject.getJSONArray("Libros");
                   while (i < jsonArray.length()) {//LLenamos nuestros Objetos
                       jsonObject = jsonArray.getJSONObject(i);
                       libro = new Libro();
                       libro.setCod_libro(jsonObject.optString("Cod_libro"));
                       libro.setISBN(jsonObject.optString("Isbm"));
                       libro.setTitulo(jsonObject.optString("Titulo"));
                       libro.setEditorial(jsonObject.optString("Editorial"));
                       libro.setEditorial(jsonObject.optString("Edicion"));
                       Recursos.ListaLibros.add(libro);
                       System.out.println((i + 1) + "<< Imprimiendo Datos: " + Recursos.ListaLibros.get(i).getTitulo()
                               + "\n>> ");

                       i++;
                   }
                   LLenarListView();
               }catch (JSONException e){
                   System.out.println("Error: "+ e.toString());
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Objeto = new HashMap<>();
                Objeto.put("clave", "todo");
                return Objeto;
            }
        };
        System.out.println("Datos enviados:-> " + Recursos.stringRequest.toString());
        Recursos.request.add(Recursos.stringRequest);
    }
    public void LLenarListView(){
        Adaptador=new AdaptadorLibro(VisualizarActivity.this,Recursos.ListaLibros);
        listView.setAdapter(Adaptador);
    }

    public void Mensaje(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }
}
