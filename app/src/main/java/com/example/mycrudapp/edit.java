package com.example.mycrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends AppCompatActivity {


    EditText ed1, ed2, ed3,ed4;
    Button bt1, bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.course);
        ed3 = findViewById(R.id.fee);
        ed4=findViewById(R.id.id);

        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);
        bt3=findViewById(R.id.bt3);


        Intent it=getIntent();
        //here we took the values from last activity and using getStringExtra we accessed it and later converted into string and stored into string variables.
        String t1=it.getStringExtra("id").toString();
        String t2=it.getStringExtra("name").toString();
        String t3=it.getStringExtra("course").toString();
        String t4=it.getStringExtra("fee").toString();


        ed4.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);
        ed3.setText(t4);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(),view.class);
                startActivity(it);
            }
        });


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Edit();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Delete();
            }
        });
    }
        public void Edit()
        {
            try {
                String name = ed1.getText().toString();
                String course = ed2.getText().toString();
                String fee = ed3.getText().toString();
                String id=ed4.getText().toString();


                SQLiteDatabase db = openOrCreateDatabase("maindb", Context.MODE_PRIVATE, null);


                String sql = "update records set name=?,course=?,fee=? where id=?";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, name);
                statement.bindString(2, course);
                statement.bindString(3, fee);
                statement.bindString(4, id);
                statement.execute();
                Toast.makeText(this,"updated successfully",Toast.LENGTH_LONG).show();

                ed1.setText("");
                ed2.setText("");
                ed3.setText("");
                ed1.requestFocus();
                ed2.requestFocus();
                ed3.requestFocus();

            }
            catch (Exception e) {
                Toast.makeText(this,"error while updating",Toast.LENGTH_LONG).show();

            }
    }
    public void Delete(){
        try {
            String id=ed4.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("maindb", Context.MODE_PRIVATE, null);



            String sql = "delete from records where id=?";
            SQLiteStatement st=db.compileStatement(sql);
            st.bindString(1,id);
            st.execute();
            Toast.makeText(this,"record deleted",Toast.LENGTH_LONG).show();
            ed1.setText("");

            ed1.requestFocus();
        }catch(Exception e){
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();

        }
    }
}