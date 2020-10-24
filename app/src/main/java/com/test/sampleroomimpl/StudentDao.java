package com.test.sampleroomimpl;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by saket.shriwas on 11/8/2017.
 *
 * The DAO class/interface contains convenience methods to insert, update and delete records and
 * Query methods to read/write records.
 * It can be an abstract class or interface. If its an abstract class then it can OPTIONALLY have a
 * constructor with the RoomDatabase as its parameter
 */

@Dao
public interface StudentDao {
    //if insert query receives only one param it can return long which represents row id of inserted
    //row. Else if its parameter is array of objects then returns a list or Array of long
    @Insert
    long insertStudent(StudentEntity mStudent);

    //Update matches the PRIMARY KEY of each entity to update in the DB
    @Update
    void updateStudent(StudentEntity... mStudents);

    //Delete query matches the PRIMARY KEY of each entity to delete in the DB
    @Delete
    void deleteStudent(StudentEntity... mStudents);

    //QUERY methods
    @Query("SELECT * FROM student_master")
    List<StudentEntity> getAllStudents();

    @Query("SELECT * FROM student_master where class_div = 'A'")
    StudentEntity[] getClassAStudents();

    //Parameterised query
    @Query("SELECT * FROM STUDENT_MASTER WHERE age >= :mAge")
    StudentEntity[] getStudentsAgeWise(int mAge);

    @Query("SELECT * FROM STUDENT_MASTER WHERE first_name LIKE :mName")
    StudentEntity findStudent(String mName);

    /*Getting Subsets of columns - if you want you can create new POJOs with only required
    columns which the method can return.
    For this you can define a new class with the required fields and make sure they match with
    column names in table*/

    //Collection as params
    @Query("SELECT * FROM STUDENT_MASTER WHERE first_name IN (:mFirstName)")
    StudentEntity[] findAllStudents(List<String> mFirstName);

}
