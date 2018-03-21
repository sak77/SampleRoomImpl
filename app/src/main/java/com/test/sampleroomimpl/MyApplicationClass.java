package com.test.sampleroomimpl;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by saket.shriwas on 11/8/2017.
 */

public class MyApplicationClass extends Application {

    public SchoolDB myDB;
    @Override
    public void onCreate() {
        super.onCreate();
        //Create instance of RoomDatabase class
        createDB();
    }

    private void createDB(){
        //Note that RoomDatabase instance is expensive. So use it as singleton
        myDB = Room.databaseBuilder(getApplicationContext(),SchoolDB.class,"student_db").build();
    }

}
