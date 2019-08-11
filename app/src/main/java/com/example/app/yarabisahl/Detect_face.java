package com.example.app.yarabisahl;

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

public class Detect_face extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{


    private static final String TAG="MainActivity";
    JavaCameraView javaCameraView;
    Mat mRgba;

    Button Btn;


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
        setContentView(R.layout.activity_detect_face);

        javaCameraView = (JavaCameraView)findViewById(R.id.java_cam);


        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);

         Btn = (Button) findViewById(R.id.nw_std);
         Btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getApplicationContext(),"Start",Toast.LENGTH_SHORT).show();
                 OpencvClass.AddNewPerson();
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
        OpencvClass.faceDetection(mRgba.getNativeObjAddr());
        return mRgba;
    }
}
