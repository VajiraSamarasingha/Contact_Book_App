package com.example.listcontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {


    Context context;
    DbHandler dbHandler;
    EditText et_name,et_number,et_email,et_organization;
    Button relation,add;

    String relationShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        context = this;
        dbHandler = new DbHandler(context);

        et_name = findViewById(R.id.Name);
        et_number = findViewById(R.id.Number);
        et_email = findViewById(R.id.Email);
        et_organization = findViewById(R.id.Organization);

        relation = findViewById(R.id.Relbutton);
        add = findViewById(R.id.Conbutton2);



        relationShip = "Unspecified";

        relation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[] = {"Business","Friends","Acquantence","Other"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chose Relationship Type").setItems(options,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            relationShip = "Business";
                        } else if (which==1) {
                            relationShip = "Friends";
                        } else if (which==2) {
                            relationShip = "Acquantence";
                        }else if (which==3) {
                            relationShip = "Other";
                        }

                    }
                }).show();


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String number = et_number.getText().toString();
                String email = et_email.getText().toString();
                String org = et_organization.getText().toString();

                if (name.trim().length()>0 && number.trim().length()>0 && email.trim().length()>0 && org.trim().length()>0 ){
                    Contact contact = new Contact(name,number,email,org,relationShip);
                    dbHandler.addContact(contact);
                    startActivity(new Intent(context,MainActivity.class));
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Please fill all the Fiealds ")
                            .setNegativeButton("OK",null)
                            .show();
                }
            }
        });
    }
}