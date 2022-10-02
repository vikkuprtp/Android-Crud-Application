package com.example.mycrudapp;

import static android.widget.Toast.*;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3;  //creating editable textbox to get input
    Button bt1, bt2; //creating two buttons for submit and view


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ed1 = findViewById(R.id.name); //taking name as input
        ed2 = findViewById(R.id.course);//course
        ed3 = findViewById(R.id.fee);//fee

        bt1 = findViewById(R.id.btn1);  //submit button
        bt2 = findViewById(R.id.btn2);  //view button

       //when clicked on btn2 that is the action being taken

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //when clicked it ll go on view class

                Intent it=new Intent(getApplicationContext(),view.class);
                startActivity(it);
            }
        });


        //action performed when clicked btn1

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calling insert function

                insert();

            }
        });
    }
        public void insert()
        {
            try {
                //taking the stored input value from ed1 and converting to string later stored into variables(name,course,fee)

                String name = ed1.getText().toString();
                String course = ed2.getText().toString();
                String fee = ed3.getText().toString();


                //creating the object(db) of database
                SQLiteDatabase db = openOrCreateDatabase("maindb", Context.MODE_PRIVATE, null);

                //executing command providing table names
                db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,course VARCHAR,fee VARCHAR )");

                //inserting the tables column names
                String sql = "insert into records(name,course,fee)values(?,?,?)";
                //creating object

                SQLiteStatement statement = db.compileStatement(sql);

                //binding based on value passed in variable
                statement.bindString(1, name);
                statement.bindString(2, course);
                statement.bindString(3, fee);
                statement.execute();


               Toast.makeText(this,"Record updated",Toast.LENGTH_LONG).show();

               //dping to clear once data is added
                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed1.requestFocus();
                ed2.requestFocus();
                ed3.requestFocus();
            }
            catch (Exception e) {
                Toast.makeText(this,"error",Toast.LENGTH_LONG).show();

            }
        }
    }