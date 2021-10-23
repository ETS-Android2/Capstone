package com.example.capstone;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseMethods {

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userID;

    public String getEmail() {
        return email;
    }

    private String email;


    private Context mContext;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance("https://capstoneproject-4b898-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = mFirebaseDatabase.getReference();
        mContext = context;

        if(mAuth.getCurrentUser() != null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public void updateEmail(String email){

        myRef.child(mContext.getString(R.string.dbname_students))
                    .child(userID)
                    .child(mContext.getString(R.string.field_email))
                    .setValue(email);
    }
    public void getChapterProgress(View view){

        final ProgressBar progressBar=view.findViewById(R.id.progress);

        DatabaseReference ref= myRef.child(mContext.getString(R.string.dbname_students)).child("userID").child(mContext.getString(R.string.chapter_progress));

        ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.getValue(Student.class);
                if(student!=null){
                    int progress=student.getChapterProgress();
                    progressBar.setProgress(progress);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);
    }





}
