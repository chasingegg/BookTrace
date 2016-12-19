package com.example.dimon.booktrace;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //variables declaration
    private TextView aboutTextView;
    private Button sign_up;
    private Button sign_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutTextView = (TextView) findViewById(R.id.aboutTextView);
        sign_up = (Button) findViewById(R.id.sign_up);
        sign_in = (Button) findViewById(R.id.sign_in);

        setAboutPage();
        sign_up();
        sign_in();
    }

    private void setAboutPage()
    {
        // adding underline and link to about textview
        aboutTextView.setPaintFlags(aboutTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Linkify.addLinks(aboutTextView, Linkify.ALL);

        // setting to point to the about us page
        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showing about_us activity
                startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
            }
        });
    }

    private void sign_up()
    {
        sign_up.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }

    private void sign_in()
    {
        sign_in.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignInActivity.class));
            }
        });
    }

}
