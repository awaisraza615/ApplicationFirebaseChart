package com.example.applicationfirebasechart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText eID,eName,eAddress,eAge;
    private Button bInsert,btnChart;
    private int UserID;
    String aget,idt;
    DatabaseReference databaseReference;

    ListView listViewdata;
    List<InsertData> edatalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        eID= (EditText)findViewById(R.id.txtId);
        eName= (EditText)findViewById(R.id.txtName);
        eAddress= (EditText)findViewById(R.id.txtAddress);
        eAge= (EditText)findViewById(R.id.txtage);
        bInsert= (Button)findViewById(R.id.btnInsert);
        btnChart= (Button)findViewById(R.id.btnChart);

        listViewdata = (ListView)findViewById(R.id.elistview);
        edatalist = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Information");


        bInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eID.getText().toString().isEmpty() && eAge.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "please fill ID and Age is Must", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    InsertData();
                }

            }
        });
        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,SecondActivity.class);
                in.putExtra("Age",aget);
                in.putExtra("Id",idt);
                startActivity(in);
            }
        });



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                edatalist.clear();

                Integer total=0,total2 = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    InsertData insertData = ds.getValue(InsertData.class);

                    Integer age = Integer.valueOf(insertData.getmAge());
                    total +=age;
                    Integer ids = Integer.valueOf(insertData.getmID());
                    total2 +=ids;
                    aget=total.toString();
                    idt=total2.toString();
                    edatalist.add(insertData);
                }
                DataList adapter = new DataList(MainActivity.this,edatalist);
                listViewdata.setAdapter(adapter);

                Toast.makeText(MainActivity.this, "value 0 "+total, Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, "value 1"+total2, Toast.LENGTH_SHORT).show();

                Log.d("Age", "mAge: "+total);
                Log.d("Id", "mAge: "+total2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


                Toast.makeText(MainActivity.this, "error occured"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                edatalist.clear();

                for (DataSnapshot datasnapshots : dataSnapshot.getChildren())
                {


                    InsertData inserteData = datasnapshots.getValue(InsertData.class);
                    edatalist.add(inserteData);

                }
                DataList adapter = new DataList(MainActivity.this,edatalist);
                listViewdata.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void InsertData()
    {

        String Id = eID.getText().toString().trim();
        String Name = eName.getText().toString().trim();
        String Address = eAddress.getText().toString().trim();
        String Age = eAge.getText().toString().trim();


        InsertData insertData = new InsertData(Id,Name,Address,Age);
        databaseReference.child(Id).setValue(insertData);
        Log.d("eid", "onCreate: "+eID.getText().toString());
        Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
}

