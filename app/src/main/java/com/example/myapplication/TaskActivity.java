package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
///INTERFAZ QUE SE GENERA CORRESPONDIENTE A LA ACTIVIDAD DE LA LISTA DE TAREAS
public class TaskActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
