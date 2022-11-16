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
    private int subject; //hace referencia al id

//constructores
/*--------------------------------------------------------------------------------------------------*/
    public Task(int id, String text, int subject) {
        this.id = id ;
        this.text = text;
        this.subject = subject;
    }
    // la base de datos genera el id, se evitan id repetidos, obligatorio la asignatura
    public Task(String text, int subject) {

        this.text = text;
        this.subject = subject;
    }

//getter + setter
/*--------------------------------------------------------------------------------------------------*/


    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

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
