package com.example.listcontact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    private DbHandler dbHandler;

    ListView listView;

    EditText search;
    Button sbtn,abtn;

    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        dbHandler = new DbHandler(context);


        listView = findViewById(R.id.contact_list);

        search = findViewById(R.id.SS);
        sbtn = findViewById(R.id.sb);
        abtn = findViewById(R.id.ab);

        contacts = new ArrayList<>();
        contacts = dbHandler.getAllContact();


        String[] nameArray = new String[contacts.size()];

        for (int x =0;x<contacts.size();x++){
            nameArray[x] = contacts.get(x).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,nameArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact contact = contacts.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(contact.getName())
                        .setMessage(contact.getNumber()
                                +"\n"+contact.getEmail()+"\n"
                                +contact.getOrganaization()+"\n"
                                +contact.getRelationship()
                        )
                        .show();
            }

        });

        abtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddContact.class);
                startActivity(intent);
            }
        });

        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("No search");
            }
        });
    }
}