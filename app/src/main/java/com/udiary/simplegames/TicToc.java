package com.udiary.simplegames;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
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

public class TicToc extends AppCompatActivity {


    private Button[][] buttons=new Button[3][3];

    private boolean player1Turn=true;

    private int roundcount;

    private int player1point;
    private int player2point;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    Toolbar toolbar;
    private SoundPool soundPool;
    private int sound_a,sound_b,sound_c,sound_d,sound_e,sound_f,sound_g,sound_h;
    boolean sound=true;
    ImageView speaker,help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_toc);

        toolbar=(Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("Tic Toc");
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

                new LovelyStandardDialog(TicToc.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.splashBg)
                        .setButtonsColorRes(R.color.black)
                        .setIcon(R.drawable.information)
                        .setTitle(R.string.titlerabbit)
                        .setMessage(R.string.meaasgeTicToc)
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
                Intent intent=new Intent(TicToc.this,MainActivity.class);
                startActivity(intent);
            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool=new SoundPool.Builder().setMaxStreams(5).build();
        }else{

            soundPool=new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        }

        sound_a=soundPool.load(this,R.raw.popup,1);
        sound_b=soundPool.load(this,R.raw.zen1,1);

        textViewPlayer1= (TextView) findViewById(R.id.textview_p1);
        textViewPlayer2= (TextView) findViewById(R.id.textview_p2);

        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;

                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!((Button) view).getText().toString().equals("")){
                            return;
                        }

                        if(player1Turn){
                            ((Button) view).setText("X");
                            if(sound==true){
                                soundPool.play(sound_a,1,1,0,0,1);
                            }

                        }else{
                            ((Button) view).setText("O");
                            if(sound==true){
                                soundPool.play(sound_b,1,1,0,0,1);
                            }
                        }

                        roundcount++;

                        if(checkForWin()){
                            if(player1Turn){
                                player1Wins();
                            }else{
                                player2Wins();
                            }
                        }else if(roundcount==9){
                            draw();
                        }else{
                            player1Turn=!player1Turn;
                        }
                    }
                });

            }
        }


        Button buttonReset= (Button) findViewById(R.id.Button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    private boolean checkForWin(){

        String[][] field=new String[3][3];

        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j]=buttons[i][j].getText().toString();
            }
        }

        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])&& field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])&& field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }




        if(field[0][0].equals(field[1][1])&& field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }




        if(field[0][2].equals(field[1][1])&& field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }


        return false;
    }


    private void updatePointText() {
        textViewPlayer1.setText("Player 1 :"+player1point);
        textViewPlayer2.setText("Player 2 :"+player2point);
    }


    private void resetBoard() {

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }

        roundcount=0;
        player1Turn=true;

    }


    private void resetGame(){
        player1point=0;
        player2point=0;
        resetBoard();
        updatePointText();
    }

    private void draw() {

        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();

        resetBoard();
    }

    private void player1Wins() {

        player1point++;
        Toast.makeText(this,"player 1 wins",Toast.LENGTH_SHORT).show();
        updatePointText();
        resetBoard();
    }




    private void player2Wins() {

        player2point++;
        Toast.makeText(this,"player 2 wins",Toast.LENGTH_SHORT).show();
        updatePointText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundcount",roundcount);
        outState.putInt("player1point",player1point);
        outState.putInt("player2point",player2point);
        outState.putBoolean("player1Turn",player1Turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundcount=savedInstanceState.getInt("roundcount");
        player1point=savedInstanceState.getInt("player1point");
        player2point=savedInstanceState.getInt("player2point");
        player1Turn=savedInstanceState.getBoolean("player1Turn");
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
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
