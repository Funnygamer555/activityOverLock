package com.example.anh_tuan.activityoverlock;

import android.app.Dialog;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    private DevicePolicyManager mDevicePolicyManager;
    private static final String description = "Some Description About Your Admin";
    public boolean result;
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        setContentView(R.layout.activity_main);


        findViewById(R.id.activity_main).setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
         

        Button btn = (Button) findViewById(R.id.button);
        Button btn1 = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                unregister(v);
                }
        });
        mDevicePolicyManager = (DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);
        //someMethod();

    }

    public void click(View v){
        //Dialog dialog = new Dialog(this);


        //Dialog dialog = new AlertDialog.Builder(this).setView(R.layout.dialog).create();
        //dialog.getWindow().setType(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
         /**                   |WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY
                            |WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
                            |WindowManager.LayoutParams.WRAP_CONTENT
                            |WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

        dialog.setContentView(R.layout.dialog);
        dialog.show();


        /**
        Dialog dialog = new Dialog(this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
        dialog.setContentView(R.layout.dialog);
        dialog.show();

        /**
        Intent i = new Intent(this,Dialog.class);
        startActivity(i);





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(new String[]{"android.permission.SYSTEM_ALERT_WINDOW"}, 2);
        }


        boolean isAdmin = mDevicePolicyManager.isAdminActive(compName);
        if (isAdmin) {
            mDevicePolicyManager.lockNow();
        }else{
            Toast.makeText(getApplicationContext(), "Not Registered as admin", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,description);
            startActivityForResult(intent, 15);
            System.out.println("register admin");

        }

        */
    }


    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("resultAc");
        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Registered As Admin", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Failed to register as Admin", Toast.LENGTH_SHORT).show();
                System.out.println(resultCode);
            }

        }
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                // SYSTEM_ALERT_WINDOW permission not granted...
                System.err.println("no permission for overlays");
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
    public void unregister(View v){
        System.exit(0);
    }
    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults){
        if (!permissions.equals(null) && !grantResults.equals(null)){
            for (int i = 0;i<permissions.length;i++){
                System.out.println(permissions[i]+ " "+grantResults[i]);
            }
        }
    }
    public void someMethod() {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        }
    }
}
