package com.example.mp.clincdatabase;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by waboy on 3/10/2018.
 */

public class MenuPrescriptions extends AppCompatActivity{

    private Button btnRecordPres;
    private Button btnViewPres;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    private DatabaseReference userDataReference;

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
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuPrescriptions.this);
                View dialogView = getLayoutInflater().inflate(R.layout.add_record, null);
                final EditText addPhysician = (EditText) dialogView.findViewById(R.id.recordPhysician);
                Button record = (Button) dialogView.findViewById(R.id.btnAddRecord);

                mBuilder.setView(dialogView);

                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                record.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MenuPrescriptions.this, "Added a record", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }
        });
        btnViewPres = (Button) findViewById(R.id.view_prescription);
        btnViewPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userDataReference = databaseReference.child("Users").child(user);
                userDataReference.orderByChild("records").equalTo(loginUser.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(""))
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                })

                Intent intent = new Intent(MenuPrescriptions.this, PrescriptionList.class);
                intent.putExtra("username", user);
                startActivity(intent);

            }
        });
    }
}
