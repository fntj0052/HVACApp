package com.mhsm0034.fntj0052.hvac;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class StatsActivity extends Activity{

    private String EVENT_DATE_TIME = "2018-04-27 12:30:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    LinearLayout linear_layout_1, linear_layout_2;
    TextView tv_days, tv_hour, tv_minute, tv_second,tv_temp;
    private Handler handler = new Handler();
    private Runnable runnable;

    String JSON_STRING;

    Button rst, srt;

    private class BackgroundTask extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            json_url = "http://justlikerav.com/hvac/temp.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                StringBuilder stringBuilder = new StringBuilder();
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((JSON_STRING = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }
                return stringBuilder.toString().trim();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            tv_temp.setText(result);
            JSON_STRING = result;
        }

    }
    public void parseJSON (View view){
        if(JSON_STRING == null){
            Toast.makeText(getApplicationContext(),"First get JSON",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent4 = new Intent(this,DisplayListView2.class);
            intent4.putExtra("json_data",JSON_STRING);
            startActivity(intent4);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        initUI();
        countDownStart();

        rst =(Button)findViewById(R.id.buttonReset);
        tv_temp=(TextView)findViewById(R.id.tv_Temp);


        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_temp.setText("");
                tv_days.setText("");
                tv_hour.setText("");
                tv_minute.setText("");
                tv_second.setText("");
            }
        });

    }
    private void initUI() {
        linear_layout_1 = (LinearLayout)findViewById(R.id.linear_layout_1);
        linear_layout_2 = (LinearLayout)findViewById(R.id.linear_layout_2);
        tv_days = (TextView)findViewById(R.id.tv_days);
        tv_hour = (TextView)findViewById(R.id.tv_hour);
        tv_minute = (TextView)findViewById(R.id.tv_minute);
        tv_second = (TextView)findViewById(R.id.tv_second);
    }

    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        //
                        tv_days.setText(String.format("%02d", Days));
                        tv_hour.setText(String.format("%02d", Hours));
                        tv_minute.setText(String.format("%02d", Minutes));
                        tv_second.setText(String.format("%02d", Seconds));
                    } else {
                        linear_layout_1.setVisibility(View.VISIBLE);
                        linear_layout_2.setVisibility(View.GONE);
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(this, HomePage.class);
                startActivity(intent);
                return true;
            case R.id.login:
                Intent intent1 = new Intent(this, LogInActivity.class);
                startActivity(intent1);
                return true;
            case R.id.register:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                startActivity(intent2);
                return true;
            case R.id.status:
                Intent intent3 = new Intent(this, StatsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.sound:
                Intent intent4 = new Intent(this, SoundActivity.class);
                startActivity(intent4);
                return true;
            case R.id.about:
                String url ="https://cenghvac.wordpress.com/about-us/";
                Intent intent5 = new Intent(Intent.ACTION_VIEW);
                intent5.setData(Uri.parse(url));
                startActivity(intent5);
                return true;
            case R.id.logout:
                Intent loginscreen=new Intent(this,Activity.class);
                loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginscreen);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getJSON (View view){
        new StatsActivity.BackgroundTask().execute();
    }


    public void sound(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

