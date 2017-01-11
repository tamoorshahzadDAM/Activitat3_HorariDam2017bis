package com.example.alumnedam.activitat3_horaridam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se comprueba si ya estan creadas las shared preferencias. en el caso de si se pasa al otro intent.
        SharedPreferences ajustes = getSharedPreferences("preferencias", 0);
        if(ajustes.getBoolean("guardar", false) != false){
            String fondo = ajustes.getString("fondo", "Blanco");
            String font = ajustes.getString("font", "Serif");
            String grup = ajustes.getString("grup", "A2");
            passarIntent(grup, fondo, font);
        }

        //Boton para ir a otro intent.
        Button btn = (Button) findViewById(R.id.btnNext);
        btn.setOnClickListener(this);



    }

    /**
     * Metodo para ver guardar shared preferences en el caso de modificacion o creacion.
     * @param grup
     * @param fondo
     * @param font
     */
    public void guardarPreferences(Spinner grup, Spinner fondo, Spinner font ){
        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fondo", fondo.getSelectedItem().toString());
        editor.putString("font", font.getSelectedItem().toString());
        editor.putString("grup", grup.getSelectedItem().toString());
        editor.putBoolean("guardar", true);
        editor.commit();
    }

    /**
     * Metodo para pasar a otro intent com las variables.
     * @param grup
     * @param fondo
     * @param font
     */
    public void passarIntent(String grup, String fondo, String font){
        intent = new Intent(this, Horario.class);
        intent.putExtra("grup", grup);
        intent.putExtra("fondo", fondo);
        intent.putExtra("font", font);
        startActivity(intent);
    }

    /**
     * Haciendo click al boton, es guarda las preferencias y pasa las variables al siguiente
     * intent y inicializa.
     * @param view
     */
    @Override
    public void onClick(View view) {
        intent = new Intent(this, Horario.class);
        Spinner grup = (Spinner) findViewById(R.id.spinCursos);
        Spinner fondo = (Spinner)findViewById(R.id.spinFondo);
        Spinner font = (Spinner)findViewById(R.id.spinFont);

            guardarPreferences(grup, fondo, font);
            intent.putExtra("grup", grup.getSelectedItem().toString());
            intent.putExtra("fondo", fondo.getSelectedItem().toString());
            intent.putExtra("font", font.getSelectedItem().toString());
            startActivity(intent);

    }
}