package com.example.librosmysql.Recursos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.librosmysql.R;

import java.util.List;

public class AdaptadorLibro extends BaseAdapter {
    Activity actividad;
    List<Libro>Libros;

    public AdaptadorLibro(Activity actividad, List<Libro> alumnos) {
        this.actividad = actividad;
        Libros = alumnos;
    }

    @Override
    public int getCount() {
        return Libros.size();
    }

    @Override
    public Object getItem(int position) {
        return Libros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Libro items=(Libro) getItem(position);
        convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_usuario, null);
        TextView Nc=convertView.findViewById(R.id.idNombre);
        TextView info=convertView.findViewById(R.id.idDes);

        Nc.setText(items.getCod_libro());
        info.setText(items.getISBN()+" "+items.getTitulo()+"\n"+items.getEditorial());

        return convertView;
    }
}
