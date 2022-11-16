//author: Christian Berdejo
package com.example.myapplication.database;

import android.provider.BaseColumns;
/*
El contrato, usamos esta clase para añadir contantes útiles que nos vendran bien
para las consultas y creación de tablas
 */
public class DictContract {
    private DictContract(){}
    public static abstract class DictEntry implements BaseColumns {
//nombre de tablas
/*--------------------------------------------------------------------------------------------------*/
    public static String TABLE_CATEGORY_SUBJECT = "subject";
    public static String TABLE_CATEGORY_TASK = "task";
//Atributos de cada clase
/*--------------------------------------------------------------------------------------------------*/
    public static String CATEGORY_ID = "id"; // se usa para ambas tablas.
    public static String CATEGORY_TEXT = "text";
    public static String CATEGORY_NAME = "name";
    public static String CATEGORY_FK_SJ = "subjectID";

// Creación de tablas
/*--------------------------------------------------------------------------------------------------*/
    public static final String SQL_CREATE_ENTRIES_SUBJECT =
            "CREATE TABLE " + TABLE_CATEGORY_SUBJECT + "(" +
                    CATEGORY_ID + " INTEGER PRIMARY KEY ," +
                    CATEGORY_NAME +  " TEXT UNIQUE);";

    public static final String SQL_CREATE_ENTRIES_TASK =
            "CREATE TABLE " + TABLE_CATEGORY_TASK + "(" +
                    CATEGORY_ID + " INTEGER PRIMARY KEY ," +
                    CATEGORY_TEXT +  " TEXT ," +
                    CATEGORY_FK_SJ + " integer, FOREIGN KEY('id') REFERENCES "+
                    TABLE_CATEGORY_SUBJECT +"('id')" +
                    ");";
// Eliminación de tablas
/*--------------------------------------------------------------------------------------------------*/
    public static final String SQL_DELETE_ENTRIES_SUBJECT =
            "DROP TABLE IF EXISTS " + TABLE_CATEGORY_SUBJECT;

    public static final String SQL_DELETE_ENTRIES_TASK =
            "DROP TABLE IF EXISTS " + TABLE_CATEGORY_TASK;
}

}
