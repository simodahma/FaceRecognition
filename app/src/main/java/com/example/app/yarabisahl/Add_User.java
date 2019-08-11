package com.example.app.yarabisahl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_User extends AppCompatActivity {

     EditText Etd,Ed1,Ed2,Ed3;
    Button btn_User;
    DatabaseHelper dbL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__user);
        dbL = new DatabaseHelper(this);
        btn_User = (Button)findViewById(R.id.Add_F);
        addData();
    }

    public void addData()
    {
        Etd = (EditText) findViewById(R.id.E_CneX);
        Ed1 = (EditText) findViewById(R.id.E_Name);
        Ed3 = (EditText) findViewById(R.id.E_Fil);
        btn_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean DtInsert = dbL.Insertdata(Etd.getText().toString(),
                        Ed1.getText().toString(),
                        Ed3.getText().toString()
                );
                if (DtInsert==true)
                {
                    Toast.makeText(Add_User.this, "Teacher Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Add_User.this, "Teacher Non Added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
