//author: Christian Berdejo
package com.example.myapplication.model;

import android.view.View;

/*
Representa la tabla de Asignaturas de la bdd, creamos esta clase para facilitar su uso en la app
En este caso solo tenemos 2 atributo, pero lo hacemos así para parametrizar lo máximo posible la respuesta
Se podría ampliar el sistema añadiendo curso, semestre, profesor que la imparte,...
*/
public class Subject {

    private String name;
    private int id;


//constructores


    public Subject( int id, String name) {
        this.name = name;
        this.id = id;
    }

    // la base de datos genera el id, se evitan id repetidos
    public Subject(String name) {
        this.name = name;
    }


 //getter + setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
