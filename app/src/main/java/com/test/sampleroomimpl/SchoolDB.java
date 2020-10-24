package com.test.sampleroomimpl;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by saket.shriwas on 11/8/2017.
 * The Database class is holder of the data information
 */

@Database(entities = StudentEntity.class,version = 1)
public abstract class SchoolDB extends RoomDatabase{
    //Each Database class must contain one abstract method that returns type of DAO instance
    public abstract StudentDao getStudentDao();
}
