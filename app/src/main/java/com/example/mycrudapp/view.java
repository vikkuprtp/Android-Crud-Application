package com.example.mycrudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class view extends AppCompatActivity {

    ListView lst; //creating list
    ArrayList<String> title = new ArrayList<String>();//Collection Framework used to store in array
    ArrayAdapter arrayAdapter;//object created

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        SQLiteDatabase db = openOrCreateDatabase("maindb", Context.MODE_PRIVATE, null);
        lst = findViewById(R.id.lst1);//getting the  created list
        //selecting all query
        final Cursor c = db.rawQuery("Select * from records", null);

        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int course = c.getColumnIndex("course");
        int fee = c.getColumnIndex("fee");
        title.clear();

        //arrayAdapter is used to load the things
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, title);
        //loading into list
        lst.setAdapter(arrayAdapter);

        //putting all the values using stud object
        final ArrayList<students> studs = new ArrayList<students>();
        if (c.moveToFirst()) {
            do {
                students stu = new students();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.course = c.getString(course);
                stu.fee = c.getString(fee);
                studs.add(stu);

                //adding into titles object of array
                title.add(c.getString(id) + "\t" + c.getString(name) + "\t" + c.getString(course) + "\t" + c.getString(fee));

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst.invalidateViews();
        }

        //when clicked on any item within the list

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    String ss = title.get(position).toString();
                    //passing to edit activity
                    students stu = studs.get(position);
                    Intent it = new Intent(getApplicationContext(), edit.class);
                    //putextra to send data from one activity to another activity
                    it.putExtra("id", stu.id);
                    it.putExtra("name", stu.name);
                    it.putExtra("course", stu.course);
                    it.putExtra("fee", stu.fee);
                    startActivity(it);
            }
        });
    }
}

