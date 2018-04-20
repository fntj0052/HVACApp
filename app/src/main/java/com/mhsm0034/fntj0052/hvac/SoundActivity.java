package com.mhsm0034.fntj0052.hvac;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SoundActivity extends AppCompatActivity  {

    String JSON_STRING;

    private class BackgroundTask extends AsyncTask<Void,Void,String> {
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url = "http://justlikerav.com/hvac/sound.php";
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
                    stringBuilder.append(JSON_STRING).append("\n");
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
            TextView tv_sound =(TextView)findViewById(R.id.tv_TextSound);
            tv_sound.setText(result);
            JSON_STRING = result;
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
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
        new BackgroundTask().execute();
    }

    public void parseJSON (View view){
        if(JSON_STRING == null){
            Toast.makeText(getApplicationContext(),"First get JSON",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent3 = new Intent(this,DisplayListView.class);
            intent3.putExtra("json_data",JSON_STRING);
            startActivity(intent3);
        }
    }

    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void back(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
