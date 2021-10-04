package com.example.eeaassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Homepage extends AppCompatActivity {
 ViewFlipper v_flipper;
 Button login;
 Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        login=(Button)findViewById(R.id.button3);
        search=(Button)findViewById(R.id.button);

        int images[]={R.drawable.luanchingpage,R.drawable.lunchingimage};
        v_flipper=findViewById(R.id.view_flipper);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity1();
            }
        });

        for(int image:images){
            flipperImage(image);

        }

    }

    private void openActivity4() {
        Intent intent=new Intent(this,Login1.class);
        startActivity(intent);
    }
    private void openActivity1() {
        Intent intent=new Intent(this,SearchMedicine.class);
        startActivity(intent);
    }
    public void flipperImage(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

}