package com.udiary.simplegames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
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

public class Rabbit extends AppCompatActivity {

    ImageView bunny_1,bunny_2,bunny_3,bunny_4,bunny_5,bunny_6,bunny_7,bunny_8,bunny_9;

    TextView tv_left,tv_score;
    Button button;
    Toolbar toolbar;

    int score=0,fps=1000,left=5,tempifleft=0;

    int which=0;
    int whichsave=0;

    AnimationDrawable an;
    SoundPool soundPool;
    private int sound_a;
    boolean sound=true;
    ImageView speaker,help;

    Random r;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit);

        toolbar=(Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("Bunny");
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

                new LovelyStandardDialog(Rabbit.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.splashBg)
                        .setButtonsColorRes(R.color.black)
                        .setIcon(R.drawable.information)
                        .setTitle(R.string.titlerabbit)
                        .setMessage(R.string.meaasgeRabbit)
                        .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // Toast.makeText(context, "positive clicked", Toast.LENGTH_SHORT).show();
                            }
                        })

                        .show();

            }
        });




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool=new SoundPool.Builder().setMaxStreams(5).build();
        }else{

            soundPool=new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }
        sound_a=soundPool.load(this,R.raw.popup,1);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.left_arrow));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Rabbit.this, MainActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                sound=false;
                finish(); // Call once you redirect to another activity

            }
        });
        r=new Random();

        button = (Button) findViewById(R.id.button);

        tv_left=(TextView) findViewById(R.id.tv_left);
        tv_score=(TextView) findViewById(R.id.tv_score);

        bunny_1=(ImageView) findViewById(R.id.bunny_1);
        bunny_2=(ImageView) findViewById(R.id.bunny_2);
        bunny_3=(ImageView) findViewById(R.id.bunny_3);
        bunny_4=(ImageView) findViewById(R.id.bunny_4);
        bunny_5=(ImageView) findViewById(R.id.bunny_5);
        bunny_6=(ImageView) findViewById(R.id.bunny_6);
        bunny_7=(ImageView) findViewById(R.id.bunny_7);
        bunny_8=(ImageView) findViewById(R.id.bunny_8);
        bunny_9=(ImageView) findViewById(R.id.bunny_9);

        bunny_1.setVisibility(View.INVISIBLE);
        bunny_2.setVisibility(View.INVISIBLE);
        bunny_3.setVisibility(View.INVISIBLE);
        bunny_4.setVisibility(View.INVISIBLE);
        bunny_5.setVisibility(View.INVISIBLE);
        bunny_6.setVisibility(View.INVISIBLE);
        bunny_7.setVisibility(View.INVISIBLE);
        bunny_8.setVisibility(View.INVISIBLE);
        bunny_9.setVisibility(View.INVISIBLE);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left=5;
                tv_left.setText("LIFE :"+left);
                score=0;
                tv_score.setText("SCORE :"+score);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        theGameActions();
                    }
                },1000);

                button.setEnabled(false);
            }
        });

        bunny_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tempifleft=1;
                bunny_1.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_1.setEnabled(true);
            }
        });

        bunny_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_2.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_2.setEnabled(true);

            }
        });



        bunny_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_3.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_3.setEnabled(true);

            }
        });

        bunny_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_4.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_4.setEnabled(true);

            }
        });

        bunny_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_5.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_5.setEnabled(true);

            }
        });

        bunny_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_6.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_6.setEnabled(true);

            }
        });

        bunny_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_7.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_7.setEnabled(true);

            }
        });

        bunny_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_8.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_8.setEnabled(true);

            }
        });

        bunny_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempifleft=1;
                bunny_9.setImageResource(R.drawable.last);
                score=score+1;
                tv_score.setText("SCORE :"+score);
                bunny_9.setEnabled(true);

            }
        });
    }

    private void theGameActions(){
        if(score<10){
            fps=1000;
        }else if(score >=10 && score<15){
            fps=950;
        }else if(score >=15 && score<20){
            fps=900;
        }else if(score >=20 && score<25){
            fps=850;
        }else if(score >=25 && score<30){
            fps=800;
        }else if(score >=30 && score<35){
            fps=750;
        }else if(score >=35 && score<40){
            fps=700;
        }else if(score >=40 && score<45){
            fps=650;
        }else if(score >=45 && score<50){
            fps=600;
        }else if(score >=50 && score<55){
            fps=550;
        }else if(score >=55 && score<60){
            fps=500;
        }else if(score >=60 && score<65){
            fps=450;
        }else if(score >=65 && score<70){
            fps=400;
        }else if(score >=70){
            fps=350;
        }

        an=(AnimationDrawable) ContextCompat.getDrawable(this,R.drawable.anim);

        do{
            which=r.nextInt(9)+1;

        }while (whichsave==which);

        whichsave=which;

        if(which==1){
            bunny_1.setImageDrawable(an);
            bunny_1.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }

            bunny_1.setEnabled(true);
        }else if(which==2){
            bunny_2.setImageDrawable(an);
            bunny_2.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_2.setEnabled(true);
        }else if(which==3){
            bunny_3.setImageDrawable(an);
            bunny_3.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_3.setEnabled(true);
        }else if(which==4){
            bunny_4.setImageDrawable(an);
            bunny_4.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_4.setEnabled(true);
        }else if(which==5){
            bunny_5.setImageDrawable(an);
            bunny_5.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_5.setEnabled(true);
        }else if(which==6){
            bunny_6.setImageDrawable(an);
            bunny_6.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_6.setEnabled(true);
        }else if(which==7){
            bunny_7.setImageDrawable(an);
            bunny_7.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_7.setEnabled(true);
        }else if(which==8){
            bunny_8.setImageDrawable(an);
            bunny_8.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_8.setEnabled(true);
        }else if(which==9){
            bunny_9.setImageDrawable(an);
            bunny_9.setVisibility(View.VISIBLE);
            if(sound==true){
                soundPool.play(sound_a,1,1,0,0,1);
            }
            bunny_9.setEnabled(true);
        }

        an.start();

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                bunny_1.setVisibility(View.INVISIBLE);
                bunny_2.setVisibility(View.INVISIBLE);
                bunny_3.setVisibility(View.INVISIBLE);
                bunny_4.setVisibility(View.INVISIBLE);
                bunny_5.setVisibility(View.INVISIBLE);
                bunny_6.setVisibility(View.INVISIBLE);
                bunny_7.setVisibility(View.INVISIBLE);
                bunny_8.setVisibility(View.INVISIBLE);
                bunny_9.setVisibility(View.INVISIBLE);

                bunny_1.setEnabled(false);
                bunny_2.setEnabled(false);
                bunny_3.setEnabled(false);
                bunny_4.setEnabled(false);
                bunny_5.setEnabled(false);
                bunny_6.setEnabled(false);
                bunny_7.setEnabled(false);
                bunny_8.setEnabled(false);
                bunny_9.setEnabled(false);


                if(tempifleft==0){
                    left=left-1;
                    tv_left.setText("LIFE : "+left);

                }else if(tempifleft==1){
                    tempifleft=0;
                }


                if(left==0){
                    Toast.makeText(Rabbit.this,"GAME OVER",Toast.LENGTH_SHORT).show();
                    button.setEnabled(true);
                }else if(left>0){
                    theGameActions();
                }
            }
        },fps);
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
