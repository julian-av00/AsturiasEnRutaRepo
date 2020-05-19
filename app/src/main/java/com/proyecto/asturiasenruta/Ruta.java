package com.proyecto.asturiasenruta;

import java.util.HashMap;

public class Ruta {

    //Atributos de la clase Ruta (añadire mas)
    private String nombre;
    private String concejo;

    //Constructor de la clase Rutas
    public Ruta(String nombre, String concejo ){
        this.nombre = nombre;
        this.concejo = concejo;

    }

    //Constructor vacío
    public Ruta() {
    }


    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConcejo() {
        return concejo;
    }

    public void setConcejo(String concejo) {
        this.concejo = concejo;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "nombre='" + nombre + '\'' +
                ", concejo='" + concejo + '\'' +
                '}';
    }
}
