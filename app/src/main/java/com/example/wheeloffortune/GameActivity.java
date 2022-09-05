package com.example.wheeloffortune;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private static final String [] sectors = {"10","15","20","10", "15","15","20","5"};
    private static final int [] sectorDegrees = new int[sectors.length];
    private static final Random random = new Random();
    private int degree = 0;
    private boolean isSpinning = false;
    private ImageView wheel;
    private TextView textScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        wheel = findViewById(R.id.imageWheel);
        textScore = findViewById(R.id.textUpScore);

         getDegreesForSectors();
    }

    public void SpinSrart (View view){

        if(!isSpinning) {
            spin();
            isSpinning = true;
        }
    }

    private void getDegreesForSectors(){
        int sectorDegree = 360/sectors.length;

        for(int i = 0; i < sectors.length; i++){
            sectorDegrees[i] = (i+1) * sectorDegree;
        }
    }



    private void spin(){
        degree = random.nextInt(sectors.length -1);

        RotateAnimation rotateAnimation = new RotateAnimation(0, (360 * sectors.length) +
                sectorDegrees[degree], RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String rezult = sectors[sectors.length - (degree + 1)];
                textScore.setText("Your score " + rezult);
                isSpinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        wheel.startAnimation(rotateAnimation);
    }
}