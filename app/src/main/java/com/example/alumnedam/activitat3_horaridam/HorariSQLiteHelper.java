package com.example.alumnedam.activitat3_horaridam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tamoor
 */

public class HorariSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateHorario = "CREATE TABLE Horario (id_horario INTEGER PRIMARY KEY, grup TEXT, codi_asignatura TEXT, hora_inici TEXT, hora_fi TEXT, dia TEXT, profe INTEGER, clase TEXT)";
    String sqlCreateAsignatura = "CREATE TABLE asignatura (id_asignatura INTEGER PRIMARY KEY, nombre TEXT, codi_asignatura INTEGER, id_profe INTEGER)";
    String sqlCreateProfe = "CREATE TABLE profe (id_profe INTEGER PRIMARY KEY, nombre TEXT)";

    public HorariSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateHorario);
        db.execSQL(sqlCreateProfe);
        db.execSQL(sqlCreateAsignatura);

        //Insertar asignaturas.
        db.execSQL("INSERT INTO asignatura VALUES(1, 'Interficies Graficas', 'M07', 1)");
        db.execSQL("INSERT INTO asignatura VALUES(2, 'Programacion', 'M03', 2)");
        db.execSQL("INSERT INTO asignatura VALUES(3, 'Android', 'M08', 3)");
        db.execSQL("INSERT INTO asignatura VALUES(4, 'Sistemes de gestió empresarial', 'M10', 5)");
        db.execSQL("INSERT INTO asignatura VALUES(5, 'M05/M02/M06', 'M05/M02/M06', 4)");
        db.execSQL("INSERT INTO asignatura VALUES(6, 'Programacio de serveis', 'M09', 4)");
        db.execSQL("INSERT INTO asignatura VALUES(7, 'Tutoria', 'Tut', 2)");

        //Insertar los profes
        db.execSQL("INSERT INTO profe VALUES(1, 'Jose Leo')");
        db.execSQL("INSERT INTO profe VALUES(2, 'Josefa')");
        db.execSQL("INSERT INTO profe VALUES(3, 'Luis')");
        db.execSQL("INSERT INTO profe VALUES(4, 'Jorge')");
        db.execSQL("INSERT INTO profe VALUES(5, 'Marta')");

        //Rellenar el horario.
        db.execSQL("INSERT INTO Horario VALUES (1, 'A2', '1', '15:00:00', '17:59:59', 'Lunes', 1, '201') ");
        db.execSQL("INSERT INTO Horario VALUES (2, 'A2', '7', '18:20:00', '19:19:59', 'Lunes', 2, '201') ");
        db.execSQL("INSERT INTO Horario VALUES (3, 'A2', '2', '19:20:00', '21:19:59', 'Lunes', 2, '201') ");

        db.execSQL("INSERT INTO Horario VALUES (4, 'A2', '3', '15:00:00', '16:59:59', 'Martes', '3', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (5, 'A2', '4', '17:00:00', '17:59:59', 'Martes', '5', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (6, 'A2', '4', '18:20:00', '19:19:59', 'Martes', '5', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (7, 'A2', '5', '19:20:00', '21:19:59', 'Martes', '4', '201') ");

        db.execSQL("INSERT INTO Horario VALUES (8, 'A2', '5', '16:00:00', '16:59:59', 'Miercoles', '4', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (9, 'A2', '3', '17:00:00', '17:59:59', 'Miercoles', '3', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (10, 'A2', '3', '18:20:00', '19:19:59', 'Miercoles', '3', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (11, 'A2', '6', '19:20:00', '20:19:59', 'Miercoles', '4', '201') ");

        db.execSQL("INSERT INTO Horario VALUES (12, 'A2', '2', '16:00:00', '17:59:59', 'Jueves', '2', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (13, 'A2', '5', '18:20:00', '21:20:00', 'Jueves', '4', '201') ");

        db.execSQL("INSERT INTO Horario VALUES (14, 'A2', '4', '15:00:00', '16:59:59', 'Viernes', '5', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (15, 'A2', '6', '17:00:00', '17:59:59', 'Viernes', '4', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (16, 'A2', '6', '18:20:00', '19:19:59', 'Viernes', '4', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (17, 'A2', '5', '19:20:00', '21:19:59', 'Viernes', '4', '201') ");

        //---------

        db.execSQL("INSERT INTO Horario VALUES (18, 'A1', '1', '15:00:00', '17:59:59', 'Lunes', 1, '201') ");

        db.execSQL("INSERT INTO Horario VALUES (19, 'A1', '2', '15:00:00', '16:59:59', 'Martes', '2', '208') ");
        db.execSQL("INSERT INTO Horario VALUES (20, 'A1', '4', '17:00:00', '17:59:59', 'Martes', '5', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (21, 'A1', '4', '18:20:00', '19:19:59', 'Martes', '5', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (22, 'A1', '5', '19:20:00', '21:19:59', 'Martes', '4', '201') ");

        db.execSQL("INSERT INTO Horario VALUES (23, 'A1', '5', '16:00:00', '16:59:59', 'Miercoles', '4', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (24, 'A1', '6', '17:00:00', '17:59:59', 'Miercoles', '4', '208') ");
        db.execSQL("INSERT INTO Horario VALUES (25, 'A1', '6', '18:20:00', '19:19:59', 'Miercoles', '4', '208') ");
        db.execSQL("INSERT INTO Horario VALUES (26, 'A1', '2', '19:20:00', '21:19:59', 'Miercoles', '2', '208') ");

        db.execSQL("INSERT INTO Horario VALUES (27, 'A1', '6', '15:00:00', '15:59:59', 'Jueves', '4', '208') ");
        db.execSQL("INSERT INTO Horario VALUES (28, 'A1', '3', '16:00:00', '17:59:59', 'Jueves', '3', '208') ");
        db.execSQL("INSERT INTO Horario VALUES (29, 'A1', '5', '18:20:00', '21:20:00', 'Jueves', '4', '201') ");

        db.execSQL("INSERT INTO Horario VALUES (30, 'A1', '4', '15:00:00', '16:59:59', 'Viernes', '5', '201') ");
        db.execSQL("INSERT INTO Horario VALUES (31, 'A1', '3', '17:00:00', '17:59:59', 'Viernes', '3', '208') ");
        db.execSQL("INSERT INTO Horario VALUES (32, 'A1', '3', '18:20:00', '19:19:59', 'Viernes', '3', '208') ");
        db.execSQL("INSERT INTO Horario VALUES (33, 'A1', '5', '19:20:00', '21:19:59', 'Viernes', '4', '201') ");

        db.execSQL("INSERT INTO Horario VALUES (34, 'Patio', null, '18:00:00', '18:19:59', 'Entre semana', null, null) ");

        //----


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        /**
        db.execSQL("DROP TABLE IF EXISTS Horario");
        db.execSQL("DROP TABLE IF EXISTS asignatura");
        db.execSQL("DROP TABLE IF EXISTS profe");

        db.execSQL(sqlCreateHorario);
        db.execSQL(sqlCreateProfe);
        db.execSQL(sqlCreateAsignatura);
         */
    }
}
