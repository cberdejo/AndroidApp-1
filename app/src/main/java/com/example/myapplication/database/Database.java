package com.example.myapplication.database;

import static com.example.myapplication.database.DictContract.DictEntry.CATEGORY_FK_SJ;
import static com.example.myapplication.database.DictContract.DictEntry.TABLE_CATEGORY_SUBJECT;
import static com.example.myapplication.database.DictContract.DictEntry.TABLE_CATEGORY_TASK;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.model.Subject;
import com.example.myapplication.model.Task;

import java.util.ArrayList;

//La base de datos para usar en las activitys
public class Database {
    private DictDBHelper dbHelper;
    private SQLiteDatabase db;

    public Database(Context context, String name){
        dbHelper = new DictDBHelper(context,"mydictionary");
        db = dbHelper.getWritableDatabase();
    }

    //Conultas Subject
    public int addSubject (Subject subject){

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",subject.getName());
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

    //Consultas Task
    public int addTask (Task task){

        ContentValues contentValues = new ContentValues();
        contentValues.put("text",task.getText());
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
            arrayListTask.add(new Task(id,name));
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
            arrayListTask.add(new Task(id,name));
        }
        return arrayListTask;
    }

    public void close(){
        db.close();
    }

    public void addSubjects(Subject[] listSubjects) {
        for (Subject s : listSubjects){
            addSubject(s);
        }
    }
}
