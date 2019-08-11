package com.example.app.yarabisahl;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class View_Result extends AppCompatActivity {
    EditText T;
    Button Btn;
    DatabaseHelper dbL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__result);
        dbL = new DatabaseHelper(this);
        T = (EditText)findViewById(R.id.E_t_sh);
        Btn = (Button)findViewById(R.id.btn_show);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = dbL.getStdData();
                if(res.getCount() == 0) {
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Cne :"+ res.getString(1)+"\n");
                    buffer.append("Name :"+ res.getString(2)+"\n");
                    buffer.append("L_Name :"+ res.getString(3)+"\n");
                    buffer.append("Abs :"+ res.getString(4)+"\n");
                    buffer.append("Fil :"+ res.getString(5)+"\n\n");
                   // Ed1.setText(res.getString(1).toString());
                    //Ed2.setText(res.getString(2).toString());
                    //Ed3.setText(res.getString(3).toString());

                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
