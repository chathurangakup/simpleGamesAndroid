package com.udiary.simplegames;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryChecker extends AppCompatActivity {

    Button b_new;
    Button button11,button12,button13,button21, button22,button23,button31,button32,
            button33,button41,button42,button43;
    List<Integer> buttons;
    int currLevel,currGuess;
    Toolbar toolbar;

    private SoundPool soundPool;
    private int sound_a,sound_b,sound_c,sound_d,sound_e,sound_f,sound_g,sound_h;
    boolean sound=true;
    ImageView speaker,help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_checker);

        toolbar=(Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("Memory");
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

                new LovelyStandardDialog(MemoryChecker.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.splashBg)
                        .setButtonsColorRes(R.color.black)
                        .setIcon(R.drawable.information)
                        .setTitle(R.string.titlerabbit)
                        .setMessage(R.string.meaasgeMemory)
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
                Intent intent=new Intent(MemoryChecker.this,MainActivity.class);
                startActivity(intent);
            }
        });

        b_new=(Button) findViewById(R.id.b_new);

        button11=(Button) findViewById(R.id.btn_11);
        button12=(Button) findViewById(R.id.btn_12);
        button13=(Button) findViewById(R.id.btn_13);
        button21=(Button) findViewById(R.id.btn_21);
        button22=(Button) findViewById(R.id.btn_22);
        button23=(Button) findViewById(R.id.btn_23);
        button31=(Button) findViewById(R.id.btn_31);
        button32=(Button) findViewById(R.id.btn_32);
        button33=(Button) findViewById(R.id.btn_33);
        button41=(Button) findViewById(R.id.btn_41);
        button42=(Button) findViewById(R.id.btn_42);
        button43=(Button) findViewById(R.id.btn_43);

        button11.setTag(1);
        button12.setTag(2);
        button13.setTag(3);
        button21.setTag(4);
        button22.setTag(5);
        button23.setTag(6);
        button31.setTag(7);
        button32.setTag(8);
        button33.setTag(9);
        button41.setTag(10);
        button42.setTag(11);
        button43.setTag(12);

        setDisable();

        buttons=new ArrayList<>();
        buttons.add(1);
        buttons.add(2);
        buttons.add(3);
        buttons.add(4);
        buttons.add(5);
        buttons.add(6);
        buttons.add(7);
        buttons.add(8);
        buttons.add(9);
        buttons.add(10);
        buttons.add(11);
        buttons.add(12);

        b_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_new.setEnabled(false);
                currGuess=0;
                currLevel=1;
                generateButton(currLevel);
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });

        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });

        button41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });
        button43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogic(view);
                ((Button) view).setText("0");
                if(sound==true){
                    soundPool.play(sound_a,1,1,0,0,1);
                }
            }
        });


    }

    private void buttonLogic(View v){
        List<Integer> tempList=new ArrayList<>();
        for(int i=0;i<currLevel;i++){
            tempList.add(buttons.get(i));
            System.out.println(tempList+"qqq");
        }
        System.out.println(v.getTag()+"qqqw");
        if(tempList.contains(v.getTag())){

            currGuess++;
            chechWin();
        }else{
            lostGame();
        }
    }

    private void chechWin(){
        if(currGuess==currLevel){
            setDisable();
            if(currLevel==12){
                Toast.makeText(MemoryChecker.this,"Success",Toast.LENGTH_LONG).show();

                b_new.setEnabled(true);
            }else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       currGuess=0;
                       currLevel++;
                       generateButton(currLevel);

                    }
                },1000);
            }
        }
    }

    private void lostGame(){
        Toast.makeText(MemoryChecker.this,"Fail",Toast.LENGTH_LONG).show();
        setDisable();
        b_new.setEnabled(true);
    }

    private void generateButton(int number){
        button11.setText("");
        button12.setText("");
        button13.setText("");
        button21.setText("");
        button22.setText("");
        button23.setText("");
        button31.setText("");
        button32.setText("");
        button33.setText("");
        button41.setText("");
        button42.setText("");
        button43.setText("");

        Collections.shuffle(buttons);

        for(int i=0;i<number;i++){
            showButton(buttons.get(i));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                button11.setText("");
                button12.setText("");
                button13.setText("");
                button21.setText("");
                button22.setText("");
                button23.setText("");
                button31.setText("");
                button32.setText("");
                button33.setText("");
                button41.setText("");
                button42.setText("");
                button43.setText("");

                setenable();
            }
        },1000);
    }

    private void showButton(int number){
        switch (number){
            case 1:
                button11.setText("0");
                break;
            case 2:
                button12.setText("0");
                break;
            case 3:
                button13.setText("0");
                break;
            case 4:
                button21.setText("0");
                break;
            case 5:
                button22.setText("0");
                break;
            case 6:
                button23.setText("0");
                break;
            case 7:
                button31.setText("0");
                break;
            case 8:
                button32.setText("0");
                break;
            case 9:
                button33.setText("0");
                break;
            case 10:
                button41.setText("0");
                break;
            case 11:
                button42.setText("0");
                break;
            case 12:
                button43.setText("0");
                break;
        }
    }

    private void setenable(){
        button11.setEnabled(true);
        button12.setEnabled(true);
        button13.setEnabled(true);
        button21.setEnabled(true);
        button22.setEnabled(true);
        button23.setEnabled(true);
        button31.setEnabled(true);
        button32.setEnabled(true);
        button33.setEnabled(true);
        button41.setEnabled(true);
        button42.setEnabled(true);
        button43.setEnabled(true);
    }
    private void setDisable(){
        button11.setEnabled(false);
        button12.setEnabled(false);
        button13.setEnabled(false);
        button21.setEnabled(false);
        button22.setEnabled(false);
        button23.setEnabled(false);
        button31.setEnabled(false);
        button32.setEnabled(false);
        button33.setEnabled(false);
        button41.setEnabled(false);
        button42.setEnabled(false);
        button43.setEnabled(false);
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
