package com.udiary.simplegames;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    GifImageView rabbitgif,tictocgif,pianogif,memorycheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);


                LinearLayout dialog   = (LinearLayout)findViewById(R.id.linear1);
                dialog.setVisibility(LinearLayout.VISIBLE);
                Animation animation   =    AnimationUtils.loadAnimation(getBaseContext(), R.anim.animation);
                animation.setDuration(500);
                dialog.setAnimation(animation);
                dialog.animate();
                animation.start();






        rabbitgif=(GifImageView) findViewById(R.id.rabbitgif);
                tictocgif=(GifImageView) findViewById(R.id.tictocgif);
                pianogif=(GifImageView) findViewById(R.id.pianogif);
                memorycheck=(GifImageView) findViewById(R.id.memorygif);

                rabbitgif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent rabbitclickintent=new Intent(MainActivity.this,Rabbit.class);
                        startActivity(rabbitclickintent);
                    }
                });

        tictocgif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rabbitclickintent=new Intent(MainActivity.this,TicToc.class);
                startActivity(rabbitclickintent);
            }
        });

        pianogif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rabbitclickintent=new Intent(MainActivity.this,Piano.class);
                startActivity(rabbitclickintent);
            }
        });

        memorycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rabbitclickintent=new Intent(MainActivity.this,MemoryChecker.class);
                startActivity(rabbitclickintent);
            }
        });


    }


    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        new FancyAlertDialog.Builder(this)
                .setTitle("Exit the app")
                .setBackgroundColor(Color.parseColor("#fbb22e"))  //Don't pass R.color.colorvalue
                .setMessage("Do you really want to Exit ?")
                .setNegativeBtnText("Cancel")
                .setPositiveBtnBackground(Color.parseColor("#ef8b17"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("OK")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                .setAnimation(com.shashank.sony.fancydialoglib.Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.exit, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        // Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

    }
}
