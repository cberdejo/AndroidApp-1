//author: Christian Berdejo
package com.example.myapplication.database;

import static com.example.myapplication.database.DictContract.DictEntry.CATEGORY_FK_SJ;
import static com.example.myapplication.database.DictContract.DictEntry.CATEGORY_ID;
import static com.example.myapplication.database.DictContract.DictEntry.CATEGORY_NAME;
import static com.example.myapplication.database.DictContract.DictEntry.CATEGORY_TEXT;
import static com.example.myapplication.database.DictContract.DictEntry.TABLE_CATEGORY_SUBJECT;
import static com.example.myapplication.database.DictContract.DictEntry.TABLE_CATEGORY_TASK;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.Subject;
import com.example.myapplication.model.Task;

import java.util.ArrayList;
import java.util.List;

/*
Esta clase se usa como base de datos para usar en las activities, para tener las consultas ya definidas
y acceder mas rápido desde los activties

 */
public class Database {
    private DictDBHelper dbHelper;
    private SQLiteDatabase db;

    public Database(Context context, String name){
        dbHelper = new DictDBHelper(context,"mydictionary");
        db = dbHelper.getWritableDatabase();
    }

//CONSULTAS subject
/*--------------------------------------------------------------------------------------------------*/
    public int addSubject (Subject subject){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_NAME,subject.getName());
        long newRowId = db.insert(TABLE_CATEGORY_SUBJECT,null,contentValues);
        return newRowId==-1 ? 0:-1 ;
    }

    public ArrayList<Subject> getAllSubjects(){
        ArrayList<Subject> arrayListSub = new ArrayList<>();
        Cursor cursor = db.query(TABLE_CATEGORY_SUBJECT,null,null,null,null,null,null);

        //vease que no entra en el while si no hay ningún objeto de la clase
        while (cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);
            arrayListSub.add(new Subject(id,name));
        }
        return arrayListSub;

    }
    public void addSubjects(List<Subject>listSubjects) {
        for (Subject s : listSubjects){
            addSubject(s);
        }
    }

    public Subject findSubjectByName(String nameIN) {
        String where = CATEGORY_NAME+"=?";
        String whereArg[] = {nameIN};
        Subject subject = new Subject();

        Cursor cursor = db.query(TABLE_CATEGORY_SUBJECT,null,where,whereArg,null,null,null);
        //Name is UNIQUE Solo entra una vez al while si existe
        while (cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);
            subject = new Subject(id,name);
        }
        return subject;

    }

    public int deleteSubject (Subject subject){
        String where = CATEGORY_ID + "=?";
        String whereArg[] = {Integer.toString(subject.getId())};

        deleteTasks(getAllTaskFromSubject(subject)); //borramos las tareas de esa asignatura

        long newRowId = db.delete(TABLE_CATEGORY_SUBJECT,where,whereArg);
        return newRowId==-1 ? 0:-1 ;
    }

    public void deleteSubjects (List<Subject> subjectList){
        for (Subject s : subjectList){
            deleteSubject(s); //borramos la asignatura
        }
    }

//CONSULTAS TASK
/*--------------------------------------------------------------------------------------------------*/
    public int addTask (Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_TEXT,task.getText());
        contentValues.put(CATEGORY_FK_SJ,task.getSubject());
        long newRowId = db.insert(TABLE_CATEGORY_TASK,null,contentValues);
        return newRowId==-1 ? 0:-1 ;
    }
    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> arrayListTask = new ArrayList<>();

        Cursor cursor = db.query(TABLE_CATEGORY_TASK,null,null,null,null,null,null);

        //vease que no entra en el while si no hay ningún objeto de la clase
        while (cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);
            int subject = Integer.parseInt(cursor.getString(2));
            arrayListTask.add(new Task(id,name,subject));

        }
        return arrayListTask;
    }

    public ArrayList<Task> getAllTaskFromSubject(Subject subject){
        ArrayList<Task> arrayListTask = new ArrayList<>();

        String where = CATEGORY_FK_SJ+"=?";
        String [] whereArgs = { Integer.toString(subject.getId())};

        Cursor cursor = db.query(TABLE_CATEGORY_TASK,null,where,whereArgs,null,null,null);
        while (cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);

            arrayListTask.add(new Task(id,name,subject.getId()));
        }
        return arrayListTask;
    }

    public void addTasks(List<Task> listTasks){
        for (Task t : listTasks){
            addTask(t);
        }
    }

    public int deleteTask (Task task){
        String where = CATEGORY_ID + "=?";
        String whereArg[] = {Integer.toString(task.getId())};

        long newRowId = db.delete(TABLE_CATEGORY_TASK,where,whereArg);
        return newRowId==-1 ? 0:-1 ;
    }

    public void deleteTasks (List<Task> taskList){
        for (Task t : taskList){
            deleteTask(t);
        }
    }

//cierra bd
/*--------------------------------------------------------------------------------------------------*/
    public void close(){
        db.close();
    }







}
