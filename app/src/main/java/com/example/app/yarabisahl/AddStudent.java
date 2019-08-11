package com.example.app.yarabisahl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddStudent extends AppCompatActivity {
    public static final int CAMERA_REQUEST = 10;
    private ImageView img;
    DatabaseHelper dbL;
    Button Btn_Add;
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Files";
 EditText Et,Et1,Et2,Et3,Et4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_std);
        dbL = new DatabaseHelper(this);
        img = (ImageView) findViewById(R.id.Etd_Pic);
        Btn_Add = (Button)findViewById(R.id.Add_Etd);
        Et =(EditText)findViewById(R.id.E_CneX);
        Et1 =(EditText)findViewById(R.id.E_Name);
        Et2 =(EditText)findViewById(R.id.E_NameF);
        Et3 =(EditText)findViewById(R.id.E_Fil);
        final String Rl = "A";
        File dir = new File(path);
        dir.mkdirs();

        Btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean DtInsert = dbL.InsertdataStd(Et.getText().toString(),
                        Et1.getText().toString(),
                        Et2.getText().toString(),
                        Rl.toString(),
                        Et3.getText().toString());

                File file = new File(path + "/F.txt");
                String [] saveText = String.valueOf(Et1.getText()).split(System.getProperty("line.separator"));
                Et.setText("");
                Et1.setText("");
                Et2.setText("");
                Et3.setText("");
                Save(file, saveText);


                if (DtInsert==true)
                {
                    Toast.makeText(AddStudent.this, "Student Added", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(AddStudent.this, "Student Not Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void Pic(View v)
    {
        Intent CameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(CameraIntent, CAMERA_REQUEST);
    }

    public void Detect(View F)
    {
       Intent In = new Intent(AddStudent.this,Detect_face.class);
        startActivity(In);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==CAMERA_REQUEST)
            {
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(cameraImage);

            }
        }
    }
    public static void Save(File file, String[] data)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }



}
