package taodev.h5app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    public static boolean SHOW_SPLASH = true;
    public static SplashActivity Instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashActivity.Instance = this;
        // setContentView(R.layout.activity_splash);

        this.onSplashStart();
    }

    public void onSplashStart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        SplashActivity.closeSplash();
    }

    public void onSplashStop() {
    }

    public static void closeSplash() {
        SplashActivity.Instance.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!SplashActivity.SHOW_SPLASH) {
                    return;
                }
                SplashActivity.Instance.onSplashStop();
                SplashActivity.Instance.finish();
            }
        });
    }
}
