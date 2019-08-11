package com.example.app.yarabisahl;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class Manage_Std extends AppCompatActivity {
    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__std);
        circleMenu = (CircleMenu) findViewById(R.id.circleMenu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.mipmap.update)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.icon_search)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.icon)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.icon_setting);
                //.addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.icon_gps);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             makeText(Manage_Std.this, "Update Student", LENGTH_SHORT).show();
                                                                   Intent In_up = new Intent(Manage_Std.this,Update.class);
                                                                   startActivity(In_up);
                                                             break;
                                                         case 1:
                                                             makeText(Manage_Std.this, "Start", LENGTH_SHORT).show();
                                                             Intent Sr =new Intent(Manage_Std.this,View_Result.class);
                                                             startActivity(Sr);

                                                             break;
                                                         case 2:
                                                             makeText(Manage_Std.this, "Delete Student", LENGTH_SHORT).show();
                                                             Intent In_dl = new Intent(Manage_Std.this,Delete.class);
                                                             startActivity(In_dl);
                                                             break;
                                                         case 3:
                                                             makeText(Manage_Std.this, "Manage Spinneret", LENGTH_SHORT).show();
                                                             Intent I_dl = new Intent(Manage_Std.this,Filiere.class);
                                                             startActivity(I_dl);
                                                             break;
                                                         /*case 4:
                                                             makeText(Manage_Std.this, "GPS button Clicked", LENGTH_SHORT).show();
                                                             break;*/
                                                     }
                                                 }
                                             }

        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                                                     @Override
                                                     public void onMenuOpened() {

                                                     }

                                                     @Override
                                                     public void onMenuClosed() {

                                                     }
                                                 }
        );
    }

    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
        finish();
    }
}
