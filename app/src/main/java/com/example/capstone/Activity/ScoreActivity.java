package com.example.capstone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.capstone.Model.Score;
import com.example.capstone.Model.Student;
import com.example.capstone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mDatabase = FirebaseDatabase.getInstance("https://capstoneproject-4b898-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        mAuth=FirebaseAuth.getInstance();

        String userID= mAuth.getCurrentUser().getUid();


        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvIncorrectScore = findViewById(R.id.tvIncorrectScore);
        TextView tvScoreSummany = findViewById(R.id.tvScoreSummany);
        TextView tvScoreChapterNumber = findViewById(R.id.tvScoreChapterNumber);
        TextView tvScoreTestType = findViewById(R.id.tvScoreTestType);
        TextView tvScoreChapterTitle = findViewById(R.id.tvScoreChapterTitle);
        TextView tvAttemptNumber=findViewById(R.id.tvAttemptNumber);
        ImageView ivChapterImage1=findViewById(R.id.ivChapterImage1);

        Intent intent = getIntent();
        if (null != intent) {

            String score= String.valueOf(intent.getIntExtra("Score",0));
            String total= String.valueOf(intent.getIntExtra("Total Items",0));
            String incorrect= String.valueOf(intent.getIntExtra("Wrong answers",0));
            String LessonTitle= intent.getStringExtra("Lesson Name");
            String LessonType= intent.getStringExtra("Lesson Type");
            String ChapterNumber= intent.getStringExtra("Chapter Number");
            String imgURL= intent.getStringExtra("imgURL");
            String testName=intent.getStringExtra("testName");

            if(LessonType.equals("Pre-Assessment")){
                tvAttemptNumber.setVisibility(View.GONE);
            }

            Glide.with(getApplicationContext()).load(imgURL).into(ivChapterImage1);
            tvScoreChapterNumber.setText("Chapter "+ChapterNumber);
            tvScoreTestType.setText(LessonType);
            tvScoreChapterTitle.setText(LessonTitle);
                     tvScore.setText("Correct Answer: "+score);
            tvIncorrectScore.setText("Incorrect Answer: "+incorrect);
              tvScoreSummany.setText("Summary: "+score+"/"+total);



            mDatabase.child("Students").child(userID).child("Chapter_"+ChapterNumber).child(testName).setValue(score);
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
           this.onBackPressed();
           finish();
        });

    }

}