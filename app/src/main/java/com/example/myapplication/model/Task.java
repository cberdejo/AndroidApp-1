//author: Christian Berdejo
package com.example.myapplication.model;


/*
Representa la tabla de tareas de la bdd, creamos esta clase para facilitar su uso en la app
En este caso solo tenemos 2 atributo, pero lo hacemos así para parametrizar lo máximo posible la respuesta
Se podría ampliar el sistema añadiendo fecha de inicio y fin, comentarios, ...
*/
public class Task {

    private String text;
    private int id;

    //constructores
    public Task(int id, String text) {
        this.id = id ;
        this.text = text;
    }
    // la base de datos genera el id, se evitan id repetidos
    public Task(String text) {
        this.text = text;
    }
    //getter + setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
