package com.labo.code.ace.oei.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


import com.labo.code.ace.oei.vo.AnswerVO;
import com.labo.code.ace.oei.vo.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chui Eng on 17/4/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "";

    private static String DB_NAME = "familyTree.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    private final String TABLE_NAME = "FAMILY_TREE";

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = myContext.getDatabasePath(DB_NAME).toString();
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getWritableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }

        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        //  this.getReadableDatabase();

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.


    //add your public methods for insert, get, delete and update data in database.

    public List<String> queryFirstDropdownList(String genderInput) {

        Boolean distinct = true;
        String[] columns = {"FIRST"};
        String selection = "MY_GENDER = ?";
        String[] selectionArgs = {genderInput};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor result = myDataBase.query(distinct, TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        List<String> dropdownList = new ArrayList<>();
        dropdownList.add(Constants.SELECT_ONE);
        if (result.moveToFirst()) {
            do {
                dropdownList.add(result.getString(0));
            } while (result.moveToNext());
        }

        return dropdownList;

    }

    public List<String> querySecondDropdownList(String genderInput, String firstSelection) {

        Boolean distinct = true;
        String[] columns = {"SECOND"};
        String selection = "MY_GENDER = ? AND FIRST = ? AND SECOND IS NOT NULL";
        String[] selectionArgs = {genderInput, firstSelection};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor result = myDataBase.query(distinct, TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        List<String> dropdownList = new ArrayList<>();
        dropdownList.add(Constants.SELECT_ONE);
        if (result.moveToFirst()) {
            do {
                dropdownList.add(result.getString(0));
            } while (result.moveToNext());
        }

        return dropdownList;

    }

    public List<String> queryThirdDropdownList(String genderInput, String firstSelection, String secondSelection) {

        Boolean distinct = true;
        String[] columns = {"THIRD"};
        String selection = "MY_GENDER = ? AND FIRST = ? AND SECOND = ? AND THIRD IS NOT NULL";
        String[] selectionArgs = {genderInput, firstSelection, secondSelection};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor result = myDataBase.query(distinct, TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        List<String> dropdownList = new ArrayList<>();
        dropdownList.add(Constants.SELECT_ONE);
        if (result.moveToFirst()) {
            do {
                dropdownList.add(result.getString(0));
            } while (result.moveToNext());
        }

        return dropdownList;

    }

    public List<String> queryFourthDropdownList(String genderInput, String firstSelection, String secondSelection, String thirdSelection) {

        Boolean distinct = true;
        String[] columns = {"FOURTH"};
        String selection = "MY_GENDER = ? AND FIRST = ? AND SECOND = ? AND THIRD = ? AND FOURTH IS NOT NULL";
        String[] selectionArgs = {genderInput, firstSelection, secondSelection, thirdSelection};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor result = myDataBase.query(distinct, TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        List<String> dropdownList = new ArrayList<>();
        dropdownList.add(Constants.SELECT_ONE);
        if (result.moveToFirst()) {
            do {
                dropdownList.add(result.getString(0));
            } while (result.moveToNext());
        }

        return dropdownList;

    }

    public AnswerVO queryAnswer(String genderInput, String firstSelection, String secondSelection, String thirdSelection, String fourthSelection) {
        String[] columns = {"ANSWER", "GENDER", "OLD_IND"};

        String firstSql = firstSelection.equalsIgnoreCase(Constants.SELECT_ONE) ? " FIRST IS NULL " : " FIRST = ? ";
        String secondSql = secondSelection.equalsIgnoreCase(Constants.SELECT_ONE) ? " SECOND IS NULL " : " SECOND = ? ";
        String thirdSql = thirdSelection.equalsIgnoreCase(Constants.SELECT_ONE) ? " THIRD IS NULL " : " THIRD = ? ";
        String fourthSql = fourthSelection.equalsIgnoreCase(Constants.SELECT_ONE) ? " FOURTH IS NULL " : " FOURTH = ? ";

        String selection = "MY_GENDER = ? AND " + firstSql + " AND " + secondSql + " AND " + thirdSql + " AND " + fourthSql;


        List<String> array = new ArrayList<>();
        array.add(genderInput);
        if (!firstSelection.equalsIgnoreCase(Constants.SELECT_ONE)) {
            array.add(firstSelection);
        }

        if (!secondSelection.equalsIgnoreCase(Constants.SELECT_ONE)) {
            array.add(secondSelection);
        }

        if (!thirdSelection.equalsIgnoreCase(Constants.SELECT_ONE)) {
            array.add(thirdSelection);
        }

        if (!fourthSelection.equalsIgnoreCase(Constants.SELECT_ONE)) {
            array.add(fourthSelection);
        }
        String[] selectionArgs = array.toArray(new String[array.size()]);

        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor result = myDataBase.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        AnswerVO answerVO = null;
        if (result.moveToFirst()) {
            do {
                String ans = result.getString(0);
                String gen = result.getString(1);
                String oldIndStr = result.getString(2);
                Boolean oldInd = oldIndStr == null ? null : Boolean.valueOf(oldIndStr);
                answerVO = new AnswerVO(ans, gen, oldInd);
            } while (result.moveToNext());
        }
        return answerVO;

    }
}