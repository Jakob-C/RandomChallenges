package com.jakob.randomchallenges;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button easy, hard, expert;
    TextView playbutton;
    int angle;
    ImageView pointer, greenwheel, orangewheel, redwheel;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        easy=(Button)findViewById(R.id.easy);
        hard=(Button)findViewById(R.id.hard);
        expert=(Button)findViewById(R.id.expert);

        greenwheel=(ImageView)findViewById(R.id.greenwheel);
        orangewheel=(ImageView)findViewById(R.id.orangewheel);
        redwheel=(ImageView)findViewById(R.id.redwheel);

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


        mNavigationView.setItemIconTintList(null);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();




                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();


    }

    public void easy (View v){
        greenwheel.setVisibility(View.VISIBLE);
        orangewheel.setVisibility(View.INVISIBLE);
        redwheel.setVisibility(View.INVISIBLE);
        easy.setAlpha(1f);
        hard.setAlpha(0.2f);
        expert.setAlpha(0.2f);
    }

    public void hard (View v){
        orangewheel.setVisibility(View.VISIBLE);
        greenwheel.setVisibility(View.INVISIBLE);
        redwheel.setVisibility(View.INVISIBLE);
        easy.setAlpha(0.2f);
        hard.setAlpha(1f);
        expert.setAlpha(0.2f);
    }

    public void expert (View v){
        redwheel.setVisibility(View.VISIBLE);
        greenwheel.setVisibility(View.INVISIBLE);
        orangewheel.setVisibility(View.INVISIBLE);
        easy.setAlpha(0.2f);
        hard.setAlpha(0.2f);
        expert.setAlpha(1f);
    }



}