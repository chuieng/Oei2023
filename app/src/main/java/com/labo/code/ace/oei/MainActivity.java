package com.labo.code.ace.oei;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.labo.code.ace.oei.vo.Constants;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
