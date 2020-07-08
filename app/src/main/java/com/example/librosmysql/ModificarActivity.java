package com.example.librosmysql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class ModificarActivity extends AppCompatActivity {
    EditText[] Txt = new EditText[5];
    EditText txtBuscar;
    LinearLayout Caja;
    Button btnActualizar,btnEliminar,btnBuscar;
    RequestQueue request;//Ejecuta la peticion volley
    StringRequest stringRequest;//Captura el reusltado desde el
    JsonObjectRequest jsonStringRequest;
    Libro libro;
    String retornar(){
        Bundle Obj =this.getIntent().getExtras();
        return (Obj!=null)?Obj.getString("Accion"):"";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        txtBuscar=findViewById(R.id.txtBusqueda);
        Txt[0] = findViewById(R.id.cod_libro);
        Txt[1] = findViewById(R.id.ISBN);
        Txt[2] = findViewById(R.id.titulo);
        Txt[3] = findViewById(R.id.editorial);
        Txt[4] = findViewById(R.id.edicion);
        Caja=findViewById(R.id.SegmentoBuscar);

        Recursos.InicializarRequets(this);
        btnActualizar=findViewById(R.id.btnActualizar);
        btnEliminar=findViewById(R.id.btnEliminar);
        btnBuscar=findViewById(R.id.btnBuscar);
       Recursos.InicializarRequets(this);
       BotonesEvento();
       ControlarComponentes(retornar());

    }
    public void  ControlarComponentes(String llave){
        switch (llave){
            case "Agregar":
                Mensaje("Recibo Para Agregar");
                break;
            case "Modificar":
                Mensaje("Recibo Para Modificar Datos");
                break;
            case "Eliminar":
                Mensaje("Recibo Para Eliminar");
                break;
                default:
                    break;
        }
    }

    public  void BotonesEvento(){
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtBuscar.getText().toString().isEmpty()){
                    Mensaje("Campo Vacios Verifica");
                    Limpiar();
                }else{
                    //  CargarDatos(url+"Consultar.php?ctrl="+txtBuscar.getText().toString());
                }
            }

        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mensaje(""+VerificarCamposVacios());
                if(VerificarCamposVacios()){
                    Mensaje("Campso Vacios Verifica");
                }else{

                    Limpiar();
                    txtBuscar.setText("");

                }
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VerificarCamposVacios()){
                    Mensaje("Campos Vacios Verifica");
                }else{

                }
            }
        });
    }


    public void Mensaje(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
    }
    public void Limpiar(){
        for (int i = 0; i < Txt.length; i++) {
            Txt[i].setText("");
        }
    }
    boolean VerificarCamposVacios() {
        boolean bandera = false;
        for (int i = 0; i < Txt.length; i++) {
            if (Txt[i].getText().toString().trim().isEmpty()) {
                bandera = true;//si se valida correctamente los campos no estan vacios
            } else {
                Txt[i].requestFocus();
                bandera = false;//los campos estan vacios
                break;
            }
        }
        return bandera;
    }
}
