//author: Christian Berdejo
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

    private List<String> listSubject; // la lista de asignaturas que se muestran por pantalla
    //database
    private Database db;
    //views
    private EditText editText;
    private ListView listView;
    private  BaseAdapter adapterSubject; // el adaptador para hacer funcionar el listView



//INICIALIZACIÓN
/*--------------------------------------------------------------------------------------------------*/
    private void initLayout() {

        //layouts
        listView = (ListView) findViewById(R.id.listView);
        adapterSubject = new BaseAdapter(getApplicationContext(),listSubject);
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


    private void initialSubject() {
        listSubject= new LinkedList<>();

        for (Subject s : db.getAllSubjects()){
            listSubject.add(s.toString());
        }
    }



//ACCIONES
/*--------------------------------------------------------------------------------------------------*/
// add subject button
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
                listSubject.add(subject.toString()); // se añade a la lista
                adapterSubject.notifyDataSetChanged(); // se debe notificar al adapter de que se ha hecho el cambio
            }else{
                toast = Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT);
            }

            toast.show();

            refreh();
        }

    }
//delete subjects button
    public void deleteSubjects(View view){
        if (view.getId() == R.id.button2) {
            db.deleteSubjects(db.getAllSubjects());
            listSubject = new ArrayList<>();
            Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.successDeleted),Toast.LENGTH_SHORT);
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
    //refresca la interfaz
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
        initialSubject(); // Se inicializa la lista que se muestra en pantalla
        initLayout(); //inicializa layouts

    }

    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }







}