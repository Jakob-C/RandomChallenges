package com.jakob.randomchallenges;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button easy, hard, expert;
    TextView playbutton;
    int angle;
    ImageView pointer,wheel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easy=(Button)findViewById(R.id.easy);
        hard=(Button)findViewById(R.id.hard);
        expert=(Button)findViewById(R.id.expert);

        wheel=(ImageView)findViewById(R.id.wheel);

        playbutton=(TextView)findViewById(R.id.playbutton);
        Typeface burbank = Typeface.createFromAsset(getApplication().getAssets(), "burbank.otf");
        playbutton.setTypeface(burbank);

        final Handler handler = new Handler();

        pointer=(ImageView)findViewById(R.id.arrow);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacksAndMessages(null);

                playbutton.animate().alpha(0f).setDuration(400);

                Random rand = new Random();
                int zusatzdrehung = rand.nextInt(2000) + 2000;
                int time = rand.nextInt(2000)+4000;

                angle=angle+zusatzdrehung;
                pointer.animate().rotation(angle).setDuration(time);

//                long pos=angle%360;
//                Log.e("###", "Neigung in Grad:   "+pos);
//                final long twelfth = pos/30+1;
//                Log.e("###", "Anzahl der Zwöftel:"+twelfth);
//
//                Log.e("###", "Drehdauer:          "+time);


                // "Spin" Text erscheinung
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        playbutton.animate().alpha(1f).setDuration(400);

                    }
                }, time-400);

            }
        });

    }

    public void easy (View v){
        wheel.setImageResource(R.drawable.greenwheel);
        easy.setAlpha(1f);
        hard.setAlpha(0.2f);
        expert.setAlpha(0.2f);
    }

    public void hard (View v){
        wheel.setImageResource(R.drawable.orangewheel);
        easy.setAlpha(0.2f);
        hard.setAlpha(1f);
        expert.setAlpha(0.2f);
    }

    public void expert (View v){
        wheel.setImageResource(R.drawable.redwheel);
        easy.setAlpha(0.2f);
        hard.setAlpha(0.2f);
        expert.setAlpha(1f);
    }


}