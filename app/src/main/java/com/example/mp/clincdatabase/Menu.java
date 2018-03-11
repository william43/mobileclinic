package com.example.mp.clincdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by waboy on 3/10/2018.
 */

public class Menu extends AppCompatActivity {


    private Button btnPrescriptions;
    private Button btnIntakeSched;
    private Button btnDoctors;
    private Button btnNotif;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Intent intent1 = getIntent();
        final String user = intent1.getStringExtra("username");
        Log.i("MYACTIVITY", "Went to the Menu CLass");
        btnPrescriptions = (Button) findViewById(R.id.btnPrescription);
        btnPrescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, MenuPrescriptions.class);
                intent.putExtra("username", user);
                startActivity(intent);

            }
        });
        btnDoctors = (Button) findViewById(R.id.btnDoctors);
        btnDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnIntakeSched = (Button) findViewById(R.id.btnIntakeSched);
        btnIntakeSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnNotif = (Button) findViewById(R.id.btnNotif);
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
