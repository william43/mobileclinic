package com.example.mp.clincdatabase;

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

import java.util.ArrayList;

/**
 * Created by waboy on 3/10/2018.
 */

public class Register extends AppCompatActivity{

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    private EditText regFname;
    private EditText regLName;
    private EditText regEmail;
    private EditText regPass;
    private String TAG = "MYACTIVITY";

    private Button btnReg;


    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_register);

        Intent intent = getIntent();

        users = new Users();
        regFname = (EditText) findViewById(R.id.regFname);
        regLName = (EditText) findViewById(R.id.regLname);
        regEmail = (EditText) findViewById(R.id.regEmail);
        regPass = (EditText) findViewById(R.id.regPass);
        Log.i("MYACTIVITY", "Went to the Register CLass");
        btnReg = (Button) findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!regFname.getText().toString().isEmpty() && !regEmail.getText().toString().isEmpty() &&
                        !regLName.getText().toString().isEmpty() && !regPass.getText().toString().isEmpty()){

                    getValues(regFname, regLName, regEmail, regPass);

                    ArrayList<Prescriptions> tempPres = new ArrayList<>();
                    tempPres.add(new Prescriptions("gamot 1", "aagain", "eaten", "none"));
                    tempPres.add(new Prescriptions("gamot 2", "aagain1", "eaten1", "none1"));

                    databaseReference.child("Users").child(users.getUsername()).setValue(users);
                    //userArray.add(users);


                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            final ArrayList<Users> userArrayTemp = new ArrayList<>();
                            for (DataSnapshot childSnapshot : dataSnapshot.child("Users").getChildren()) {
                                users = childSnapshot.getValue(Users.class);
                                userArrayTemp.add(users);
                            }
                            Log.i(TAG, userArrayTemp.size() + "THE CURRENT SIZE");
                            for(int j=0; j< userArrayTemp.size(); j++) {
                                Log.i(TAG, userArrayTemp.get(j).getUsername());
                                Log.i(TAG, userArrayTemp.get(j).getFname());
                            }
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(Register.this,
                            "Successfully registered", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(Register.this,
                            "Please fill in the blanks", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getValues(EditText Fname, EditText Lname, EditText username, EditText Pass){
        users.setFname(Fname.getText().toString());
        users.setLName(Lname.getText().toString());
        users.setPassword(Pass.getText().toString());
        users.setUsername(username.getText().toString());
    }
}
