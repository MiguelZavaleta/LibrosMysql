package com.example.librosmysql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.librosmysql.Recursos.AdaptadorLibro;
import com.example.librosmysql.Recursos.Libro;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VisualizarActivity extends AppCompatActivity {
ListView listView;
AdaptadorLibro Adaptador;
    RequestQueue request;//Ejecuta la peticion volley
    StringRequest stringRequest;//Captura el reusltado desde el
    JsonObjectRequest jsonStringRequest;
   // Libro Alumno;
   Button btnRegresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);
        listView=findViewById(R.id.idListVista);
        btnRegresa=findViewById(R.id.idRegresar);
        request = Volley.newRequestQueue(this);
        WebConsultarDatos("todo");

        btnRegresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void WebConsultarDatos(String NumeroControl){
       /* String url = utilidades.DireccionHttp + "Consultar.php?" +
                "ctrl=" + NumeroControl;
        jsonStringRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //System.out.println("Response: " + response.toString());
                int i = 0;
                try {
                    //Declaramos el JSON que Recibimos de nuestra clase PHP
                    JSONArray jsonArray = response.getJSONArray("Alumnos");
                    utilidades.ListaAlumnos.clear();
                    while (i < jsonArray.length()) {//LLenamos nuestros Objetos
                        JSONObject jsonObject = null;
                        jsonObject = jsonArray.getJSONObject(i);
                        Alumno = new Alumno();
                        Alumno.setNumeroControl(jsonObject.optString("NumeroCtrl"));
                        Alumno.setNombre(jsonObject.optString(Alumno.LLaves[1]));
                        Alumno.setApellidos((jsonObject.optString(Alumno.LLaves[2])));
                        Alumno.setCarrera(jsonObject.optString(Alumno.LLaves[3]));
                        Alumno.setCorreo(jsonObject.optString(Alumno.LLaves[4]));
                        Alumno.setDireccion(jsonObject.optString(Alumno.LLaves[5]));
                        Alumno.setTelefono(jsonObject.optString(Alumno.LLaves[6]));
                        utilidades.ListaAlumnos.add(Alumno);
                        System.out.println((i + 1) + "<< Imprimiendo Datos: " + utilidades.ListaAlumnos.get(i).getNumeroControl()
                                + "\n>> ");
                        i++;
                    }
                    LLenarListView();
                } catch (JSONException e) {
                    utilidades.ListaAlumnos.clear();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Mensaje("Error en el volley");
            }
        });
        System.out.println("URL enviada: " +jsonStringRequest);
        request.add(jsonStringRequest);
    }
    public void LLenarListView(){
        Adapter=new Adaptador(VisualizarActivity.this,utilidades.ListaAlumnos);
        listView.setAdapter(Adapter);
    }
    public void Mensaje(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    */}
}
