package com.zubi.madmathgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class result extends AppCompatActivity {
    public String  perstr = "Score %: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPREFS, Context.MODE_PRIVATE);
        String corrans = Integer.toString(sharedPreferences.getInt(questions.CORRQUES, 0));
        String totques = Integer.toString(sharedPreferences.getInt(MainActivity.FINQUES, 10));
        String plrname =  sharedPreferences.getString(MainActivity.NAME, "Player1");
        int perint;
        TextView per_txt =  findViewById(R.id.perc_txt);
        TextView corrres = findViewById(R.id.corr_txt);
        TextView wrongres = findViewById(R.id.wrong_txt);
        TextView name_txt = findViewById(R.id.name_txt);

        corrres.setText(corrans);
        wrongres.setText("" + (Integer.parseInt(totques) - Integer.parseInt(corrans)));
        perint = ((Integer.parseInt(corrans) * 100) / Integer.parseInt(totques));
        per_txt.setText(perstr + perint);
        name_txt.setText(plrname+" ");


    }

    public void again (View view){
        Intent intent = new Intent(getApplicationContext(), questions.class);
        startActivity(intent);
        SharedPreferences sharedPreferences =  getSharedPreferences(MainActivity.MyPREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(questions.CORRQUES, 0);
        editor.commit();
    }
    public void finish (View view){

        SharedPreferences sharedPreferences =  getSharedPreferences(MainActivity.MyPREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(questions.CORRQUES, 0);
        editor.commit();


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){

    }

}
