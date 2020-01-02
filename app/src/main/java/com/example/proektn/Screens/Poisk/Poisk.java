package com.example.proektn.Screens.Poisk;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.proektn.R;
import com.example.proektn.Screens.Acquaintance.Acquaintance;
import com.example.proektn.Screens.CreatingProfile.CreatingProfile;
import com.example.proektn.Screens.ListOfDating.ListOfDating;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Poisk extends AppCompatActivity {

    private RadioGroup polPoisk;
    private Spinner spinnerPoiskOt;
    private Spinner spinnerPoiskDo;
    private Spinner spinnerCelZnak;
    private String poiskPol;
    private Button buttonPoisk;
    long millis;
    long millis1;

    int myYear = 1990;
    int myMonth = 01;
    int myDay = 01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poisk);

        buttonPoisk = findViewById(R.id.buttonPoisk);


        spinnerPoiskOt = findViewById(R.id.spinnerPoiskVozrastOt);
        spinnerPoiskDo = findViewById(R.id.spinnerPoiskVozrastDo);

        spinnerCelZnak = findViewById(R.id.spinnerCelZnskom);

        polPoisk = findViewById(R.id.radioGrypPolPoisk);
        polPoisk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case -1: poiskPol=null;

                        break;
                    case R.id.polManPoisk: poiskPol="Мужской";

                        break;
                    case R.id.polGerlPoisk: poiskPol="Женский";
                        break;
                    default:
                        break;
                }


            }
        });

        buttonPoisk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Poisk.this, Acquaintance.class);

                startActivity(intent);
/*
                final Calendar cal = Calendar.getInstance();
                myYear = cal.get(Calendar.YEAR);
                myMonth = cal.get(Calendar.MONTH);
                myDay = cal.get(Calendar.DAY_OF_MONTH);

                SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");//задаю формат даты

                String dateInString = myDay+" "+myMonth+" "+(myYear-Long.parseLong(spinnerPoiskDo.getSelectedItem().toString()));// "29 11 2015";//создаю строку по заданному формату
                try {
                    Date date = formatter.parse(dateInString);//создаю дату через
                     millis = date.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }


                //Toast.makeText(Poisk.this,spinnerCelZnak.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Poisk.this, ListOfDating.class);
                intent.putExtra("poiskPol",poiskPol);
                intent.putExtra("celZnak",spinnerCelZnak.getSelectedItem().toString());
                intent.putExtra("poiskVoz",millis);
                intent.putExtra("poiskVoz1",millis1);
                startActivity(intent);
                /*
                int myYear = 1990;
                int myMonth = 02;
                int myDay = 23;

                SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");//задаю формат даты
                String dateInString = myDay+" "+myMonth+" "+myYear;// "29 11 2015";//создаю строку по заданному формату
                try {
                    Date date = formatter.parse(dateInString);//создаю дату через
                    long millis = date.getTime();
                    Date ndate = new Date(millis);
                    Toast.makeText(Poisk.this,ndate+"",Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
*/


            }
        });
    }
}
