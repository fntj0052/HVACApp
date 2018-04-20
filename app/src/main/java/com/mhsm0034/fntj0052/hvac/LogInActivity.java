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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LogInActivity extends AppCompatActivity{
    EditText umail,upassw;
    Button uLogIn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        umail = (EditText) findViewById(R.id.email);
        upassw = (EditText) findViewById(R.id.passwrd);
        uLogIn = (Button) findViewById(R.id.buttonLogIn);
    }

    public void logIn(View vw) {
        String email = umail.getText().toString();
        String password = upassw.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, email, password);
        if (!validate()) {
            onLoginFailed();
            return;

        } else {
            uLogIn.setEnabled(false);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onLoginSuccess();
                        }
                    }, 3000);

            Intent intent = new Intent(getApplicationContext(), HomePage.class);
            startActivity(intent);
        }

    }

    public void onLoginSuccess() {
        uLogIn.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login Failed", Toast.LENGTH_SHORT).show();
        umail.setText("");
        upassw.setText("");
        uLogIn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = umail.getText().toString();
        String password = upassw.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            umail.setError("Please enter a valid email address");
            valid = false;
        }
        else {
            umail.setError(null);
        }

        if (password.isEmpty()) {
            upassw.setError("Incorrect Password/Nothing typed in");
            valid = false;
        }
        else if (password.length() <= 1){
            upassw.setError("Password not long enough");
        }
        else {
            upassw.setError(null);
        }

        return valid;
    }

    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        moveTaskToBack(true);
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
}
