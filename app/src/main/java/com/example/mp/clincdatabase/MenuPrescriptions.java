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

public class MenuPrescriptions extends AppCompatActivity{

    private Button btnRecordPres;
    private Button btnViewPres;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_prescriptions);

        Intent intent1 = getIntent();
        final String user = intent1.getStringExtra("username");
        Log.i("MYACTIVITY", "Went to the Prescription menu CLass");
        btnRecordPres = (Button) findViewById(R.id.record_prescription);
        btnRecordPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnViewPres = (Button) findViewById(R.id.view_prescription);
        btnViewPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuPrescriptions.this, PrescriptionList.class);
                intent.putExtra("username", user);
                startActivity(intent);

            }
        });
    }
}
