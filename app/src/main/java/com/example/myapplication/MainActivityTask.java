//author: Christian Berdejo
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.BaseAdapter;
import com.example.myapplication.database.Database;
import com.example.myapplication.model.Subject;
import com.example.myapplication.model.Task;

import java.util.LinkedList;
import java.util.List;

public class MainActivityTask extends AppCompatActivity {

    private List<String> listTask;
    private Subject SubjectSelected; // Es la asignatura seleccionada
    //database
    private Database db;
    //views
    private EditText editText;
    private TextView selected;
    private ListView listView;
    private BaseAdapter adapterTask;


//INICIALIZACIÓN
/*--------------------------------------------------------------------------------------------------*/
    private void initLayout() {

        //layouts
        listView = (ListView) findViewById(R.id.listView);
         adapterTask = new BaseAdapter(getApplicationContext(),listTask);
        listView.setAdapter(adapterTask);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Lo dejo vacío por si se llega a ampliar la implementación
            }
        });
        editText = (EditText)findViewById(R.id.editText);
        selected = (TextView) findViewById(R.id.textView4);
        selected.setText(SubjectSelected.toString());



    }
    private void initDb() {
        //database
        db = new Database(getApplicationContext(),"mydictionary");
    }


    private void initialTasks(String SubjectName) {

        SubjectSelected = db.findSubjectByName(SubjectName); //name is unique
        listTask= new LinkedList<>();

        for (Task t : db.getAllTaskFromSubject(SubjectSelected)){
            listTask.add(t.toString());
        }
    }
//ACTION
/*--------------------------------------------------------------------------------------------------*/

    public void addTask(View view) {
        if (view.getId() == R.id.button) {
            Task task = new Task(editText.getText().toString(),SubjectSelected.getId());
            int res = db.addTask(task); // se añade a la bbdd
            hideSoftKeyboard(view); //esconde teclado

            // Toast
            String exito = getString(R.string.success);
            String error = getString(R.string.error);

            Toast toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT); //inicialización
            if (res == -1){
                toast = Toast.makeText(getApplicationContext(),exito,Toast.LENGTH_SHORT);
                listTask.add(task.toString());
                adapterTask.notifyDataSetChanged();
            }else{
                toast = Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT);
            }

            toast.show();
            refreh();
        }

    }

    public void deleteTasks(View view){
        if (view.getId() == R.id.button2) {
            db.deleteTasks(db.getAllTasks());
            Toast toast = Toast.makeText(getApplicationContext(),getString(R.string.successDeleted),Toast.LENGTH_SHORT);
            refreh();
        }
    }

    public void goBack(View view ){
        if (view.getId() == R.id.button3) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

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
        setContentView(R.layout.activity_main_task);
        initDb(); //inicializa base de datos

        String SubjectName = getIntent().getStringExtra("asignatura"); // nos pasan nombre de la aignatura de la primera actividad

        initialTasks(SubjectName); // inicializa la lista
        initLayout(); //inicializa layouts
    }


}