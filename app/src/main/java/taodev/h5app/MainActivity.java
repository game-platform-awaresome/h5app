package taodev.h5app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

import taodev.h5app.utils.X5WebView;

public class MainActivity extends AppCompatActivity {
    public static MainActivity Instance = null;

    private X5WebView mGameView = null;
    public String mGameURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.Instance = this;

        this.mGameURL = this.getString(R.string.gameurl);

        // 常亮不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        this.initGameView();
    }

    private void initGameView() {
        this.mGameView = (X5WebView)findViewById(R.id.gameView);
        this.mGameView.loadUrl(this.mGameURL);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(this).setTitle(R.string.app_name).setMessage("是否退出游戏").setPositiveButton("确定", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    MainActivity.this.finish();
                    System.exit(0);
                }
            }).setNegativeButton("取消", null).show();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
