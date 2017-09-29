package com.clarifai.android.starter.api.v2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.clarifai.android.starter.api.v2.R;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class shopping extends AppCompatActivity {
TextView Objects,name1,address1,name2, address2, name3, address3,name4,address4,name5,address5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Objects = (TextView)findViewById(R.id.objects);
        name1 = (TextView)findViewById(R.id.name1);
        name2 = (TextView)findViewById(R.id.name2);
        name3 = (TextView)findViewById(R.id.name3);
        name4 = (TextView)findViewById(R.id.name4);
        name5 = (TextView)findViewById(R.id.name5);
        address1 = (TextView)findViewById(R.id.address1);
        address2 = (TextView)findViewById(R.id.address2);
        address3 = (TextView)findViewById(R.id.address3);
        address4 = (TextView)findViewById(R.id.address4);
        address5 = (TextView)findViewById(R.id.address5);
        Intent intent = getIntent();
        String name  = intent.getStringExtra("Object");
        Objects.setText(name + " stores near me :");
        RequestQueue requestQueue = newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.foursquare.com/v2/venues/search?near=kansas city&query="+name+"&oauth_token=ZCXRQXJKGEQ050PRHIIDVWNAIN1OD50YRDLNPO0OUJQYPJQH&v=20170928",null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject obj = response.getJSONObject("response");
                    String names1 = obj.getJSONArray("venues").getJSONObject(2).getString("name");
                    JSONObject loc = obj.getJSONArray("venues").getJSONObject(2).getJSONObject("location");
                    String adrs1 = loc.getString("city")+","+loc.getString("state")+","+loc.getString("postalCode");
                    String names2 = obj.getJSONArray("venues").getJSONObject(3).getString("name");
                    JSONObject loc2 = obj.getJSONArray("venues").getJSONObject(3).getJSONObject("location");
                    String adrs2 = loc2.getString("city")+","+loc2.getString("state")+","+loc2.getString("postalCode");
                    String names3 = obj.getJSONArray("venues").getJSONObject(7).getString("name");
                    JSONObject loc3 = obj.getJSONArray("venues").getJSONObject(7).getJSONObject("location");
                    String adrs3 = loc3.getString("city")+","+loc3.getString("state")+","+loc3.getString("postalCode");
                    String names4 = obj.getJSONArray("venues").getJSONObject(8).getString("name");
                    JSONObject loc4 = obj.getJSONArray("venues").getJSONObject(8).getJSONObject("location");
                    String adrs4 = loc4.getString("city")+","+loc4.getString("state")+","+loc4.getString("postalCode");
                    String names5 = obj.getJSONArray("venues").getJSONObject(9).getString("name");
                    JSONObject loc5 = obj.getJSONArray("venues").getJSONObject(9).getJSONObject("location");
                    String adrs5 = loc5.getString("city")+","+loc5.getString("state")+","+loc5.getString("postalCode");
                    name1.setText(names1);
                    name2.setText(names2);
                    name3.setText(names3);
                    name4.setText(names4);
                    name5.setText(names5);
                    address1.setText(adrs1);
                    address2.setText(adrs2);
                    address3.setText(adrs3);
                    address4.setText(adrs4);
                    address5.setText(adrs5);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
