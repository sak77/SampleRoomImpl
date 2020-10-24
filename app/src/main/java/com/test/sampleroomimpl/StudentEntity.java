package com.test.sampleroomimpl;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by saket.shriwas on 11/8/2017.*
 * Each entity class represents a table in the DB. Each property of the class represents a column in the table.
 */

@Entity (tableName = "student_master")
public class StudentEntity {
    //Each entity must have one primary key
    @PrimaryKey (autoGenerate =  true)
    public int student_id;
    public  String first_name;
    public String last_name;
    public int age;
    public int roll_no;
    public  String student_class;
    public  String class_div;
}

