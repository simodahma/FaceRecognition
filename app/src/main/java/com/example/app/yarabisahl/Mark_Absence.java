package com.example.app.yarabisahl;

import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Mark_Absence extends AppCompatActivity  implements CameraBridgeViewBase.CvCameraViewListener2{

    private static final String TAG="MainActivity";
    JavaCameraView javaCameraView;
    Mat mRgba;
    Button Btn2;
    DatabaseHelper dbL;
    BaseLoaderCallback MLoaderCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status)
            {
                case BaseLoaderCallback.SUCCESS:
                {
                    javaCameraView.enableView();
                    break;
                }
                default:
                {
                    super.onManagerConnected(status);
                    break;
                }
            }
        }
    };

    static {

        System.loadLibrary("MyLibs");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark__absence);
        dbL = new DatabaseHelper(this);
        javaCameraView = (JavaCameraView)findViewById(R.id.java_cam_Reco);//initialisation javaCameraView
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
        Btn2 =(Button)findViewById(R.id.Btn_Fin);

        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = dbL.Mark(
                            Integer.toString(OpencvClass.lab1()),"P");
                    if(isUpdate == true)
                        Toast.makeText(Mark_Absence.this,"Student Update",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(Mark_Absence.this,"Student not Updated",Toast.LENGTH_LONG).show();

                 isUpdate = dbL.Mark(
                        Integer.toString(OpencvClass.lab2()),"P");
                if(isUpdate == true)
                    Toast.makeText(Mark_Absence.this,"Student Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Mark_Absence.this,"Student not Updated",Toast.LENGTH_LONG).show();

                isUpdate = dbL.Mark(
                        Integer.toString(OpencvClass.lab3()),"P");
                if(isUpdate == true)
                    Toast.makeText(Mark_Absence.this,"Student Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Mark_Absence.this,"Student not Updated",Toast.LENGTH_LONG).show();

                isUpdate = dbL.Mark(
                        Integer.toString(OpencvClass.lab4()),"P");
                if(isUpdate == true)
                    Toast.makeText(Mark_Absence.this,"Student Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Mark_Absence.this,"Student not Updated",Toast.LENGTH_LONG).show();

                isUpdate = dbL.Mark(
                        Integer.toString(OpencvClass.lab5()),"P");
                if(isUpdate == true)
                    Toast.makeText(Mark_Absence.this,"Student Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Mark_Absence.this,"Student not Updated",Toast.LENGTH_LONG).show();

                isUpdate = dbL.Mark(
                        Integer.toString(OpencvClass.lab6()),"P");
                if(isUpdate == true)
                    Toast.makeText(Mark_Absence.this,"Student Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Mark_Absence.this,"Student not Updated",Toast.LENGTH_LONG).show();

                isUpdate = dbL.Mark(
                        Integer.toString(OpencvClass.lab7()),"P");
                if(isUpdate == true)
                    Toast.makeText(Mark_Absence.this,"Student Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Mark_Absence.this,"Student not Updated",Toast.LENGTH_LONG).show();

                }



        });


    }


    @Override
    protected void onPause()
    {
        super.onPause();
        if(javaCameraView!=null)
            javaCameraView.disableView();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(javaCameraView!=null)
            javaCameraView.disableView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if(OpenCVLoader.initDebug())
        {
            Log.d(TAG,"Opencv Succesfully Loaded");
            MLoaderCallBack.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else
        {
            Log.d(TAG,"you failed");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9,this,MLoaderCallBack);
        }
    }
    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped()
    {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        OpencvClass.RecogniseFace(mRgba.getNativeObjAddr());
        return mRgba;
    }

}
