package com.test.sampleroomimpl;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by saket.shriwas on 11/8/2017.
 */

@Dao
public interface StudentDao {
    //The DAO class/interface contains convenience methods to insert, update and delete records and Query methods to read/write records
    //It can be an abstract class or interface. If its an abstract class then it can OPTIONALLY have a constructor with the RoomDatabase as its parameter
    @Insert
    public long insertStudent(StudentEntity mStudent);  //if insert query receives only one param it can return long which represents row id of inserted
    //row. Else if its parameter is array of objects then returns a list or Array of long
    @Update
    public void updateStudent(StudentEntity... mStudents); //Update matches the PRIMARY KEY of each entity to update in the DB
    @Delete
    public void deleteStudent(StudentEntity... mStudents); //Delete query matches the PRIMARY KEY of each entity to delete in the DB

    //QUERY methods
    @Query("SELECT * FROM student_master")
    public List<StudentEntity> getAllStudents();

    @Query("SELECT * FROM student_master where class_div = 'A'")
    public StudentEntity[] getClassAStudents();

    //Parameterised query
    @Query("SELECT * FROM STUDENT_MASTER WHERE age >= :mAge")
    public StudentEntity[] getStudentsAgeWise(int mAge);

    @Query("SELECT * FROM STUDENT_MASTER WHERE first_name LIKE :mName")
    public StudentEntity findStudent(String mName);

    //Getting Subsets of columns - if you want you can create new POJOs with only required columns which the method can return.
    //For this you can define a new class with the required fields and make sure they match with column names in table

    //Collection as params
    @Query("SELECT * FROM STUDENT_MASTER WHERE first_name IN (:mFirstName)")
    public StudentEntity[] findAllStudents(List<String> mFirstName);

}
