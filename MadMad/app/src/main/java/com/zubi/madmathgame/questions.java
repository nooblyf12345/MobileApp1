package com.zubi.madmathgame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class questions extends AppCompatActivity {
    public String [] oparr = {
            "+","-","x","÷"
    };
    public int randint1;
    public int randint2;
    public String operator;
    public String corrans;
    public int counter;
    public int atques;
    public int totques;
    public  int corrat;
    public int hintavail = 0;
    public int hintused = 0;
    public String [] oprem;
    public static final String CORRQUES= "CorrQues";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        RadioButton opt1 = findViewById(R.id.opt1);
        RadioButton opt2 = findViewById(R.id.opt2);
        RadioButton opt3 = findViewById(R.id.opt3);
        RadioButton opt4 = findViewById(R.id.opt4);
        opt1.setVisibility(View.VISIBLE);
        opt2.setVisibility(View.VISIBLE);
        opt3.setVisibility(View.VISIBLE);
        opt4.setVisibility(View.VISIBLE);
        RadioGroup optgroup = findViewById(R.id.radio_group);
        optgroup.clearCheck();


        atques = 1;
        counter  = 1;

        refresher();

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPREFS, Context.MODE_PRIVATE);
        totques = sharedPreferences.getInt(MainActivity.FINQUES,10);
        corrat = sharedPreferences.getInt(CORRQUES, 0);
        hintavail = sharedPreferences.getInt(MainActivity.HINTCHK, 0);

        hintused = 0;

        TextView yay = findViewById(R.id.qnum);
        yay.setText(atques + "/" + totques);
    }

    public void nxtclk (View view){

        RadioGroup optgroup =  findViewById(R.id.radio_group);
        int chkbtn = -1;
        chkbtn = optgroup.getCheckedRadioButtonId();

        if (chkbtn !=-1) {
            RadioButton radchk = findViewById(chkbtn);
            if (corrans == radchk.getText()){
                atques +=1;


                if (atques > totques){
                    corrat+=1;
                    SharedPreferences sharedPreferences =  getSharedPreferences(MainActivity.MyPREFS,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(questions.CORRQUES, corrat);
                    editor.commit();

                    RadioButton opt1 = findViewById(R.id.opt1);
                    RadioButton opt2 = findViewById(R.id.opt2);
                    RadioButton opt3 = findViewById(R.id.opt3);
                    RadioButton opt4 = findViewById(R.id.opt4);
                    opt1.setVisibility(View.GONE);
                    opt2.setVisibility(View.GONE);
                    opt3.setVisibility(View.GONE);
                    opt4.setVisibility(View.GONE);

                    hintused= 0;


                    Intent intent = new Intent(getApplicationContext(), result.class);
                    startActivity(intent);
                }
                else{
                    TextView yay = findViewById(R.id.qnum);
                    yay.setText(atques + "/" + totques);
                    corrat+=1;
                    RadioButton opt1 = findViewById(R.id.opt1);
                    RadioButton opt2 = findViewById(R.id.opt2);
                    RadioButton opt3 = findViewById(R.id.opt3);
                    RadioButton opt4 = findViewById(R.id.opt4);
                    opt1.setVisibility(View.VISIBLE);
                    opt2.setVisibility(View.VISIBLE);
                    opt3.setVisibility(View.VISIBLE);
                    opt4.setVisibility(View.VISIBLE);
                    hintused= 0;
                    optgroup.clearCheck();
                    refresher();
                }
            }
            else if ((hintavail==1) && (hintused==0)){
                hintused = 1;
                optgroup.clearCheck();
                RadioButton opt1 = findViewById(R.id.opt1);
                RadioButton opt2 = findViewById(R.id.opt2);
                RadioButton opt3 = findViewById(R.id.opt3);
                RadioButton opt4 = findViewById(R.id.opt4);
                if (opt1.getText() != corrans && opt2.getText() != corrans){
                    opt1.setVisibility(View.GONE);
                    opt2.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Option 1 and 2 removed", Toast.LENGTH_SHORT).show();
                }
                else if (opt2.getText() != corrans && opt3.getText() != corrans){
                    opt2.setVisibility(View.GONE);
                    opt3.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Option 2 and 3 removed", Toast.LENGTH_SHORT).show();
                }
                else if (opt3.getText() != corrans && opt4.getText() != corrans){
                    opt3.setVisibility(View.GONE);
                    opt4.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Option 3 and 4 removed", Toast.LENGTH_SHORT).show();
                }
                else if (opt1.getText() != corrans && opt4.getText() != corrans){
                    opt1.setVisibility(View.GONE);
                    opt4.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Option 1 and 4 removed", Toast.LENGTH_SHORT).show();
                }
                else if (opt1.getText() != corrans && opt3.getText() != corrans){
                    opt1.setVisibility(View.GONE);
                    opt3.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Option 1 and 3 removed", Toast.LENGTH_SHORT).show();
                }
                else if (opt2.getText() != corrans && opt4.getText() != corrans){
                    opt2.setVisibility(View.GONE);
                    opt4.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Option 2 and  removed", Toast.LENGTH_SHORT).show();
                }


            }









            else if (hintavail == 0 || hintused == 1)
                {
                atques +=1;
                TextView yay = findViewById(R.id.qnum);

                if (atques > totques){
                    SharedPreferences sharedPreferences =  getSharedPreferences(MainActivity.MyPREFS,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(questions.CORRQUES, corrat);
                    editor.commit();

                    RadioButton opt1 = findViewById(R.id.opt1);
                    RadioButton opt2 = findViewById(R.id.opt2);
                    RadioButton opt3 = findViewById(R.id.opt3);
                    RadioButton opt4 = findViewById(R.id.opt4);
                    opt1.setVisibility(View.GONE);
                    opt2.setVisibility(View.GONE);
                    opt3.setVisibility(View.GONE);
                    opt4.setVisibility(View.GONE);

                    hintused= 0;

                    Intent intent = new Intent(getApplicationContext(), result.class);
                    startActivity(intent);
                    }
                else{
                    yay.setText(atques + "/" + totques);
                    hintused= 0;
                    RadioButton opt1 = findViewById(R.id.opt1);
                    RadioButton opt2 = findViewById(R.id.opt2);
                    RadioButton opt3 = findViewById(R.id.opt3);
                    RadioButton opt4 = findViewById(R.id.opt4);
                    opt1.setVisibility(View.VISIBLE);
                    opt2.setVisibility(View.VISIBLE);
                    opt3.setVisibility(View.VISIBLE);
                    opt4.setVisibility(View.VISIBLE);
                    optgroup.clearCheck();
                    refresher();
                }
                }

            }

    }





    public void refresher(){
        int i1 = new Random().nextInt(100);
        int i2 = new Random().nextInt(100);
        int o1 = new Random().nextInt(3);
        TextView text_ques =  findViewById(R.id.text_ques);
        RadioButton opt1 = findViewById(R.id.opt1);
        RadioButton opt2 = findViewById(R.id.opt2);
        RadioButton opt3 = findViewById(R.id.opt3);
        RadioButton opt4 = findViewById(R.id.opt4);




        randint1 = i1;
        randint2 = i2;
        operator = oparr[o1];
        if (operator =="+"){
            corrans = Integer.toString(randint1 + randint2);
        }
        if (operator =="-"){
            corrans = Integer.toString(randint1 - randint2);
        }
        if (operator =="x"){
            corrans = Integer.toString(randint1 * randint2);
        }
        if (operator =="÷"){
            corrans = Integer.toString(randint1 / randint2);
        }
        text_ques.setText(randint1 + " " + operator + " " + randint2);
        String [] option1 = {
                Integer.toString(Integer.parseInt(corrans) - new Random().nextInt(50)),Integer.toString(Integer.parseInt(corrans) + new Random().nextInt(50)),Integer.toString(Integer.parseInt(corrans) + new Random().nextInt(50)), corrans
        };
        List<String> strList = Arrays.asList(option1);
        Collections.shuffle(strList);
        option1 = strList.toArray(new String[strList.size()]);
        opt1.setText(option1[0]);
        opt2.setText(option1[1]);
        opt3.setText(option1[2]);
        opt4.setText(option1[3]);
        oprem = option1;

    }

}
