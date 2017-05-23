package com.test.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.test.database.DBHelperSingleton;


/**
 * Created by Mankesh Mishra on 10/11/2016.
 */
public class TestApplication extends MultiDexApplication {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public TestApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        TestApplication.context = getApplicationContext();
        DBHelperSingleton.getInstance().initDB(this);
    }


}
