package com.labo.code.ace.oei;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.labo.code.ace.oei.db.DatabaseHelper;
import com.labo.code.ace.oei.vo.AnswerVO;
import com.labo.code.ace.oei.vo.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chui Eng on 23/1/2016.
 */

public class FamilyTree extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1, s2, s3, s4; //relationship layers
    String gender;
    TextView myText, firstText, secondText, thirdText;
    ImageView who;
    DatabaseHelper myDbHelper;

//    private AdView mAdView;
//    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_tree);

        Intent myIntent = getIntent(); // gets the previously created intent
        gender = myIntent.getStringExtra(Constants.GENDER_SELECTION);

        //init DB
        myDbHelper = new DatabaseHelper(FamilyTree.this);
        try {

            myDbHelper.createDataBase();
            myDbHelper.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Ans pic
        who = (ImageView) findViewById(R.id.who);

        //init display text
        initText();

        //init 1st dropdown
        initSpinner1();

        s1 = (Spinner) findViewById(R.id.spinner1);
        s2 = (Spinner) findViewById(R.id.spinner2);
        s3 = (Spinner) findViewById(R.id.spinner3);
        s4 = (Spinner) findViewById(R.id.spinner4);
        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        s3.setOnItemSelectedListener(this);


//        //Advertisement
//        mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(Constants.AD_UNIT_ID);
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spinner1) {
            generateSpinner2Chinese();
        } else if (spinner.getId() == R.id.spinner2) {
            generateSpinner3Chinese();
        } else if (spinner.getId() == R.id.spinner3) {
            generateSpinner4Chinese();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    public void initText() {
        myText = (TextView) findViewById(R.id.myText);
        firstText = (TextView) findViewById(R.id.firstText);
        secondText = (TextView) findViewById(R.id.secondText);
        thirdText = (TextView) findViewById(R.id.thirdText);
        myText.setText(Constants.MINE);
        firstText.setText(Constants.MINES);
        secondText.setText(Constants.MINES);
        thirdText.setText(Constants.MINES);
    }

    public void initSpinner1() {
        s1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = myDbHelper.queryFirstDropdownList(gender);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(dataAdapter);
    }

    public void generateSpinner2Chinese() {
        String sp1 = String.valueOf(s1.getSelectedItem());

        //reset spinner
        List<String> reset = new ArrayList<String>();
        reset.add(Constants.SELECT_ONE);

        ArrayAdapter<String> resetDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, reset);
        resetDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resetDataAdapter.notifyDataSetChanged();
        s2.setAdapter(resetDataAdapter);
        s3.setAdapter(resetDataAdapter);

        //load 2nd dropdown based on first
        List<String> list = myDbHelper.querySecondDropdownList(gender, sp1);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        s2.setAdapter(dataAdapter);
    }

    public void generateSpinner3Chinese() {
        String sp1 = String.valueOf(s1.getSelectedItem());
        //reset spinner 3
        List<String> reset = new ArrayList<String>();
        reset.add(Constants.SELECT_ONE);

        ArrayAdapter<String> resetDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, reset);
        resetDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resetDataAdapter.notifyDataSetChanged();
        s3.setAdapter(resetDataAdapter);

        String sp2 = String.valueOf(s2.getSelectedItem());


        //load 3rd dropdown based on first
        List<String> list = myDbHelper.queryThirdDropdownList(gender, sp1, sp2);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        s3.setAdapter(dataAdapter);
    }

    public void generateSpinner4Chinese() {
        String sp1 = String.valueOf(s1.getSelectedItem());
        //reset spinner 4
        List<String> reset = new ArrayList<String>();
        reset.add(Constants.SELECT_ONE);

        ArrayAdapter<String> resetDataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, reset);
        resetDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resetDataAdapter.notifyDataSetChanged();
        s4.setAdapter(resetDataAdapter);

        String sp2 = String.valueOf(s2.getSelectedItem());
        String sp3 = String.valueOf(s3.getSelectedItem());

        //load 4th dropdown based on first
        List<String> list = myDbHelper.queryFourthDropdownList(gender, sp1, sp2, sp3);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        s4.setAdapter(dataAdapter);
    }

    public void answer(View view) {
        TextView answer = (TextView) findViewById(R.id.ansTextView);
        String sp1 = String.valueOf(s1.getSelectedItem());
        String sp2 = String.valueOf(s2.getSelectedItem());
        String sp3 = String.valueOf(s3.getSelectedItem());
        String sp4 = String.valueOf(s4.getSelectedItem());

        AnswerVO queryAns = myDbHelper.queryAnswer(gender, sp1, sp2, sp3, sp4);
        answer.setText(queryAns.getAnswer());

        //load image as ANSWER. Random pic picked
        TypedArray imgs = null;
        if (Constants.FEMALE_CHAR.equalsIgnoreCase(queryAns.getGender())) {
            if (queryAns.getOldInd() == null) {
                imgs = getResources().obtainTypedArray(R.array.lady);
            } else {
                if (true == queryAns.getOldInd()) {
                    imgs = getResources().obtainTypedArray(R.array.oldLady);
                } else {
                    imgs = getResources().obtainTypedArray(R.array.youngLady);
                }
            }
        } else {
            if (queryAns.getOldInd() == null) {
                imgs = getResources().obtainTypedArray(R.array.man);
            } else {
                if (true == queryAns.getOldInd()) {
                    imgs = getResources().obtainTypedArray(R.array.oldMan);
                } else {
                    imgs = getResources().obtainTypedArray(R.array.youngMan);
                }
            }

        }


        final Random rand = new Random();
        final int rndInt = rand.nextInt(imgs.length());
        final int resID = imgs.getResourceId(rndInt, 0);

        who.setBackgroundResource(resID);


    }
}
