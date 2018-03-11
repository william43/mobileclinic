package com.example.mp.clincdatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by waboy on 3/10/2018.
 */

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.ViewHolder> {
    @NonNull

    private ArrayList<Records> prescriptionList;
    private String user;
    private Context context;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    private DatabaseReference userDataReference;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
            private View mView;
            private TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mView = v;

            userDataReference = databaseReference.child("Users").child(user);
            userDataReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    prescriptionList.clear();
                    if(dataSnapshot.exists()){
                        for(DataSnapshot childRecord: dataSnapshot.child("records").getChildren()){
                            Records recordtemp = childRecord.getValue(Records.class);
                            prescriptionList.add(recordtemp);
                        }

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        public void setName(String prescName){
            mTextView = mView.findViewById(R.id.presName);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){
                        Records clickedDataItem = prescriptionList.get(pos);
                        Toast.makeText(mView.getContext(), "You clicked " + clickedDataItem.getPhysician() + "ME: "+user + "INDEX AT " + pos, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mView.getContext(), RecordView.class);
                        intent.putExtra("username", user);
                        intent.putExtra("position", pos);
                        context.startActivity(intent);
                    }
                }
            });

            mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    final int pos = getAdapterPosition();

                    // check if item still exists
                    if(pos != RecyclerView.NO_POSITION){


                        Records clickedDataItem = prescriptionList.get(pos);
                        Toast.makeText(mView.getContext(), "You longclicked " + clickedDataItem.getPhysician() + "ME: "+user, Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialogadd_prescription, null);
                        final EditText addMed = (EditText) dialogView.findViewById(R.id.addMed);
                        final EditText adFrequency = (EditText) dialogView.findViewById(R.id.adFrequency);
                        final EditText addCondition = (EditText) dialogView.findViewById(R.id.addCondition);
                        final EditText addNotes = (EditText) dialogView.findViewById(R.id.addNotes);

                        Button addPresLogin = (Button) dialogView.findViewById(R.id.addPresLogin);

                        mBuilder.setView(dialogView);

                        final AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        addPresLogin.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view){
                                prescriptionList.get(pos).getPrescriptions().add(new Prescriptions(addMed.getText().toString(), adFrequency.getText().toString(),addCondition.getText().toString(), addNotes.getText().toString()));

                                userDataReference.child("records").setValue(prescriptionList);
                                Toast.makeText(mView.getContext(), "GUMAGANA SIYA BITCHES", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });


                    }
                    return false;
                }
            });
            mTextView.setText(prescName);
        }

        public TextView getmTextView (){
            return mTextView;
        }
    }

    public PrescriptionAdapter(ArrayList<Records> prescriptionList, String user, Context context){
        this.prescriptionList = prescriptionList;
        this.user = user;
        this.context = context;
    }


    @Override
    public PrescriptionAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_list,parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.i("MYACTIVITY", "onClicked this bitch" + "ME: "+user);
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
               Log.i("MYACTIVITY", "LONGCLICKEDTHISBITCH  " + "ME: "+user);
                return true;
            }
        });
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PrescriptionAdapter.ViewHolder holder, int position) {
        holder.setName(prescriptionList.get(position).getPhysician());
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }
}
