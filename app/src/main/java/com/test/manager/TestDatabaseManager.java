package com.test.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.test.models.TestModel;

import java.util.ArrayList;


/**
 * Created by Maik on 4/10/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class TestDatabaseManager {
    private SQLiteDatabase db;
    private final String TABLE_NAME_CATEGORY = "testTable";
    private final String ID = "id";
    private final String FULL_NAME = "fullName";
    private final String MOBILE_NUMBER = "mobileNumber";
    private final String EMAIL_ID = "emailId";
    private final String PASSWORD = "password";
    private final String TAG = TestDatabaseManager.class.getSimpleName();

    public TestDatabaseManager(SQLiteDatabase db) {
        this.db = db;
    }

    public boolean insertCategory(TestModel testModel) {
        db.insert(TABLE_NAME_CATEGORY, null, this.getObject(testModel));
        return true;
    }

    /*want to update any column*/
    public boolean updateTabs(String tabsListItemId, String isUserTabSelected) {
        db.update(TABLE_NAME_CATEGORY, this.getUpdatedObject(isUserTabSelected), "id = ? ", new String[]{"" + tabsListItemId});
        return true;
    }

    private ContentValues getUpdatedObject(String isUserTabSelected) {
        ContentValues contentValues = new ContentValues();
        // contentValues.put(ISUSERSELECTION, isUserTabSelected);
        return contentValues;
    }

    private ContentValues getObject(TestModel testModel) {
        ContentValues contentValues = new ContentValues();

        if (testModel.getId() != null && !("".equals(testModel.getId()))) {
            contentValues.put(ID, testModel.getId());
        }

        if (testModel.getStrFullName() != null && !("".equals(testModel.getStrFullName()))) {
            contentValues.put(FULL_NAME, testModel.getStrFullName());
        }
        if (testModel.getStrMobileNumber() != null && !("".equals(testModel.getStrMobileNumber()))) {
            contentValues.put(MOBILE_NUMBER, testModel.getStrMobileNumber());
        }
        if (testModel.getStrEmailId() != null && !("".equals(testModel.getStrEmailId()))) {
            contentValues.put(EMAIL_ID, testModel.getStrEmailId());
        }
        if (testModel.getStrPassword() != null && !("".equals(testModel.getStrPassword()))) {
            contentValues.put(PASSWORD, testModel.getStrPassword());
        }

        return contentValues;
    }

    /*get list of vehicle*/
    public ArrayList<TestModel> getFullArrayList() {
        ArrayList<TestModel> fullList = new ArrayList<>();
        // select query

        String sql = "";
        sql += "SELECT id,fullName,mobileNumber,emailId,password FROM testTable " +
                "GROUP BY fullName HAVING COUNT(*)>=1";

        try {
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    // add to list
                    fullList.add(new TestModel(cursor.getString(cursor.getColumnIndex(ID)), cursor.getString(cursor.getColumnIndex(FULL_NAME)),
                            cursor.getString(cursor.getColumnIndex(MOBILE_NUMBER)), cursor.getString(cursor.getColumnIndex(EMAIL_ID)),
                            cursor.getString(cursor.getColumnIndex(PASSWORD))));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLiteException se) {
            Log.d(TAG, se.getMessage());
        }
        // return the list of records
        return fullList;
    }

    /*get list of vehicle*/
    public ArrayList<TestModel> getSearchList(String fullName) {
        ArrayList<TestModel> searchList = new ArrayList<>();
        // select query

        String sql = "";
        sql += "SELECT * from testTable where " + FULL_NAME + "='" + fullName + "'";

        try {
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    // add to list
                    searchList.add(new TestModel(cursor.getString(cursor.getColumnIndex(ID)), cursor.getString(cursor.getColumnIndex(FULL_NAME)),
                            cursor.getString(cursor.getColumnIndex(MOBILE_NUMBER)), cursor.getString(cursor.getColumnIndex(EMAIL_ID)),
                            cursor.getString(cursor.getColumnIndex(PASSWORD))));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLiteException se) {
            Log.d(TAG, se.getMessage());
        }
        // return the list of records
        return searchList;
    }

    public void delete() {
        db.delete(TABLE_NAME_CATEGORY, null, null);
    }
}
