package com.example.alumnedam.activitat3_horaridam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tamoor
 */

public class Horario extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    String intent;
    String font, fondo, string;
    //
    //Comentario introducio para testear el commit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario_activity);

        //Boton para ir a ajustes, para cambiar ajustes.
        Button btn = (Button) findViewById(R.id.btnSalir);
        btn.setOnClickListener(this);

        //recoje los valores de activity anterior.
        intent = getIntent().getStringExtra("grup");
        font = getIntent().getStringExtra("font");
        fondo = getIntent().getStringExtra("fondo");

        //Cambia el color de fondo.
        fondo();
        //Cambia el tipo de letras.
        font();
        //Metodo de Consulta el horario
        Timetable(intent);

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(50000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Timetable(intent);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }


    /**
     * Metodo para recoger el dia actual de sistema.
     * @return dia.
     */
    public String DiesSetmana() {
        String[] diesSetmana = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        Calendar cal = Calendar.getInstance();
        int diaDeSemana = cal.get(Calendar.DAY_OF_WEEK);
        String dia = diesSetmana[diaDeSemana - 1];
        return dia;
    }

    /**
     * Metodo para recoger el nombre de asignatura.
     * @param asignatura
     * @return
     */
    public String Asignatura(String asignatura) {
        String nombre = "";
        String[] args = new String[]{asignatura};
        Cursor c = db.rawQuery("SELECT nombre FROM asignatura WHERE ? like id_asignatura", args);
        if (c.moveToFirst()) {
            do {
                nombre = c.getString(0);
            } while (c.moveToNext());
        }
        return nombre;
    }

    /**
     * Metodo para recoger nombre de profesor.
     * @param profesor
     * @return
     */
    public String Profe(String profesor) {
        String nombre = "";
        String[] args = new String[]{profesor};
        Cursor c = db.rawQuery("SELECT nombre FROM profe WHERE ? LIKE id_profe", args);
        if (c.moveToFirst()) {
            do {
                nombre = c.getString(0);
            } while (c.moveToNext());
        }
        return nombre;
    }


    /**
     * Metodo para hacer consulta a partir del grupo selecionado, para poder mostrar su horario.
     * @param intent
     */
    public void Timetable(String intent) {
        String horaActual;
        String grup, asignatura, horaInici, horaFi, diaDeSemana, diaHorario, professor, clase;
        //Guardo el dia actual en la variable diaDeSemana
        diaDeSemana = DiesSetmana();
        //Se crea base de datos.
        HorariSQLiteHelper sql = new HorariSQLiteHelper(this, "dbHorario", null, 1);
        db = sql.getWritableDatabase();


        //Es para dar el formato, a la hora.
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        horaActual = format.format(cal.getTime());

        //Select con condicion de que no este vacio.

        /*
            Si pongo este consulta me muestra error " E/SQLiteLog: (1) no such column: Viernes" y no arraca la app
            --> poniendo otra consulta se no me muestra nada pero los de mas cosas funsionan.
            Cursor c = db.rawQuery("SELECT * FROM Horario WHERE '"+ horaActual +"' BETWEEN hora_inici AND hora_fi AND dia = "+ diaDeSemana, args);
             */
        if (db != null) {
            String[] args = new String[]{horaActual, this.intent, diaDeSemana};
            Cursor c = db.rawQuery("SELECT * FROM Horario WHERE ? BETWEEN hora_inici AND hora_fi AND grup = ? AND dia = ?", args);
            if (c.moveToFirst()) {
                do {

                    grup = c.getString(1);
                    asignatura = Asignatura(c.getString(2));
                    horaInici = c.getString(3);
                    horaFi = c.getString(4);
                    diaHorario = c.getString(5);
                    professor = Profe(c.getString(6));
                    clase = c.getString(7);
                } while (c.moveToNext());

                //Muestra datos por pantalla.
                TextView diaSetmana = (TextView) findViewById(R.id.tvDia);
                diaSetmana.setText(diaHorario);

                TextView asig = (TextView) findViewById(R.id.tvAsig);
                asig.setText(asignatura);

                TextView numClas = (TextView) findViewById(R.id.tvClass);
                numClas.setText(clase);

                TextView prof = (TextView) findViewById(R.id.tvProf);
                prof.setText(professor);

                TextView grupo = (TextView) findViewById(R.id.tvGrup);
                grupo.setText(grup);

                TextView horainicio = (TextView) findViewById(R.id.tvHoraInici);
                horainicio.setText(horaInici);

                TextView horaFin = (TextView) findViewById(R.id.tvHoraFi);
                horaFin.setText(horaFi);



            }
        }
    }


    /**
     * Haciendo el click en el boton, de ciera el intent y asi se abre otra vez el main activity
     * para poder configurar otra vez.
     * @param view
     */
    @Override
    public void onClick(View view) {
        finish();
    }


    /**
     * Metodo para cambiar el fondo segun el opcion selecionado.
     */
    public void fondo() {

        switch (fondo) {
            case "Blanco":
                findViewById(R.id.horario_activity).setBackgroundColor(Color.WHITE);
                break;
            case "Verde":
                findViewById(R.id.horario_activity).setBackgroundColor(Color.GREEN);
                break;
            case "Negro":
                findViewById(R.id.horario_activity).setBackgroundColor(Color.BLACK);
                break;
            case "Cyan":
                findViewById(R.id.horario_activity).setBackgroundColor(Color.CYAN);
                break;
        }

    }

    /**
     * Metodo para cambiar el tipo de letra segon el opcion selecionado anterior mente.
     */
    public void font() {
        TextView profe = (TextView) findViewById(R.id.tvProf);
        TextView grup = (TextView) findViewById(R.id.tvGrup);
        TextView asig = (TextView) findViewById(R.id.tvAsig);
        TextView clase = (TextView) findViewById(R.id.tvClass);
        TextView horaInici = (TextView) findViewById(R.id.tvHoraInici);
        TextView horaFi = (TextView) findViewById(R.id.tvHoraFi);
        TextView dia = (TextView) findViewById(R.id.tvDia);

switch (font) {
            case "Sant-Serif":
                profe.setTypeface(Typeface.SANS_SERIF);
                grup.setTypeface(Typeface.SANS_SERIF);
                asig.setTypeface(Typeface.SANS_SERIF);
                clase.setTypeface(Typeface.SANS_SERIF);
                horaInici.setTypeface(Typeface.SANS_SERIF);
                horaFi.setTypeface(Typeface.SANS_SERIF);
                dia.setTypeface(Typeface.SANS_SERIF);
                break;

            case "Serif":
                profe.setTypeface(Typeface.SERIF);
                grup.setTypeface(Typeface.SERIF);
                asig.setTypeface(Typeface.SERIF);
                clase.setTypeface(Typeface.SERIF);
                horaInici.setTypeface(Typeface.SERIF);
                horaFi.setTypeface(Typeface.SERIF);
                dia.setTypeface(Typeface.SERIF);
                break;

            case "Monospace":

                profe.setTypeface(Typeface.MONOSPACE);
                grup.setTypeface(Typeface.MONOSPACE);
                asig.setTypeface(Typeface.MONOSPACE);
                clase.setTypeface(Typeface.MONOSPACE);
                horaInici.setTypeface(Typeface.MONOSPACE);
                horaFi.setTypeface(Typeface.MONOSPACE);
                dia.setTypeface(Typeface.MONOSPACE);
                break;

            case "DEFAULT-BOLD":

                profe.setTypeface(Typeface.DEFAULT_BOLD);
                grup.setTypeface(Typeface.DEFAULT_BOLD);
                asig.setTypeface(Typeface.DEFAULT_BOLD);
                clase.setTypeface(Typeface.DEFAULT_BOLD);
                horaInici.setTypeface(Typeface.DEFAULT_BOLD);
                horaFi.setTypeface(Typeface.DEFAULT_BOLD);
                dia.setTypeface(Typeface.DEFAULT_BOLD);
                break;
        }

    }
}
