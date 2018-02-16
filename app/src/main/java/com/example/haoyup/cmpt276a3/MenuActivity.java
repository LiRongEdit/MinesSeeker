package com.example.haoyup.cmpt276a3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
        setUpPlayButton();
        setUOptButton();
        setUpHelpButton();
    }


    private void setUpPlayButton() {
        final Button playButton = (Button) findViewById(R.id.playBtn);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });
    }
    private void setUOptButton() {
        Button optButton = (Button) findViewById(R.id.optionBtn);
        optButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OptionActivity.makeIntent(MenuActivity.this);
                startActivity(intent);
            }
        });
    }
    private void setUpHelpButton() {
        Button helpButton = (Button) findViewById(R.id.helpBtn);

    }
    public static Intent makeIntent(Context context){
        return new Intent(context, MenuActivity.class);
    }

    // Redefine the navigation back button
    @Override
    public void onBackPressed() {
        finish();
    }
}

