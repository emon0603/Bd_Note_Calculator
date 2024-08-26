package com.developeremon.bdnotecalculator;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class About extends AppCompatActivity {

    ImageView whatsappbt, facebt,linkbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about);


        whatsappbt = findViewById(R.id.whatsappbt);
        facebt = findViewById(R.id.facebt);
        linkbt = findViewById(R.id.linkbt);



        whatsappbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWhatsApp();

            }
        });




        facebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String facebookId = "rmrakibul.ahmed/";
                String facebookUrl = "https://www.facebook.com/" + facebookId;
                Intent intent = new Intent(Intent.ACTION_VIEW);

                try {
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent.setData(Uri.parse("fb://profile/" + facebookId));
                } catch (Exception e) {
                    intent.setData(Uri.parse(facebookUrl));
                }

                startActivity(intent);
            }


        });

        linkbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String linkedInId = "md-emonhossain/";
                String linkedInUrl = "https://www.linkedin.com/in/" + linkedInId;

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl));
                startActivity(intent);

            }
        });


    }
    //=========================================================

    private void openWhatsApp() {
        // Replace "your_phone_number" with your actual phone number in international format
        String phoneNumber = "+8801755756373";
        String message = "Hello, You can contract me If you need";

        // Construct the URL to open a chat with the specified number
        String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));

        if (isWhatsAppInstalled()) {
            startActivity(intent);
        } else {

        }
    }



    //=========================================================




    private boolean isWhatsAppInstalled() {
        try {
            getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }




}