package com.mhsm0034.fntj0052.hvac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView2 extends AppCompatActivity {
    String JSON_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ResultAdapter2 resultAdapter2;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview2_layout);
        listView = (ListView) findViewById(R.id.listview2);
        resultAdapter2 = new ResultAdapter2(this,R.layout.row_layout2);
        listView.setAdapter(resultAdapter2);
        JSON_string = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(JSON_string);
            jsonArray = jsonObject.getJSONArray("hvac_response");

            int count = 0;
            String id,temp,timestamp;

            while(count<jsonArray.length()){

                JSONObject JO = jsonArray.getJSONObject(count);
                id = JO.getString("id");
                temp = JO.getString("temp");
                timestamp = JO.getString("timestamp");

                Results2 results2 = new Results2(id,temp,timestamp);
                resultAdapter2.add(results2);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void back (View view){
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }
}
