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
        setContentView(R.layout.activity_main);


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




                if(menuItem.getItemId() == R.id.teilen){

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.app_name));
                    shareIntent.putExtra(Intent.EXTRA_TEXT, getText(R.string.text_fuers_teilen_vor_name) + " " +  getText(R.string.app_name) + " " + getText(R.string.text_fuers_teilen_nach_name) +"\n\n" + getText(R.string.link_zur_app));
                    startActivity(Intent.createChooser(shareIntent,  "Share via..."));
                }



                if (menuItem.getItemId() == R.id.chestmap) {

                    String url = "https://play.google.com/store/apps/details?id=com.pentasounds.fortnitechestmap";

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    onPause();
                    startActivity(intent);
                }

                if (menuItem.getItemId() == R.id.ninjasoundboard) {

                    String url = "https://play.google.com/store/apps/details?id=com.pentasounds.soundboardninja";

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    onPause();
                    startActivity(intent);
                }


                if (menuItem.getItemId() == R.id.mythsoundboard) {

                    String url = "https://play.google.com/store/apps/details?id=com.pentasounds.soundboardtsmmyth";

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    onPause();
                    startActivity(intent);
                }


                if (menuItem.getItemId() == R.id.soundboardapps) {

                    String url = "https://play.google.com/store/apps/developer?id=PentaSounds";

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    onPause();
                    startActivity(intent);
                }

                if (menuItem.getItemId() == R.id.buttonapps) {

                    String url = "https://play.google.com/store/apps/developer?id=PentaButtons";

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    onPause();
                    startActivity(intent);
                }

                if (menuItem.getItemId() == R.id.instagram) {

                    AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                    a_builder.setMessage(R.string.instagram_text)
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String url = "https://www.redirection.lima-city.de/links/instagram.html";

                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(url));
                                    onPause();
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Instagram");
                    alert.show();
                }

//                if (menuItem.getItemId() == R.id.paypaldialog) {
//                    saveToWeb("paypal_dialog");
//
//
//
//                    AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
//                    a_builder.setMessage(R.string.paypal_text)
//                            .setCancelable(true)
//                            .setPositiveButton("Zu PayPal", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    saveToWeb("paypal");
//                                    String url = "https://www.redirection.lima-city.de/links/paypal.html";
//
//                                    Intent intent = new Intent();
//                                    intent.setAction(Intent.ACTION_VIEW);
//                                    intent.setData(Uri.parse(url));
//                                    onPause();
//                                    startActivity(intent);
//                                }
//                            })
//                            .setNegativeButton("Ne, bin gerade Broke", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int i) {
//                                    dialog.cancel();
//                                }
//                            });
//                    AlertDialog alert = a_builder.create();
//                    alert.setTitle("Unterstützung");
//                    alert.show();
//                }



                if (menuItem.getItemId() == R.id.email) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","pentasounds@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_TEXT,"[Packagename:" + getText(R.string.package_name) +  "  ---Don't delete this information---]\n\n\n\n\n (Your Text here)");
                    startActivity(Intent.createChooser(emailIntent, "Send E-Mail..."));
                }


                if (menuItem.getItemId() == R.id.rechtliches) {
                    AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                    a_builder.setMessage(R.string.rechtliches)
                            .setCancelable(true)
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = a_builder.create();
                    alert.setTitle("Impressum");
                    alert.show();
                }




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