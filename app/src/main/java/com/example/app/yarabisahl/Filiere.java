package com.example.app.yarabisahl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Filiere extends AppCompatActivity {

      EditText Et1;
      Button Btn1;
      Button Btn2;
      DatabaseHelper dbF;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filiere);
        dbF = new DatabaseHelper(this);
        Btn1 = (Button)findViewById(R.id.Btn_add);
        Btn2 = (Button)findViewById(R.id.Btn_del);
        Et1 = (EditText) findViewById(R.id.T_A_S);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean DtInsert = dbF.InsertdataFiliere(Et1.getText().toString());
                if (DtInsert==true)
                {
                    Toast.makeText(Filiere.this, "Spinneret Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Filiere.this, "Spinneret Non Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = dbF.deleteDataFil(Et1.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(Filiere.this,"Spinneret Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Filiere.this,"Spinneret not Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }



}