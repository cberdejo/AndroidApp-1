package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapplication.database.Database;
import com.example.myapplication.model.Subject;
import com.example.myapplication.Adapter.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//VARIABLES
    private List<String> listSubjects;

    //database
    private Database db;
    //views
    private EditText editText;
    private ListView listView;



//INICIALIZACIÓN
/*--------------------------------------------------------------------------------------------------*/
    private void initLayout() {

        //layouts
        listView = (ListView) findViewById(R.id.listView);
        BaseAdapter adapterSubject = new BaseAdapter(getApplicationContext(),listSubjects);
        listView.setAdapter(adapterSubject);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        //editText = (EditText)findViewById(R.id.editText);


    }
    private void initDb() {
        //database
        db = new Database(getApplicationContext(),"mydictionary");
    }

    // sirve para inicializar asignaturas génericas a la base de datos para observar mejor el funcionamiento del sistema
    private void initListElemnts() {
        Subject s1 = new Subject("Sistemas empotrados y android");
        Subject s2 = new Subject("Técnicas computacionales");
        Subject s3 = new Subject("Análisis y diseño de algoritmos");
        Subject s4 = new Subject("Programación concurrente");
        Subject s5 = new Subject("Teoría de automátas y Lenguajes formales");
        Subject listSubjects[] = {s1,s2,s3,s4,s5};

        db.addSubjects(listSubjects);

    }

    //Esta función nos inicializa la lista de asignaturas que se muestran en la pantlla
    private void initialSubjects() {
        listSubjects= new LinkedList<>();

        for (Subject s : db.getAllSubjects()){
            listSubjects.add(s.toString());
        }
    }

//ACCIONES
/*--------------------------------------------------------------------------------------------------*/
// crea una nueva asignatura
    public void newSubject(View view) {
        switch (view.getId()) {
            // case R.id.button: search_word(); break;
        }
    }

//MÉTODOS AUXILIARES
/*--------------------------------------------------------------------------------------------------*/
    //esconde el teclado de la pantalla
    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }
//CREACIÓN
/*--------------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDb(); //inicializa base de datos
        initListElemnts(); //inicializa unas cuantas asignaturas para la bbd
        initialSubjects(); // inicializa la lista
        initLayout(); //inicializa layouts

    }


    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }

}