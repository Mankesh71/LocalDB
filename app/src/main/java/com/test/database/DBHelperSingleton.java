package com.test.database;

import android.content.Context;

/**
 * Created by Maik on 4/10/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class DBHelperSingleton {
    private static DBHelperSingleton ourInstance = new DBHelperSingleton();

    public static DBHelperSingleton getInstance() {
        return ourInstance;
    }

    public static DatabaseHandler mDBDbHelper = null;

    private DBHelperSingleton() {

    }

    public void initDB(Context mContext) {
        mDBDbHelper = new DatabaseHandler(mContext);
        mDBDbHelper.getWritableDatabase();
        mDBDbHelper.initObjects();
    }
}
