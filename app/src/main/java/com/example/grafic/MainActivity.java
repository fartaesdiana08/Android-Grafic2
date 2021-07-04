package com.example.grafic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private Button normalize;

    public static final int REQUEST_CODE = 200;

    public Intent intent;

    List<Film> list = new ArrayList<>();

    LinearLayout grafic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), AddFilmActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        populareLista(list);

        grafic = (LinearLayout)findViewById(R.id.grafic);
        grafic.addView(new GraficView(getApplicationContext(), list));

        normalize = findViewById(R.id.btn_Normalizare);
        normalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        intent=getIntent();

    }

    private void populareLista(List list){
        Film f1=new Film();
        f1.setPret(80);
        Film f2=new Film();
        f2.setPret(40);
        Film f3=new Film();
        f3.setPret(20);
        Film f4=new Film();
        f4.setPret(50);
        f4.setGen(Gen.ROMANTIC);
        Film f5=new Film();
        f5.setPret(70);
        f5.setGen(Gen.ROMANTIC);

        list.add(f1);list.add(f2); list.add(f3); list.add(f4); list.add(f5);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            Bundle b = data.getExtras();
            Film s = (Film)b.getSerializable("film");
            list.add(s);
            grafic.removeAllViews();
            grafic.addView(new GraficView(getApplicationContext(), list));
        }
    }
}