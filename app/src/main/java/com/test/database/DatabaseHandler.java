package com.test.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.manager.TestDatabaseManager;
import com.test.models.TestModel;

import java.util.ArrayList;

/**
 * Created by Mankesh Mishra on 9/4/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Test.db";
    private final String testTable = "testTable";
    private TestDatabaseManager testDatabaseManager;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF  NOT EXISTS testTable(id INTEGER PRIMARY KEY  " +
                "AUTOINCREMENT ,fullName VARCHAR, mobileNumber VARCHAR,emailId VARCHAR,password VARCHAR)");

    }

    public void initObjects() {
        testDatabaseManager = new TestDatabaseManager(this.getWritableDatabase());

        alterTable(testTable);
    }

    public void alterTable(String tableName) {

        Cursor res = this.getWritableDatabase().rawQuery("SELECT sql as msql FROM sqlite_master WHERE tbl_name='" + tableName + "' AND type='table'", null);
        res.moveToFirst();
        String strFindColumn = "";
        while (res.isAfterLast() == false) {

            strFindColumn = res.getString(0);

            res.moveToNext();

        }
        res.close();
        switch (tableName) {
            case testTable:/*No Need to do */
                addColumnsInCategory(tableName, strFindColumn);
                break;
        }
    }

    /*When any update is need in tables*/
    public void addColumnsInCategory(String tableName, String strFindColumn) {
        /*if (!strFindColumn.equals("")){
            if (!strFindColumn.contains("isNeed")){
                addColumnsInTable(tableName,"isNeed BOOLEAN");
            }
        }*/
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        onCreate(db);
    }

    /*For AutoCategory table*/
    public boolean insertCategory(TestModel testModel) {

        return testDatabaseManager.insertCategory(testModel);
    }

    public ArrayList<TestModel> getFullArrayList() {
        return testDatabaseManager.getFullArrayList();
    }

    public ArrayList<TestModel> getSearchList(String fullName) {
        return testDatabaseManager.getSearchList(fullName);
    }

}
