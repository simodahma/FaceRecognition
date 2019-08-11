package com.example.app.yarabisahl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Delete extends AppCompatActivity {
Button Btn_Del;
    DatabaseHelper dbL;
    EditText Ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        dbL = new DatabaseHelper(this);
        Ed = (EditText)findViewById(R.id.E_CneX);
        Btn_Del = (Button)findViewById(R.id.Btn_UpX);
        DeleteData();
    }

    public void DeleteData() {
        Btn_Del.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = dbL.deleteDataStd (Ed.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Delete.this,"Student Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Delete.this,"Student not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
