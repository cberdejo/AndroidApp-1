package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.database.Database;
import com.example.myapplication.model.Subject;
import com.example.myapplication.Adapter.BaseAdapter;
import com.example.myapplication.model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//VARIABLES
// /*--------------------------------------------------------------------------------------------------*/

    private List<String> listSubject;
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
        BaseAdapter adapterSubject = new BaseAdapter(getApplicationContext(),listSubject);
        listView.setAdapter(adapterSubject);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),MainActivityTask.class);
                intent.putExtra("asignatura",listSubject.get(i));
                startActivity(intent);
            }
        });
        editText = (EditText)findViewById(R.id.editText);


    }
    private void initDb() {
        //database
        db = new Database(getApplicationContext(),"mydictionary");
    }

    // sirve para inicializar asignaturas génericas a la base de datos para observar mejor el funcionamiento del sistema
    private void initListElements() {
        Subject s1 = new Subject("Sistemas empotrados y android");
        Subject s2 = new Subject("Técnicas computacionales");
        Subject s3 = new Subject("Análisis y diseño de algoritmos");
        Subject s4 = new Subject("Programación concurrente");
        Subject s5 = new Subject("Teoría de automátas y Lenguajes formales");
        List<Subject> listSubjects = new ArrayList<>();
        listSubjects.add(s1);
        listSubjects.add(s2);
        listSubjects.add(s3);
        listSubjects.add(s4);
        listSubjects.add(s5);

        List<Task> taskList = createTaskForSubject(listSubjects); // se crean 5 tareas por asignatura

        db.addSubjects(listSubjects);
        db.addTasks(taskList);

    }

    private List<Task> createTaskForSubject(List <Subject> listSubjects) {
        List<Task> arrayListTask = new ArrayList<>();
        for (Subject s: listSubjects){
            Task t1 = new Task("Task1",s.getId());
            Task t2 = new Task("Task2",s.getId());
            Task t3 = new Task("Task3",s.getId());
            Task t4 = new Task("Task4",s.getId());
            Task t5 = new Task("Task5",s.getId());
            arrayListTask.add(t1);
            arrayListTask.add(t2);
            arrayListTask.add(t3);
            arrayListTask.add(t4);
            arrayListTask.add(t5);

        }
        return arrayListTask;
    }

    private void initialSubject() {
        listSubject= new LinkedList<>();

        for (Subject s : db.getAllSubjects()){
            listSubject.add(s.toString());
        }
    }



//ACCIONES
/*--------------------------------------------------------------------------------------------------*/
// crea una nueva asignatura
    public void addSubject(View view) {
        if (view.getId() == R.id.button) {
            Subject subject = new Subject(editText.getText().toString());
            int res = db.addSubject(subject); // se añade a la bbdd
            hideSoftKeyboard(view);

            // Toast
            String exito = getString(R.string.success);
            String error = getString(R.string.error) +" " + getString(R.string.check);
            Toast toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);
            if (res == -1){
                toast = Toast.makeText(getApplicationContext(),exito,Toast.LENGTH_SHORT);
                listSubject.add(subject.toString());
            }else{
                toast = Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT);
            }

            toast.show();

            refreh();
        }

    }

    public void deleteSubjects(View view){
        if (view.getId() == R.id.button2) {
            db.deleteSubjects(db.getAllSubjects());
            listSubject = new ArrayList<>();
            refreh();
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

    private void refreh (){
        finish();
        startActivity(getIntent());
    }
//CREACIÓN
/*--------------------------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDb(); //inicializa base de datos
        initListElements(); //inicializa unas cuantas asignaturas  y tareas para la bbd
        initialSubject(); // Se inicializa la lista que se muestra en pantalla
        initLayout(); //inicializa layouts

    }







}