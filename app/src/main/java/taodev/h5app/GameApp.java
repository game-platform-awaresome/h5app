package taodev.h5app;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;
import com.tendcloud.appcpa.TalkingDataAppCpa;

/**
 * Created by Administrator on 2017/6/11.
 */

public class GameApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TalkingDataAppCpa.init(this.getApplicationContext(), "F873A7D9FCB64B068231C417E20CB024", "测试渠道");

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                // Log.d("app", " onViewInitFinished is " + arg0);
            }
            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
    }
}
