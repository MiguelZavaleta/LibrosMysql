package com.example.librosmysql.Recursos;

import android.app.Activity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class Recursos {
   public static RequestQueue request;//Ejecuta la peticion volley
    public static StringRequest stringRequest;//Captura el reusltado desde el
    public static JsonObjectRequest jsonStringRequest;
    public static void InicializarRequets(Activity act) {
        request = Volley.newRequestQueue(act);
    }
public  static String DireccionHtt="http://localhost:8081/Android/Biblioteca/";
    public static List<Libro>ListaLibros;
}
