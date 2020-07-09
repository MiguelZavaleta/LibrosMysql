package com.example.librosmysql.Recursos;

public class Libro {
    String Cod_libro,ISBN,Titulo,Editorial,Edicion;


    public Libro(String cod_libro, String ISBN, String titulo, String editorial, String edicion) {
        Cod_libro = cod_libro;
        this.ISBN = ISBN;
        Titulo = titulo;
        Editorial = editorial;
        Edicion = edicion;
    }
    public Libro(){}

    public String getCod_libro() {
        return Cod_libro;
    }

    public void setCod_libro(String cod_libro) {
        Cod_libro = cod_libro;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String editorial) {
        Editorial = editorial;
    }

    public String getEdicion() {
        return Edicion;
    }

    public void setEdicion(String edicion) {
        Edicion = edicion;
    }
}
