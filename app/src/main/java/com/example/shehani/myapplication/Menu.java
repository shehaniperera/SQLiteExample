package com.example.shehani.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btnadd;
    Button btndelete;
    Button btnview;
    Database myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnadd = (Button)findViewById(R.id.btnadd); //get the java ref for the xml addbtn
        btndelete =(Button)findViewById(R.id.btndelete); //get the java ref for the xml adddelete
        btnview = (Button)findViewById(R.id.btnv); //get the java ref for the xml addupdate
        myDb = new Database(this);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Menu.this,AddStudent.class); //move into new intent when the button is pressed
                startActivity(i); // start activity

            }
        });



        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Cursor res =   myDb.getInformation(); // get all the details of all students
                if(res.getCount() == 0){
                    showMessage("Error","No Data Found"); // display error message if data is not found
                    return;
                }

                else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){ // else display all data

                        buffer.append("ID :"+res.getString(0)+"\n");
                        buffer.append("NAME :"+res.getString(1)+"\n");
                        buffer.append("AGE :"+res.getString(2)+"\n");
                        buffer.append("NIC :"+res.getString(3)+"\n");
                        buffer.append("ADDRESS :"+res.getString(4)+"\n");
                        buffer.append("COURSE NAME :"+res.getString(5)+"\n\n");


                    }

                    showMessage("DETAILS",buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title , String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
