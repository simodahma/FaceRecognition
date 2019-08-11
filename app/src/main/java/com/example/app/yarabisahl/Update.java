package com.example.app.yarabisahl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    Button Btn_upd;
    DatabaseHelper dbL;
    EditText Et, Et1, Et2, Et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dbL = new DatabaseHelper(this);
        Btn_upd = (Button) findViewById(R.id.Btn_UpX);
        Et = (EditText) findViewById(R.id.E_CneX);
        Et1 = (EditText) findViewById(R.id.E_Name);
        Et2 = (EditText) findViewById(R.id.E_NameF);
        Et3 = (EditText) findViewById(R.id.E_Fil);
        UpdateData();
    }


    public void UpdateData() {
        Btn_upd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = dbL.updateDataStd(Et.getText().toString(),
                                Et1.getText().toString(),
                                Et2.getText().toString(),Et3.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(Update.this,"Student Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Update.this,"Student not Updated",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
