package com.example.marty.customizer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    Button intentBttn = null;
    Button intentBttn1 = null;
    Button intentBttn2 = null;
    SeekBar blendBar = null;

    int counter = 0;
    int finalRed1 = 0;
    int finalGreen1 =0;
    int finalBlue1 =0;
    int finalRed2 = 0;
    int finalGreen2 =0;
    int finalBlue2 =0;



    static final int RESULT_OK = 1;
    ImageView hairdo = null;
    ImageView hairdo1 = null;
    public final static String COLOR_APP = "android.intent.action.COLOR";


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData){
        super.onActivityResult(requestCode,resultCode,resultData);

        hairdo = (ImageView) findViewById(R.id.trumpHair);
        Bundle colors = resultData.getExtras();
        Integer red = colors.getInt("redValue");
        Integer green = colors.getInt("greenValue");
        Integer blue = colors.getInt("blueValue");


        finalRed1 = red;
        finalGreen1 = green;
        finalBlue1 = blue;
        hairdo.setBackgroundColor(Color.rgb(red, green, blue));

        if (counter == 1) {
            Integer red1 = colors.getInt("redValue");
            Integer green1 = colors.getInt("greenValue");
            Integer blue1 = colors.getInt("blueValue");
            finalRed2 = red;
            finalGreen2 = green;
            finalBlue2 = blue;
            counter = 0;
            hairdo.setBackgroundColor(Color.rgb(red1, green1, blue1));
        }

        counter++;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intentBttn = (Button)findViewById(R.id.intentButton);
        intentBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.marty.COLOR");
                startActivityForResult(intent, RESULT_OK);

            }
        });
        intentBttn1 = (Button)findViewById(R.id.intentButton1);
        intentBttn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.marty.COLOR");
                startActivityForResult(intent, RESULT_OK);

            }
            });



        //need final RGBs for the first and second, denoted as finalRed1, finalRed2, finalGreen1, etc -
        //can't seem to pass the values in as parameters in any of these methods
        //Once those vals get passed in, this should work as long as there are 2 colors picked
        blendBar = (SeekBar) findViewById(R.id.blendBar);
        blendBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                float ratio = progress/100;
                float blendRed = color - (finalRed1 -finalRed2) *ratio;
                float blendGreen = finalGreen1 - (finalGreen1 - finalGreen2) * ratio;
                float blendBlue = finalBlue1 - (finalBlue1 - finalBlue2) * ratio;
                findViewById(R.id.trumpHair).setBackgroundColor(Color.rgb((int) blendRed,(int) blendGreen, (int) blendBlue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
