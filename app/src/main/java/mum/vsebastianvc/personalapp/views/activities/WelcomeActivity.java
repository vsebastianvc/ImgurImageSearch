package mum.vsebastianvc.personalapp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

import mum.vsebastianvc.personalapp.R;

/**
 * Created by Sebastian on 02/04/2020.
 */
public class WelcomeActivity extends AppCompatActivity {

    private static final long WELCOME_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Initialize Splash
        ImageView imageView = findViewById(R.id.splashApplication);

        //Loading image from below url into imageView
        Glide.with(this)
                .asGif()
                .load(R.drawable.welcome)
                .override(200, 200)
                .into(imageView);

        Timer objectTimer = new Timer();
        objectTimer.schedule(new WelcomeTask(), WELCOME_DELAY);
    }


    private class WelcomeTask extends TimerTask {

        @Override
        public void run() {
            Intent categoryActivity;
            categoryActivity = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(categoryActivity);
            finish();
        }

    }
}