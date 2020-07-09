package com.example.librosmysql;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.librosmysql.Recursos.Libro;
import com.example.librosmysql.Recursos.Recursos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ModificarActivity extends AppCompatActivity {
    EditText[] Txt = new EditText[5];
    EditText txtBuscar;
    LinearLayout Caja;
    Button btnActualizar, btnEliminar, btnBuscar, btnAgregar;
    RequestQueue request;//Ejecuta la peticion volley
    StringRequest stringRequest;//Captura el reusltado desde el
    JsonObjectRequest jsonStringRequest;
    Libro libro;

    String retornar() {
        Bundle Obj = this.getIntent().getExtras();
        return (Obj != null) ? Obj.getString("Accion") : "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        txtBuscar = findViewById(R.id.txtBusqueda);
        Txt[0] = findViewById(R.id.cod_libro);
        Txt[1] = findViewById(R.id.ISBN);
        Txt[2] = findViewById(R.id.titulo);
        Txt[3] = findViewById(R.id.editorial);
        Txt[4] = findViewById(R.id.edicion);
        Caja = findViewById(R.id.SegmentoBuscar);

        Recursos.InicializarRequets(this);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnAgregar = findViewById(R.id.btnGuardar);
        Recursos.InicializarRequets(this);
        BotonesEvento();
        ControlarComponentes(retornar());
    }
    public void ControlarComponentes(String llave) {
        switch (llave) {
            case "Agregar":
                Mensaje("Recibo Para Agregar");
                Caja.setVisibility(View.GONE);
                btnActualizar.setVisibility(View.GONE);
                btnEliminar.setVisibility(View.GONE);
                break;
            case "Modificar":
               // Mensaje("Recibo Para Modificar Datos");
                btnAgregar.setVisibility(View.GONE);
                Txt[0].setEnabled(false);
                break;
            case "Eliminar":
                btnAgregar.setVisibility(View.GONE);
                btnActualizar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public void BotonesEvento() {
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VerificarCamposVacios()) {
                    Mensaje("Campso Vacios Verifica");
                } else {
                    Mensaje("Agregar Dato");
                    InsertarDatosPost();

                }

            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mensaje(txtBuscar.getText().toString());
                if (txtBuscar.getText().toString().isEmpty()) {
                    Mensaje("Campo Vacios Verifica");
                    Limpiar();
                } else {
                  MostrarDatos();
                }
            }

        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mensaje(""+VerificarCamposVacios());
                if (VerificarCamposVacios()) {
                    Mensaje("Campso Vacios Verifica");
                } else {
                    ModificarPost();
                    //Limpiar();
                    //txtBuscar.setText("");

                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VerificarCamposVacios()) {
                    Mensaje("Campos Vacios Verifica");
                } else {
                    Eliminar();
                }
            }
        });
    }

    public void InsertarDatosPost() {
        String url = Recursos.DireccionHtt + "Insertar.php";
        Recursos.stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Mensaje(response);
                switch (response) {
                    case "YaExiste":
                        Mensaje("Ese Libro ya Existe Verifica El codigo del libro");
                        break;
                    case "Insertado":
                        Mensaje("Datos Insertados Correctamente");
                        Limpiar();
                        break;
                    case "ErrorInsertar":
                        Mensaje("Los Datos no se Insertaron");
                        break;
                    default:
                        //Mensaje("Los Datos no se Insertaron");
                        break;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Mensaje(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Objeto = new HashMap<>();
                Objeto.put("Cod_libro", Txt[0].getText().toString());
                Objeto.put("ISBN", Txt[1].getText().toString());
                Objeto.put("Titulo", Txt[2].getText().toString());
                Objeto.put("Editorial", Txt[3].getText().toString());
                Objeto.put("Edicion", Txt[4].getText().toString());
                return Objeto;
            }
        };
        System.out.println("Datos enviados:-> " + Recursos.stringRequest.toString());
        Recursos.request.add(Recursos.stringRequest);
    }

    public void ModificarPost() {
        String url = Recursos.DireccionHtt + "Modificar.php";
        Recursos.stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                switch (response) {
                    case "Actualizado":
                        Mensaje("Los Datos Fueron Actualizados Correctamente");
                        //Limpiar();
                        break;
                    case "NoActualizado":
                        Mensaje("Los Datos no se Actualizaron");
                        break;
                    default:
                        //Mensaje("Los Datos no se Insertaron");
                        break;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Mensaje(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Objeto = new HashMap<>();
                Objeto.put("Cod_libro", Txt[0].getText().toString());
                Objeto.put("Isbm", Txt[1].getText().toString());
                Objeto.put("Titulo", Txt[2].getText().toString());
                Objeto.put("Editorial", Txt[3].getText().toString());
                Objeto.put("Edicion", Txt[4].getText().toString());
                return Objeto;
            }
        };
        System.out.println("Datos enviados:-> " + Recursos.stringRequest.toString());
        Recursos.request.add(Recursos.stringRequest);
    }

    public void Eliminar() {
        String url = Recursos.DireccionHtt + "Eliminar.php";
        Recursos.stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                if (response.trim().equalsIgnoreCase("Eliminado")) {
                    Mensaje("Dato Eliminado Exitosamente");
                    Limpiar();
                } else {
                    Mensaje("El dato no existe");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Mensaje(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> Objeto = new HashMap<>();
                Objeto.put("Cod_libro", Txt[0].getText().toString());
                return Objeto;
            }
        };
        System.out.println("Datos enviados:-> " + Recursos.stringRequest.toString());
        Recursos.request.add(Recursos.stringRequest);
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
                        libro.setEdicion(jsonObject.optString("Edicion"));



                        i++;
                    }
                    if (libro.getCod_libro().equals("NE")) {
                        Mensaje("No existe en la Base de datos el Libro");
                    } else {
                        Txt[0].setText(libro.getCod_libro());
                        Txt[1].setText(libro.getISBN());
                        Txt[2].setText(libro.getTitulo());
                        Txt[3].setText(libro.getEditorial());
                        Txt[4].setText(libro.getEdicion());
                    }

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
                Objeto.put("clave", txtBuscar.getText().toString());
                return Objeto;
            }
        };
        System.out.println("Datos enviados:-> " + Recursos.stringRequest.toString());
        Recursos.request.add(Recursos.stringRequest);
    }

    public void Mensaje(String msj) {
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }

    public void Limpiar() {
        for (int i = 0; i < Txt.length; i++) {
            Txt[i].setText("");
        }
    }

    boolean VerificarCamposVacios() {
        boolean bandera = false;
        for (int i = 0; i < Txt.length; i++) {
            if (Txt[i].getText().toString().trim().isEmpty()) {
                bandera = Txt[i].getText().toString().trim().isEmpty();
                break;
            } else {
                Txt[i].requestFocus();
                bandera = Txt[i].getText().toString().trim().isEmpty();

            }
            System.out.println("Imprimir Validacion" + bandera + "\n");
        }
        return bandera;
    }
}
