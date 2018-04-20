package com.mhsm0034.fntj0052.hvac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String JSON_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ResultAdapter resultAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = (ListView)findViewById(R.id.listview);
        resultAdapter = new ResultAdapter(this,R.layout.row_layout);
        listView.setAdapter(resultAdapter);

        JSON_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(JSON_string);
            jsonArray = jsonObject.getJSONArray("hvac_response");

            int count = 0;
            String id,sound,timestamp;

            while (count <jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                id = JO.getString("id");
                sound = JO.getString("sound");
                if(sound != "0")
                {
                    sound = "Normal sound level.";
                }
                else
                {
                    sound = "High sound level! Check system!";
                }
                timestamp = JO.getString("timestamp");

                Results results = new Results(id, sound, timestamp);
                resultAdapter.add(results);

                count++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void back (View view){
        Intent intent = new Intent(this, SoundActivity.class);
        startActivity(intent);
    }
}
