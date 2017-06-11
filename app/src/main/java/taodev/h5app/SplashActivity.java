package taodev.h5app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * 闪屏页面
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

    // 屏蔽闪屏返回退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
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
