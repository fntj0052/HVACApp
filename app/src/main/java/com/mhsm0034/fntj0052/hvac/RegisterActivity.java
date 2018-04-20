package com.mhsm0034.fntj0052.hvac;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText cname, cmail, cpass, ccpass;
    private String name, email, password, cpassword;
    Button csubmit;

    String ServerURL = "http://justlikerav.com/hvac/emailtransfer.php";
    String TempEmail, TempPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cname = (EditText)findViewById(R.id.editNameText);
        cmail = (EditText)findViewById(R.id.editEmailText);
        cpass = (EditText) findViewById(R.id.editPasswordText);
        ccpass = (EditText) findViewById(R.id.editConfrimText);
        csubmit = (Button)findViewById(R.id.buttonConfirm);
    }

    public void confirm(View view) {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Sign-Up Failed", Toast.LENGTH_SHORT).show();
        } else {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            onSignUpSuccess();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            cname.setText("");
                            cmail.setText("");
                            cpass.setText("");
                            ccpass.setText("");
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
        }

    }

    public  void onSignUpSuccess(){
        TempEmail = cmail.getText().toString();
        TempPass = cpass.getText().toString();
        InsertData(TempEmail, TempPass);
    }

    public void InsertData(final String email, final String password){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String EmailHolder = email ;
                String PasswordHolder = password;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("password", PasswordHolder));


                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();

                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(RegisterActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(email, password);
    }

    public boolean validate(){
        boolean valid = true;
        if(name.isEmpty() ||name.length()>32){
            cname.setError("Please Enter Valid Name");
            valid = false;
        }
        if (email.isEmpty() ||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            cmail.setError("Please Enter Valid E-mail");
            valid = false;
        }

        if (password.isEmpty() || password.length() <= 1){
            cpass.setError("Please Enter Password");
            valid = false;
        }
        if (!cpassword.equals(password)) {
            ccpass.setError("Please Re-enter Password");
            valid = false;
        }
        return valid;
    }

    public void initialize(){
        name = cname.getText().toString().trim();
        email = cmail.getText().toString().trim();
        password = cpass.getText().toString().trim();
        cpassword = ccpass.getText().toString().trim();
    }

    public void back(View view){
        Intent intent = new Intent(this, LogInActivity.class);
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
