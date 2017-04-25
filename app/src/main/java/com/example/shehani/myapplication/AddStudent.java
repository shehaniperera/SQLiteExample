package com.example.shehani.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    EditText name;
    EditText age;
    EditText nic;
    EditText address;
    EditText coursename;
    Button save;
    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name = (EditText)findViewById(R.id.edname); //get the java ref for the xml edittextname
        age = (EditText)findViewById(R.id.edage); //get the java ref for the xml edittextage
        nic = (EditText)findViewById(R.id.ednic); //get the java ref for the xml edittextnic
        address = (EditText)findViewById(R.id.eddob); //get the java ref for the xml edittextdob
        coursename = (EditText)findViewById(R.id.edcname); //get the java ref for the xml edittextcname
        save = (Button)findViewById(R.id.button); //get the java ref for the xml buttonsave


        myDb = new Database(this); // get the reference to the Database class

        addData(); // call the add data method when save button is pressed
    }



    public void addData(){ // add data method

       save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted =  myDb.insertData(name.getText().toString(),age.getText().toString(),nic.getText().toString()
                        ,address.getText().toString(),coursename.getText().toString());

                if(isInserted == true){
                    Toast.makeText(AddStudent.this,"Data is Inserted",Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(AddStudent.this,"Data is not Inserted",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
