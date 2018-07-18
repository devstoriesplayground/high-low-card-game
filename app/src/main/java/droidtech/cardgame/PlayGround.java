package droidtech.cardgame;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonlabs.imageviewpopup.ImagePopup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TooManyListenersException;


public class PlayGround extends AppCompatActivity{
    Context context;
    TextView playerScore, systemScore, count;
    ImageView left,middle,right,imageView1,imageView2;
    Button shuffle, newgame;
    List<Integer> cards;
    List<Integer> image;

    Random r;

    Integer[] images= {
            R.drawable.ic_club_1,
            R.drawable.ic_club_2,
            R.drawable.ic_club_3,
            R.drawable.ic_club_4,
            R.drawable.ic_club_5,
            R.drawable.ic_club_6,
            R.drawable.ic_club_7,
            R.drawable.ic_club_8,
            R.drawable.ic_club_9,
            R.drawable.ic_club_10,
            R.drawable.ic_club_king,
            R.drawable.ic_club_queen,
            R.drawable.ic_club_jack,
            R.drawable.ic_flower_1,
            R.drawable.ic_flower_2,
            R.drawable.ic_flower_3,
            R.drawable.ic_flower_4,
            R.drawable.ic_flower_5,
            R.drawable.ic_flower_6,
            R.drawable.ic_flower_7,
            R.drawable.ic_flower_8,
            R.drawable.ic_flower_9,
            R.drawable.ic_flower_10,
            R.drawable.ic_flower_king,
            R.drawable.ic_flower_queen,
            R.drawable.ic_flower_jack,
            R.drawable.ic_heart_1,
            R.drawable.ic_heart_2,
            R.drawable.ic_heart_3,
            R.drawable.ic_heart_4,
            R.drawable.ic_heart_5,
            R.drawable.ic_heart_6,
            R.drawable.ic_heart_7,
            R.drawable.ic_heart_8,
            R.drawable.ic_heart_9,
            R.drawable.ic_heart_10,
            R.drawable.ic_heart_king,
            R.drawable.ic_heart_queen,
            R.drawable.ic_heart_jack,
            R.drawable.ic_diamond_1,
            R.drawable.ic_diamond_2,
            R.drawable.ic_diamond_3,
            R.drawable.ic_diamond_4,
            R.drawable.ic_diamond_5,
            R.drawable.ic_diamond_6,
            R.drawable.ic_diamond_7,
            R.drawable.ic_diamond_8,
            R.drawable.ic_diamond_9,
            R.drawable.ic_diamond_10,
            R.drawable.ic_diamond_king,
            R.drawable.ic_diamond_queen,
            R.drawable.ic_diamond_jack
    };

    int pickImage = 0 ,pickImage1=0, lastPick=0, checkImage1=0, checkImage2=0, playScore=0 , sysScore=0, roundCount=0;

    private Animation animation1;

    private Animation animation2;

