package com.zubi.madmathgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String MyPREFS = "MyPrefs";
    public static final String FINQUES = "finques";
    public static final String NAME = "playerName";
    public static final String HINTCHK = "hintison";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFS, Context.MODE_PRIVATE);


        //int finques = sharedPreferences.getInt(FINQUES, 10);


    }



    public void startclick(View view) {
        SharedPreferences sharedPreferences =  getSharedPreferences(MyPREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText ed_name = findViewById(R.id.et_name);
        EditText ed_ques = findViewById(R.id.et_ques);
        CheckBox hnt =  findViewById(R.id.hintcheck);

        editor.putInt(questions.CORRQUES, 0);

        String name;
        int finq =10;

        if (hnt.isChecked()){
            sharedPreferences.getInt(HINTCHK, 0);
            editor.putInt(HINTCHK, 1);
            editor.commit();

        }
        if (!hnt.isChecked()){
            sharedPreferences.getInt(HINTCHK, 0);
            editor.putInt(HINTCHK, 0);
            editor.commit();
        }

        if ((""+ ed_name.getText()) == ""){
            editor.putString(NAME, "player 1");
        }

        if (("" + ed_name.getText()) != ""){
            name = "" + ed_name.getText();
            editor.putString(NAME, name);
            editor.commit();}
        if (("" + ed_ques.getText()) != ""){
            try{
                finq = Integer.parseInt("" + ed_ques.getText());
                editor.putInt(FINQUES, finq);
                editor.commit();}
            catch(NumberFormatException nfe)
            {
                finq = 0;
            }
        }
        if ((""+ ed_ques.getText()) == ""){
            finq = 0;}


        if (finq >= 10){
            Intent intent = new Intent(getApplicationContext(), questions.class);
            startActivity(intent);}

    }




}
