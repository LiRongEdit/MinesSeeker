package com.example.haoyup.cmpt276a3;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity implements Animation.AnimationListener {

    ImageView image;
    Animation fadeIn;
    Animation fadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        image = (ImageView) findViewById(R.id.imageView);
        image.setVisibility(View.INVISIBLE);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn.setAnimationListener(this);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        image.startAnimation(fadeIn);
        setUpButton();
    }

    private void setUpButton() {
        Button btn = (Button) findViewById(R.id.startBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MenuActivity.makeIntent(WelcomeActivity.this);
                startActivity(intent);

                finish();
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == fadeIn) {
            image.startAnimation(fadeOut);
            image.startAnimation(fadeIn);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
