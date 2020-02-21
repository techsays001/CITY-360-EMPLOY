package com.msg91.sendotp.sample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Popup extends AppCompatActivity {
TextView name,map,applyadate,time,date,details,address;
ImageView image;
Button apply,review;
Intent j;
String a,b,c,d,e,f,g;
SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
j=getIntent();
          name=findViewById(R.id.enam123);
        applyadate=findViewById(R.id.apdate);
        date=findViewById(R.id.dateap);
        time=findViewById(R.id.aptime);
        address=findViewById(R.id.apaddress);
        details=findViewById(R.id.apdetails);

        map=findViewById(R.id.mapss);
          apply=findViewById(R.id.apbt);
         image=findViewById(R.id.img123);
        Picasso.get().load(j.getStringExtra("img")).into(image);
        name.setText(j.getStringExtra("name"));
      //  ph.setText(j.getStringExtra("ph"));
      //  Toast.makeText(Popup.this,j.getStringExtra("job"), Toast.LENGTH_LONG).show();

        sh=getSharedPreferences("data",MODE_PRIVATE);
apply.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/City_360/sms1.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//If we are getting success from server
                      //  Toast.makeText(Popup.this, response, Toast.LENGTH_LONG).show();


                        if(response.equals("OK"))
                        {

                            new SweetAlertDialog(Popup.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText(" Success")
                                    .setContentText("Back To Home!")
                                    .setConfirmText("Yes")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog
                                                    .setTitleText("Logining...!")

                                                    .setConfirmText("OK")

                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            Intent in=new Intent(Popup.this,Homepage.class);
                                                            startActivity(in);
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        }
                                    })
                                    .show();




//
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();



                params.put("add", "Appoiment Confirmid");
                params.put("ph", j.getStringExtra("job"));
                params.put("eph", sh.getString("phone",null));
                params.put("ename", sh.getString("name",null));

//
//returning parameter
                return params;
            }

        };

// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Popup.this);
        requestQueue.add(stringRequest);



    }
});
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+f+","+g));
                startActivity(intent);



            }
        });


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://androidprojectstechsays.000webhostapp.com/City_360/viewworkmore.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json_obj = jsonArray.getJSONObject(i);
//ba = json_obj.getString("balance");
                                a=json_obj.getString("date");
                                b=json_obj.getString("time");
                                c=json_obj.getString("detail");
                                d=json_obj.getString("address");
                                e=json_obj.getString("applydate");
                                f=json_obj.getString("la");
                                g=json_obj.getString("lo");




                            }
//Toast.makeText(Recharge.this, "your new balnce is "+ba, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(Signin.this, "success", Toast.LENGTH_LONG).show();
                        if (response.contains("success")) {


applyadate.setText(e);
time.setText(b);
date.setText(a);
details.setText(c);
address.setText(d);









                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//Adding parameters to request
                params.put("ph", j.getStringExtra("job"));


//returning parameter
                return params;
            }

        };
// m = Integer.parseInt(ba) - Integer.parseInt(result.getContents());
// balance.setText(m+"");


//Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(Popup.this);
        requestQueue.add(stringRequest);














    }
}
