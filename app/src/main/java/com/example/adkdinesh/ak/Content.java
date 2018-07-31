package com.example.adkdinesh.ak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Content extends AppCompatActivity {

    TextView tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        String newString;
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

    }
}
