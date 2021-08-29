package com.udiary.simplegames;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.Random;

public class Piano extends AppCompatActivity {

    ImageView iv_11,iv_12,iv_13, iv_21,iv_22,iv_23, iv_31,iv_32,iv_33,
            iv_41,iv_42,iv_43, iv_51,iv_52,iv_53;

    Button b_play;
    TextView tv_time,tv_score,tv_best;
    Random r;
    int rockLocation1,rockLocation2,rockLocation3,rockLocation4,rockLocation5;
    int frameImage,pawInframeImage,tapImage,emptyImage;
    int currentScore=0;
    int bestscore;
    CountDownTimer timer;
    Toolbar toolbar;
    private SoundPool soundPool;
    private int sound_a,sound_b,sound_c,sound_d,sound_e,sound_f,sound_g,sound_h;
    boolean sound=true;
    ImageView speaker,help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        toolbar=(Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("Piano");
        //  toolbar.setTitleTextColor();
        speaker=(ImageView) findViewById(R.id.iv_speaker);
        help=(ImageView) findViewById(R.id.iv_help);
        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound==true){
                    speaker.setImageResource(R.drawable.speaker_close);
                    sound=false;
                }else{
                    speaker.setImageResource(R.drawable.speaker);
                    sound=true;
                }

            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new LovelyStandardDialog(Piano.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.splashBg)
                        .setButtonsColorRes(R.color.black)
                        .setIcon(R.drawable.information)
                        .setTitle(R.string.titlerabbit)
                        .setMessage(R.string.meaasgePiano)
                        .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Toast.makeText(context, "positive clicked", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();

            }
        });



        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Piano.this,MainActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences preferences=getSharedPreferences("PREFS",0);
        bestscore=preferences.getInt("highscore",0);

        iv_11=(ImageView) findViewById(R.id.iv_11);
        iv_12=(ImageView) findViewById(R.id.iv_12);
        iv_13=(ImageView) findViewById(R.id.iv_13);

        iv_21=(ImageView) findViewById(R.id.iv_21);
        iv_22=(ImageView) findViewById(R.id.iv_22);
        iv_23=(ImageView) findViewById(R.id.iv_23);

        iv_31=(ImageView) findViewById(R.id.iv_31);
        iv_32=(ImageView) findViewById(R.id.iv_32);
        iv_33=(ImageView) findViewById(R.id.iv_33);

        iv_41=(ImageView) findViewById(R.id.iv_41);
        iv_42=(ImageView) findViewById(R.id.iv_42);
        iv_43=(ImageView) findViewById(R.id.iv_43);

        iv_51=(ImageView) findViewById(R.id.iv_51);
        iv_52=(ImageView) findViewById(R.id.iv_52);
        iv_53=(ImageView) findViewById(R.id.iv_53);

        b_play=(Button) findViewById(R.id.btn_play);

        tv_score=(TextView) findViewById(R.id.txt_score);
        tv_score.setText("SCORE "+currentScore);

        tv_best=(TextView) findViewById(R.id.txt_best);
        tv_best.setText("BEST "+bestscore);

        tv_time=(TextView) findViewById(R.id.txt_time);
        tv_time.setText("TIME "+millstotime(15000));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool=new SoundPool.Builder().setMaxStreams(5).build();
        }else{

            soundPool=new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }


        sound_a=soundPool.load(this,R.raw.a,1);
        sound_b=soundPool.load(this,R.raw.b,1);
        sound_c=soundPool.load(this,R.raw.c,1);

        r=new Random();
        loadImages();
        timer=new CountDownTimer(15000,500) {
            @Override
            public void onTick(long l) {
                tv_time.setText("TIME "+millstotime(l));
            }

            @Override
            public void onFinish() {
                tv_time.setText("TIME "+millstotime(0));

                iv_31.setEnabled(false);
                iv_32.setEnabled(false);
                iv_33.setEnabled(false);
                b_play.setVisibility(View.VISIBLE);

                iv_11.setImageResource(emptyImage);
                iv_12.setImageResource(emptyImage);
                iv_13.setImageResource(emptyImage);

                iv_21.setImageResource(emptyImage);
                iv_22.setImageResource(emptyImage);
                iv_23.setImageResource(emptyImage);

                iv_31.setImageResource(emptyImage);
                iv_32.setImageResource(emptyImage);
                iv_33.setImageResource(emptyImage);

                iv_41.setImageResource(emptyImage);
                iv_42.setImageResource(emptyImage);
                iv_43.setImageResource(emptyImage);

                iv_51.setImageResource(emptyImage);
                iv_52.setImageResource(emptyImage);
                iv_53.setImageResource(emptyImage);

                Toast.makeText(Piano.this,"GAME OVER",Toast.LENGTH_LONG).show();

                if(currentScore>bestscore){
                    bestscore=currentScore;
                    tv_best.setText("BEST :"+bestscore);

                    SharedPreferences preferences1=getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor=preferences1.edit();
                    editor.putInt("highscore ",bestscore);
                    editor.apply();
                }
            }
        };


        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }

                if(rockLocation3==1){
                    continueGame();
                }else {
                    endGame();
                }
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound==true){
                    soundPool.play(sound_b,1,1,0,0,1);
                }
                if(rockLocation3==2){
                    continueGame();
                }else {
                    endGame();
                }
            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound==true){
                    soundPool.play(sound_c,1,1,0,0,1);
                }
                if(rockLocation3==3){
                    continueGame();
                }else {
                    endGame();
                }
            }
        });

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initGame();
            }
        });
    }

    private void continueGame(){
        rockLocation5=rockLocation4;
        setRockLocation(rockLocation5,5);

        rockLocation4=rockLocation3;
        setRockLocation(rockLocation4,4);

        rockLocation3=rockLocation2;
        setRockLocation(rockLocation3,3);

        rockLocation2=rockLocation1;
        setRockLocation(rockLocation2,2);

        rockLocation1=r.nextInt(3)+1;
        setRockLocation(rockLocation1,1);

        currentScore++;
        tv_score.setText("SCORE "+currentScore);
    }

    private void initGame(){
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        b_play.setVisibility(View.INVISIBLE);

        currentScore=0;
        tv_score.setText("SCORE "+currentScore);

        timer.start();

        //row5-nothing
        //row4
        rockLocation4=2;
        iv_42.setImageResource(pawInframeImage);

        //row3
        rockLocation3=2;
        iv_32.setImageResource(tapImage);

        rockLocation2=r.nextInt(3)+1;
        setRockLocation(rockLocation2,2);

        rockLocation1=r.nextInt(3)+1;
        setRockLocation(rockLocation1,1);



    }

    private void endGame(){
        timer.cancel();

        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);
        b_play.setVisibility(View.VISIBLE);

        iv_11.setImageResource(emptyImage);
        iv_12.setImageResource(emptyImage);
        iv_13.setImageResource(emptyImage);

        iv_21.setImageResource(emptyImage);
        iv_22.setImageResource(emptyImage);
        iv_23.setImageResource(emptyImage);

        iv_31.setImageResource(emptyImage);
        iv_32.setImageResource(emptyImage);
        iv_33.setImageResource(emptyImage);

        iv_41.setImageResource(emptyImage);
        iv_42.setImageResource(emptyImage);
        iv_43.setImageResource(emptyImage);

        iv_51.setImageResource(emptyImage);
        iv_52.setImageResource(emptyImage);
        iv_53.setImageResource(emptyImage);

        Toast.makeText(Piano.this,"FAILED",Toast.LENGTH_LONG).show();
    }

    private void setRockLocation(int place,int row){
            if(row==1){
                iv_11.setImageResource(emptyImage);
                iv_12.setImageResource(emptyImage);
                iv_13.setImageResource(emptyImage);

                switch (place){
                    case 1:
                        iv_11.setImageResource(frameImage);
                        break;
                    case 2:
                        iv_12.setImageResource(frameImage);
                        break;
                    case 3:
                        iv_13.setImageResource(frameImage);
                        break;
                }
            }

        if(row==2){
            iv_21.setImageResource(emptyImage);
            iv_22.setImageResource(emptyImage);
            iv_23.setImageResource(emptyImage);

            switch (place){
                case 1:
                    iv_21.setImageResource(frameImage);
                    break;
                case 2:
                    iv_22.setImageResource(frameImage);
                    break;
                case 3:
                    iv_23.setImageResource(frameImage);
                    break;
            }
        }

        if(row==3){
            iv_31.setImageResource(emptyImage);
            iv_32.setImageResource(emptyImage);
            iv_33.setImageResource(emptyImage);

            switch (place){
                case 1:
                    iv_31.setImageResource(tapImage);
                    break;
                case 2:
                    iv_32.setImageResource(tapImage);
                    break;
                case 3:
                    iv_33.setImageResource(tapImage);
                    break;
            }
        }

        if(row==4){
            iv_41.setImageResource(emptyImage);
            iv_42.setImageResource(emptyImage);
            iv_43.setImageResource(emptyImage);

            switch (place){
                case 1:
                    iv_41.setImageResource(pawInframeImage);
                    break;
                case 2:
                    iv_42.setImageResource(pawInframeImage);
                    break;
                case 3:
                    iv_43.setImageResource(pawInframeImage);
                    break;
            }
        }

        if(row==5){
            iv_51.setImageResource(emptyImage);
            iv_52.setImageResource(emptyImage);
            iv_53.setImageResource(emptyImage);

            switch (place){
                case 1:
                    iv_51.setImageResource(frameImage);
                    break;
                case 2:
                    iv_52.setImageResource(frameImage);
                    break;
                case 3:
                    iv_53.setImageResource(frameImage);
                    break;
            }
        }
    }

    private int millstotime(long mills){
        return (int) mills/1000;
    }

    private void loadImages(){
            frameImage=R.drawable.iv_frame;
            pawInframeImage=R.drawable.iv_fill;
            tapImage=R.drawable.iv_tap;
            emptyImage=R.drawable.iv_empty;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
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
