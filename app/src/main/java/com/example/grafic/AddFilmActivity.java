package com.example.grafic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddFilmActivity extends AppCompatActivity {

    private Intent intent;
    public static final String ADD_FILM = "adaugare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        final EditText etTitlu = findViewById(R.id.etTitlu);
        final EditText etDescriere = findViewById(R.id.etDescriere);
        final EditText etDate = findViewById(R.id.etData);
        final EditText etPret = findViewById(R.id.etPret);

        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        final  String DATE_FORMAT = "dd/MM/yyyy";
        Calendar calendar=Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddFilmActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        intent = getIntent();

        Button btn1 = findViewById(R.id.btnSave);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etTitlu.getText()==null || etTitlu.getText().toString().isEmpty() || etTitlu.getText().toString().trim().isEmpty())
                    etTitlu.setError("Introduceti titlul!");
                else
                if (etDescriere.getText()==null || etDescriere.getText().toString().isEmpty() || etDescriere.getText().toString().trim().isEmpty())
                    Toast.makeText(getApplicationContext(), "Introduceti descriere!", Toast.LENGTH_LONG).show();
                else
                if (etDate.getText().toString().isEmpty())
                    etDate.setError("Introduceti data!");
                else
                if (etPret.getText().toString().isEmpty())
                    etPret.setError("Introduceti pretul!");

                else
                {
                    //creare obiect
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);

                    try {
                        sdf.parse(etDate.getText().toString());

                        String titlu = etTitlu.getText().toString();
                        String descriere = etDescriere.getText().toString();
                        Date data = new Date(etDate.getText().toString());
                        int pret = Integer.parseInt(etPret.getText().toString());

                        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        Gen gen = Gen.valueOf(radioButton.getText().toString().toUpperCase());

                        Film film = new Film(titlu, pret,gen, data, descriere);

                        //Toast.makeText(getApplicationContext(), film.toString(), Toast.LENGTH_LONG).show();

                        Bundle b = new Bundle();
                        b.putSerializable("film", film);
                        intent.putExtras(b);
                        setResult(Activity.RESULT_OK, intent);
                        finish();

                    } catch (ParseException e) {
                        e.printStackTrace();

                        Toast.makeText(getApplicationContext(), "Data invalida!", Toast.LENGTH_LONG).show();
                    }


                }
            }
        });

    }
}