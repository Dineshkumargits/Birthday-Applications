package com.example.adkdinesh.ak;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Content extends AppCompatActivity {

    private ProgressDialog pd;

    TextView tview;

    ImageView imageView;

    List<String> content_list;

    private static String S_URL= "http://10.42.0.1/AK/php/content_retrieve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        String newString;

        content_list=new ArrayList<>();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("names");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("names");
        }

        tview=(TextView)findViewById(R.id.tx);
        tview.setText("Many More Happy Returns of the Day Elambirai by "+newString.toUpperCase());

        request(newString);



    }

    private void request(final String name){
//        pd.setMessage("Signing Up . . .");
//        pd.show();
        RequestQueue queue = Volley.newRequestQueue(Content.this);
        String response = null;
        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
//                        pd.hide();
                        //Response
                        try {
                            JSONObject obj=new JSONObject(response);

                            content_list.add(obj.getString("q1"));
                            content_list.add(obj.getString("q2"));
                            content_list.add(obj.getString("q3"));
                            content_list.add(obj.getString("q4"));
                            content_list.add(obj.getString("q5"));
                            content_list.add(obj.getString("q6"));
                            content_list.add(obj.getString("q7"));
                            content_list.add(obj.getString("q8"));
                            content_list.add(obj.getString("q9"));
                            content_list.add(obj.getString("q10"));
                            content_list.add(obj.getString("description"));
                            content_list.add(obj.getString("image"));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Content.this, content_list.get(10), Toast.LENGTH_SHORT).show();
                                    imageView=findViewById(R.id.imageView);
                                    Picasso.get().load("http://10.42.0.1/AK/images/"+content_list.get(11)).resize(100,100).into(imageView);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("ErrorResponse", finalResponse);
                        Toast.makeText(Content.this, "ERROR", Toast.LENGTH_SHORT).show();


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("name",name );

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);
    }
}
