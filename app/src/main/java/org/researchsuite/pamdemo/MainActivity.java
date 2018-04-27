package org.researchsuite.pamdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.researchsuite.pamdemo.studyManagement.RSActivity;
import org.researchsuite.pamdemo.studyManagement.RSActivityManager;

public class MainActivity extends RSActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isReadStoragePermissionGranted() && isWriteStoragePermissionGranted()) {
            RSActivityManager.get().queueActivity(this, "RSpam", true);
            RSActivityManager.get().queueActivity(this, "demography", true);
            RSActivityManager.get().queueActivity(this, "planner", true);
            RSActivityManager.get().queueActivity(this, "productivity", true);



        }
    }

    public boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;

            case 3:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                break;
        }
    }

}