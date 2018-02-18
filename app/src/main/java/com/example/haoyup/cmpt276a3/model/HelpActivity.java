package com.example.haoyup.cmpt276a3.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.haoyup.cmpt276a3.MenuActivity;
import com.example.haoyup.cmpt276a3.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        //cmpt276 home directory
        TextView textView = (TextView) findViewById(R.id.hyperlinkID);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.cs.sfu.ca/CourseCentral/276/bfraser/index.html'> Link to cmpt 276 </a>";
        textView.setText(Html.fromHtml(text));
        //pics references
        TextView textView2 = (TextView) findViewById(R.id.hyperlinkID2);
        textView2.setClickable(true);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        String text2 = "<a href='http://www.shutterstock.com'> Link to the mine image </a>";
        textView2.setText(Html.fromHtml(text2));
        //instructions
        TextView instruction = (TextView) findViewById(R.id.instructionID);
        instruction.setText("Use option manu to set up the scale of the game. During the game, click on the squares to reveal or to scan a spot, if there is a mine, then the mine will be revealed, if there isn't, the scan of how many mines it has vertically and horizontally. Use as few scan as possible to reveal all the mines to win the game");


        Button button = (Button) findViewById(R.id.backID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public static Intent makeIntent(Context context){
        return new Intent(context, HelpActivity.class);
    }
}
