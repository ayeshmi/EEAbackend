package com.example.eeaassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class BranchesDetails extends AppCompatActivity {
    private Button call1;
    private Button share1;
    private Button call2;
    private Button share2;
    private Button call3;
    private Button share3;
    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches_details);
        call1=findViewById(R.id.call1);
        share1=findViewById(R.id.share1);
        call2=findViewById(R.id.call2);
        share2=findViewById(R.id.share2);
        call3=findViewById(R.id.call3);
        share3=findViewById(R.id.share3);
        call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.co.in/maps?q="+"228, Havelock Rd, Colombo 05";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                startActivity(intent);
            }
        });
        share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("0112556143");
            }
        });
        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.co.in/maps?q="+" No:42D, Hospital Rd, Angoda";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                startActivity(intent);
            }
        });
        share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("0112567237");
            }
        });
        call3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://maps.google.co.in/maps?q="+"375/10 A High level Road, Makumbura";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                startActivity(intent);
            }
        });
        share3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall("0112098105");
            }
        });
    }

    private void makePhoneCall(String number) {

        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(BranchesDetails.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(BranchesDetails.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(BranchesDetails.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall("0112245798");

            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}