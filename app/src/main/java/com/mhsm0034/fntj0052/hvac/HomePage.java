package com.mhsm0034.fntj0052.hvac;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void logreg (View view){
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
    public void stats (View view){
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }
    public void sound (View view){
        Intent intent = new Intent(this, SoundActivity.class);
        startActivity(intent);
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

    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
