package com.test.sampleroomimpl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * The app stores student info and displays the saved results in the logcat.
 * A simple DB App that uses Room API to save and retrieve data.
 * There are 3 main components of a Room DB -
 *
 * Entity class represents a table in the DB
 * Dao is an interface which holds the different operations which can be performed for the entity
 * An abstract class that extends RoomDatabase instance itself and holds reference to entities and corresponding dao.
 *
 * DB creation is expensive so it should be done only once for the whole app. Preferably at the application level.
 */
public class MainActivity extends AppCompatActivity {

    Activity currActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(insert_click);
        Button btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(show_click);
        currActivity = this;
    }

    Button.OnClickListener insert_click = new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
            //Read data from fields
            EditText edtfName = (EditText) findViewById(R.id.edtfName);
            EditText edtLName = (EditText) findViewById(R.id.edtLName);
            EditText edtAge = (EditText) findViewById(R.id.edtAge);
            EditText edtClass = (EditText) findViewById(R.id.edtClass);
            EditText edtDiv = (EditText) findViewById(R.id.edtDiv);
            EditText edtRollNo = (EditText) findViewById(R.id.edtRno);
            //Crate new instance of StudentEntity
            final StudentEntity myStudent =  new StudentEntity();
            myStudent.first_name = edtfName.getText().toString();
            myStudent.last_name = edtLName.getText().toString();
            myStudent.age = Integer.parseInt(edtAge.getText().toString());
            myStudent.student_class = edtClass.getText().toString();
            myStudent.class_div = edtDiv.getText().toString();
            myStudent.roll_no= Integer.parseInt(edtRollNo.getText().toString());
            //Now insert this record to DB
            try {
                //All DB actions must be executed on a separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StudentDao studentDao = ((MyApplicationClass)getApplication()).myDB.getStudentDao();
                        final long rowId = studentDao.insertStudent(myStudent);
                        currActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                displayResult(rowId);
                            }
                        });
                    }
                }).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void displayResult(long rowId){
        if (rowId>-1){
            Toast.makeText(getApplicationContext(),"Inserted successfully!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Insert error",Toast.LENGTH_SHORT).show();
        }
    }

    Button.OnClickListener show_click = new Button.OnClickListener(){

        @Override
        public void onClick(View view) {
            try {
                //All DB actions must be executed on a separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StudentDao studentDao = ((MyApplicationClass)getApplication()).myDB.getStudentDao();
                        final List<StudentEntity> allStudents = studentDao.getAllStudents();
                        for (StudentEntity currStudent : allStudents){
                            Log.d("MainActivity","Student ID: "+ currStudent.student_id);
                            Log.d("MainActivity","Student Name: "+ currStudent.first_name);
                            Log.d("MainActivity","Student Class: "+ currStudent.student_class);
                        }
                    }
                }).start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