    private boolean isBackOfCardShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ground);

        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
        }

        cards = new ArrayList<>();
        cards.add(107);
        cards.add(207);
        cards.add(307);


        left = findViewById(R.id.left);
        middle = findViewById(R.id.middle);
        right = findViewById(R.id.right);


        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        playerScore = (TextView) findViewById(R.id.textView1);
        systemScore = (TextView) findViewById(R.id.textView2);
        count = (TextView) findViewById(R.id.roundCount);


        animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);



        shuffle = (Button) findViewById(R.id.shuffle);
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Collections.shuffle(cards);

                left.setImageResource(R.drawable.ic_club_back);
                middle.setImageResource(R.drawable.ic_club_back);
                right.setImageResource(R.drawable.ic_club_back);

                Animation anim_left = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.left);
                Animation anim_middle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);
                Animation anim_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.middle);

                left.startAnimation(anim_left);
                middle.startAnimation(anim_middle);
                right.startAnimation(anim_right);

            }
        });

        newgame = (Button) findViewById(R.id.newgame);
        newgame.setEnabled(true);
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = new Random();

               if ((imageView1.getVisibility() == View.GONE) && (imageView2.getVisibility() == View.GONE)){

                        imageView1.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.VISIBLE);

                }else if ((imageView1.getVisibility() == View.VISIBLE) && (imageView2.getVisibility() == View.VISIBLE)){

                         imageView1.setImageResource(R.drawable.ic_club_back);
                         imageView2.setImageResource(R.drawable.ic_club_back);

                }

                do {

                    pickImage = r.nextInt(images.length);
                    pickImage1 = r.nextInt(images.length);

                } while (pickImage == lastPick);

                lastPick = pickImage;


                if ((pickImage == 0) || (pickImage == 13 ) || (pickImage == 26 ) || (pickImage == 39)){ checkImage1 = 1; }
                if ((pickImage1 == 0) || (pickImage1 == 13 ) || (pickImage1 == 26 ) || (pickImage1 == 39)){ checkImage2 = 1; }

                if ((pickImage == 1) || (pickImage == 14 ) || (pickImage == 27 ) || (pickImage == 40) ){ checkImage1 = 2; }
                if ((pickImage1 == 1) || (pickImage1 == 14 ) || (pickImage1 == 27 ) || (pickImage1 == 40)){ checkImage2 = 2; }

                if ((pickImage == 2) || (pickImage == 15 ) || (pickImage == 28 ) || (pickImage == 41) ){ checkImage1 = 3; }
                if ((pickImage1 == 2) || (pickImage1 == 15 ) || (pickImage1 == 28 || (pickImage1 == 41))){ checkImage2 = 3; }

                if ((pickImage == 3) || (pickImage == 16 ) || (pickImage == 29 ) || (pickImage == 42)){ checkImage1 = 4; }
                if ((pickImage1 == 3) || (pickImage1 == 16 ) || (pickImage1 == 29 ) || (pickImage1 == 42)){ checkImage2 = 4; }

                if ((pickImage == 4) || (pickImage == 17 ) || (pickImage == 30 ) || (pickImage == 43)){ checkImage1 = 5; }
                if ((pickImage1 == 4) || (pickImage1 == 17 ) || (pickImage1 == 30 ) || (pickImage1 == 43)){ checkImage2 = 5; }

                if ((pickImage == 5) || (pickImage == 18 ) || (pickImage == 31 ) || (pickImage == 44)){ checkImage1 = 6; }
                if ((pickImage1 == 5) || (pickImage1 == 18 ) || (pickImage1 == 31 ) || (pickImage1 == 44)){ checkImage2 = 6; }

                if ((pickImage == 6) || (pickImage == 19 ) || (pickImage == 32 ) || (pickImage == 45)){ checkImage1 = 7; }
                if ((pickImage1 == 6) || (pickImage1 == 19 ) || (pickImage1 == 32 ) || (pickImage1 == 45)){ checkImage2 = 7; }

                if ((pickImage == 7) || (pickImage == 20 ) || (pickImage == 33 ) || (pickImage == 46)){ checkImage1 = 8; }
                if ((pickImage1 == 7) || (pickImage1 == 20 ) || (pickImage1 == 33 ) || (pickImage1 == 46)){ checkImage2 = 8; }

                if ((pickImage == 8) || (pickImage == 21 ) || (pickImage == 34 ) || (pickImage == 47)){ checkImage1 = 9; }
                if ((pickImage1 == 8) || (pickImage1 == 21 ) || (pickImage1 == 34 ) || (pickImage1 == 47)){ checkImage2 = 9; }

                if ((pickImage == 9) || (pickImage == 22 ) || (pickImage == 35 ) || (pickImage == 48)){ checkImage1 = 10; }
                if ((pickImage1 == 9) || (pickImage1 == 22 ) || (pickImage1 == 35 ) || (pickImage1 == 48)){ checkImage2 = 10; }

                if ((pickImage == 10) || (pickImage == 23 ) || (pickImage == 36 ) || (pickImage == 49)){ checkImage1 = 10; }
                if ((pickImage1 == 10) || (pickImage1 == 23 ) || (pickImage1 == 36 ) || (pickImage1 == 49)){ checkImage2 = 10; }

                if ((pickImage == 11) || (pickImage == 24 ) || (pickImage == 37 ) || (pickImage == 50)){ checkImage1 = 10; }
                if ((pickImage1 == 11) || (pickImage1 == 24 ) || (pickImage1 == 37 ) || (pickImage1 == 50)){ checkImage2 = 10; }

                if ((pickImage == 12) || (pickImage == 25 ) || (pickImage == 38 ) || (pickImage == 51)){ checkImage1 = 10; }
                if ((pickImage1 == 12) || (pickImage1== 25 ) || (pickImage1 == 38 ) || (pickImage1 == 51)){ checkImage2 = 10; }


                if (roundCount == 26){
                    newgame.setEnabled(false);

                    if (playScore > sysScore) {
                        Toast.makeText(PlayGround.this, "YOU WIN!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(PlayGround.this, "YOU LOSE!", Toast.LENGTH_SHORT).show();
                    }

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PlayGround.this);
                    alertDialogBuilder.setTitle("Card Game");
                    alertDialogBuilder.setMessage("Do you want to play again?");

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dialog.dismiss();
                            //System.exit(0);
                            Intent intent = new Intent(PlayGround.this, PlayGround.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("Exit me", true);
                            startActivity(intent);
                            finish();
                        }
                    });

                    alertDialogBuilder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    newgame.setEnabled(true);
                                    count.setText("0");
                                    playerScore.setText("0");
                                    systemScore.setText("0");
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView1.clearAnimation();
                imageView1.setAnimation(animation1);
                imageView1.startAnimation(animation1);

                imageView2.clearAnimation();
                imageView2.setAnimation(animation1);
                imageView2.startAnimation(animation1);


                if (checkImage1 > checkImage2) {
                    playScore += 1;
                    playerScore.setText(" "+ playScore);
                    Toast.makeText(PlayGround.this,"Player win!" ,Toast.LENGTH_SHORT).show();
                } else if  (checkImage1 < checkImage2) {
                    sysScore += 1;
                    systemScore.setText(" "+ sysScore);
                    Toast.makeText(PlayGround.this,"System win!",Toast.LENGTH_SHORT).show();
                }
                /*else if  (checkImage2 > checkImage1) {
                    sysScore += 1;
                    systemScore.setText(" "+ sysScore);
                    Toast.makeText(PlayGround.this,"System win!",Toast.LENGTH_SHORT).show();
                } else if  (checkImage2 < checkImage1) {
                    playScore += 1;
                    playerScore.setText(" "+ playScore);
                    Toast.makeText(PlayGround.this,"Player win!",Toast.LENGTH_SHORT).show();
                }
                */
                else if  (checkImage1 == checkImage2) {
                    Toast.makeText(PlayGround.this,"Draw!",Toast.LENGTH_SHORT).show();
                }

                roundCount = playScore + sysScore;
                count.setText(" "+roundCount);

                if (isBackOfCardShowing) {
                    imageView1.setImageResource(images[pickImage]);
                    imageView2.setImageResource(images[pickImage1]);
                }

                imageView1.clearAnimation();
                imageView1.setAnimation(animation2);
                imageView1.startAnimation(animation2);

                imageView2.clearAnimation();
                imageView2.setAnimation(animation2);
                imageView2.startAnimation(animation2);

            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView1.clearAnimation();
                imageView1.setAnimation(animation1);
                imageView1.startAnimation(animation1);

                imageView2.clearAnimation();
                imageView2.setAnimation(animation1);
                imageView2.startAnimation(animation1);


                if (checkImage1 > checkImage2) {
                    playScore += 1;
                    playerScore.setText(" "+ playScore);
                    Toast.makeText(PlayGround.this,"Player win! " ,Toast.LENGTH_SHORT).show();
                } else if  (checkImage1 < checkImage2) {
                    sysScore += 1;
                    systemScore.setText(" "+ sysScore);
                    Toast.makeText(PlayGround.this,"System win!" ,Toast.LENGTH_SHORT).show();
                }
                /*else if  (checkImage2 > checkImage1) {
                    sysScore += 1;
                    systemScore.setText(" "+ sysScore);
                    Toast.makeText(PlayGround.this,"System win!",Toast.LENGTH_SHORT).show();
                } else if  (checkImage2 < checkImage1) {
                    playScore += 1;
                    playerScore.setText(" "+ playScore);
                    Toast.makeText(PlayGround.this,"Player win!",Toast.LENGTH_SHORT).show();
                }
                */
                else if  (checkImage1 == checkImage2) {
                    Toast.makeText(PlayGround.this,"Draw!" ,Toast.LENGTH_SHORT).show();
                }

                roundCount = playScore + sysScore;
                count.setText(" "+roundCount);

                if (isBackOfCardShowing) {
                    imageView1.setImageResource(images[pickImage]);
                    imageView2.setImageResource(images[pickImage1]);
                }

                imageView1.clearAnimation();
                imageView1.setAnimation(animation2);
                imageView1.startAnimation(animation2);

                imageView2.clearAnimation();
                imageView2.setAnimation(animation2);
                imageView2.startAnimation(animation2);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
