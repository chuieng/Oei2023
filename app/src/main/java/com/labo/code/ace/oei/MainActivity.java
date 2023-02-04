package com.labo.code.ace.oei;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.labo.code.ace.oei.vo.Constants;

public class MainActivity extends AppCompatActivity {

    FirebaseAnalytics analytics;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        analytics = FirebaseAnalytics.getInstance(this);
    }

    public void maleSelected(View view) {
        Intent i = new Intent(this, FamilyTree.class);
        i.putExtra(Constants.GENDER_SELECTION, Constants.MALE_CHAR);
        startActivity(i);
    }

    public void femaleSelected(View view) {
        Intent i = new Intent(this, FamilyTree.class);
        i.putExtra(Constants.GENDER_SELECTION, Constants.FEMALE_CHAR);
        startActivity(i);

    }

}
