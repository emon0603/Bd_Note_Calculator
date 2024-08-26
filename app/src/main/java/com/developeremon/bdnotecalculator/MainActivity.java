package com.developeremon.bdnotecalculator;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.messaging.FirebaseMessaging;

import org.checkerframework.checker.nullness.qual.NonNull;

public class MainActivity extends AppCompatActivity {

    TextView tktotal, totalnotes, tv1000,tv500,tv200,tv100,tv50,tv20,tv10,tv5,tv2,tv1;
    EditText ed1000, ed500, ed200, ed100, ed50, ed20,ed10,ed5,ed2, ed1;
    Button  reset;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tktotal = findViewById(R.id.totaltk);
        totalnotes = findViewById(R.id.totalnotes);
        toolbar = findViewById(R.id.toolbar);

        tv1000 = findViewById(R.id.tvdisplay1000);
        tv500 = findViewById(R.id.tvdisplay500);
        tv200 = findViewById(R.id.tvdisplay200);
        tv100 = findViewById(R.id.tvdisplay100);
        tv50 = findViewById(R.id.tvdisplay50);
        tv20 = findViewById(R.id.tvdisplay20);
        tv10 = findViewById(R.id.tvdisplay10);
        tv5 = findViewById(R.id.tvdisplay5);
        tv2 = findViewById(R.id.tvdisplay2);
        tv1 = findViewById(R.id.tvdisplay1);

        ed1000 = findViewById(R.id.ed1000);
        ed500 = findViewById(R.id.ed500);
        ed200 = findViewById(R.id.ed200);
        ed100 = findViewById(R.id.ed100);
        ed50 = findViewById(R.id.ed50);
        ed20 = findViewById(R.id.ed20);
        ed10 = findViewById(R.id.ed10);
        ed5 = findViewById(R.id.ed5);
        ed2 = findViewById(R.id.ed2);
        ed1 = findViewById(R.id.ed1);

        reset = findViewById(R.id.resetbutton);


        //------------------------------------------------------------

        askNotificationPermission();
        logRegToken();


        //------------------------------------------------------------

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tktotal.setText(null);
                totalnotes.setText(null);

                ed1000.setText(null);
                ed500.setText(null);
                ed200.setText(null);
                ed100.setText(null);
                ed50.setText(null);
                ed20.setText(null);
                ed10.setText(null);
                ed5.setText(null);
                ed2.setText(null);
                ed1.setText(null);

                tv1000.setText(null);
                tv500.setText(null);
                tv200.setText(null);
                tv100.setText(null);
                tv50.setText(null);
                tv20.setText(null);
                tv10.setText(null);
                tv5.setText(null);
                tv2.setText(null);
                tv1.setText(null);




            }
        });


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateAndDisplayTotalSum();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        ed1000.addTextChangedListener(textWatcher);
        ed500.addTextChangedListener(textWatcher);
        ed200.addTextChangedListener(textWatcher);
        ed100.addTextChangedListener(textWatcher);
        ed50.addTextChangedListener(textWatcher);
        ed20.addTextChangedListener(textWatcher);
        ed10.addTextChangedListener(textWatcher);
        ed5.addTextChangedListener(textWatcher);
        ed2.addTextChangedListener(textWatcher);
        ed1.addTextChangedListener(textWatcher);



        //=============================================================

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.sharebt){

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this amazing app I found!\n"+"https://drive.google.com/drive/u/0/folders/1UR5Ru-v5pHqtdlA6zxSvLBcMmsrgAPhf");
                    startActivity(Intent.createChooser(shareIntent, "Share via"));

                } else if (item.getItemId() == R.id.aboutbt){

                    startActivity(new Intent(MainActivity.this, About.class));

                }



                return true;
            }
        });








        //=============================================================





    }//----------




    private void calculateAndDisplayTotalSum() {
        // ইনপুট নাম্বারগুলো নেওয়া
        String sed1 = ed1.getText().toString();
        String sed2 = ed2.getText().toString();
        String sed5 = ed5.getText().toString();
        String sed10 = ed10.getText().toString();
        String sed20 = ed20.getText().toString();
        String sed50 = ed50.getText().toString();
        String sed100 = ed100.getText().toString();
        String sed200 = ed200.getText().toString();
        String sed500 = ed500.getText().toString();
        String sed1000 = ed1000.getText().toString();




        int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0, sum9 = 0, sum10 = 0;
        int note1 = 0, note2 = 0, note3 = 0, note4 = 0, note5 = 0, note6 = 0, note7 = 0, note8 = 0, note9 = 0, note10 = 0;

        if (!sed1.isEmpty()) {
            sum1 = Integer.parseInt(sed1) * 1;
            note1 = Integer.parseInt(note1+sed1);
            tv1.setText("" + sum1);
        }

        if (!sed2.isEmpty()) {
            sum2 = Integer.parseInt(sed2) * 2;
            note2 = Integer.parseInt(note2+sed2);
            tv2.setText("" + sum2);
        }

        if (!sed5.isEmpty()) {
            sum3 = Integer.parseInt(sed5) * 5;
            note3 = Integer.parseInt(note3+sed5);
            tv5.setText("" + sum3);
        }

        if (!sed10.isEmpty()) {
            sum4 = Integer.parseInt(sed10) * 10;
            note4 = Integer.parseInt(note4+sed10);
            tv10.setText("" + sum4);
        }
        if (!sed20.isEmpty()) {
            sum5 = Integer.parseInt(sed20) * 20;
            note5 = Integer.parseInt(note5+sed20);
            tv20.setText("" + sum5);
        }

        if (!sed50.isEmpty()) {
            sum6 = Integer.parseInt(sed50) * 50;
            note6 = Integer.parseInt(note6+sed50);
            tv50.setText("" + sum6);
        }

        if (!sed100.isEmpty()) {
            sum7 = Integer.parseInt(sed100) * 100;
            note7 = Integer.parseInt(note7+sed100);
            tv100.setText("" + sum7);
        }

        if (!sed200.isEmpty()) {
            sum8 = Integer.parseInt(sed200) * 200;
            note8 = Integer.parseInt(note8+sed200);
            tv200.setText("" + sum8);
        }

        if (!sed500.isEmpty()) {
            sum9 = Integer.parseInt(sed500) * 500;
            note9 = Integer.parseInt(note9+sed500);
            tv500.setText("" + sum9);
        }

        if (!sed1000.isEmpty()) {
            sum10 = Integer.parseInt(sed1000) * 1000;
            note10 = Integer.parseInt(note10+sed1000);
            tv1000.setText("" + sum10);
        }

        int totalSum = sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7 + sum8 + sum9 + sum10;
        int notestotal = note1 + note2 + note3 + note4 + note5 + note6 + note7 + note8 + note9 + note10;

        // ফলাফল দেখানো
        tktotal.setText(""+totalSum);
        totalnotes.setText(""+notestotal);

    }


   //-------------------------------------------------------------------------

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                } else {

                }
            });
    //-------------------------------------------------------------------------


    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {

            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {

            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        }


    }
    //----------------------------------------------------------------------
    private void logRegToken() {
        // [START log_reg_token]
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("token", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = "FCM Registration token: " + token;
                        Log.d(TAG, msg);

                    }
                });

    }


}