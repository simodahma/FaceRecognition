package com.example.app.yarabisahl;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button BtnTeacher,btnFil;
    DatabaseHelper dbL;
    EditText Et1,Et2;
    String N,P;
    boolean S=false;
    static String V;
    public void init()
    {
        BtnTeacher=(Button)findViewById(R.id.BtnTeacher);

        BtnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N = Et1.getText().toString();
                P = Et2.getText().toString();
                Cursor res = dbL.getTeacherData();
                if(res.getCount() == 0) {

                    showMessage("Error","Nothing found");
                    return;
                }

                while (res.moveToNext()) {
                    if (res.getString(1).equals(N) && res.getString(2).equals(P))
                    {
                        S=true;
                    }break;

                }

                if (S==true)
                {
                    Intent Nl = new Intent(MainActivity.this,Navigation_menu.class);
                    startActivity(Nl);
                }
                else
                Toast.makeText(getApplicationContext(),"User Name Or Password Incorrect",Toast.LENGTH_SHORT).show();

            }
        });

        btnFil = (Button)findViewById(R.id.Btn_Sign_in);
        btnFil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IN = new Intent(MainActivity.this,Add_User.class);
                startActivity(IN);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        dbL = new DatabaseHelper(this);
        Et1 = (EditText)findViewById(R.id.E_Name);
        Et2 = (EditText)findViewById(R.id.E_NameF);
        init();
    }
}