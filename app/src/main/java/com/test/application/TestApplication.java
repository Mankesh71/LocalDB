package com.test.application;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.test.database.DBHelperSingleton;


/**
 * Created by Maik on 4/10/2016.
 */
@SuppressWarnings("DefaultFileTemplate")
public class TestApplication extends MultiDexApplication {

    public TestApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        DBHelperSingleton.getInstance().initDB(this);
    }


}
