package taodev.h5app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import taodev.h5app.MainActivity;

public class X5WebView extends WebView {
	TextView title;
	private WebViewClient client = new WebViewClient() {
		/**
		 * 防止加载网页时调起系统浏览器
		 */
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// 如下方案可在非微信内部WebView的H5页面中调出微信支付
			if (url.startsWith("weixin://wap/pay?")) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
				MainActivity.Instance.startActivity(intent);
				return true;
			} else if (parseScheme(url)) {
				try {
					Intent intent;
					intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
					intent.addCategory("android.intent.category.BROWSABLE");
					intent.setComponent(null);
					// intent.setSelector(null);
					MainActivity.Instance.startActivity(intent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				view.loadUrl(url);
			}
			return true;
		}

		public boolean parseScheme(String url) {
			if (url.contains("platformapi/startapp")) {
				return true;
			} else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
					&& (url.contains("platformapi") && url.contains("startapp"))) {
				return true;
			} else {
				return false;
			}
		}
	};

	@SuppressLint("SetJavaScriptEnabled")
	public X5WebView(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		this.setWebViewClient(client);
		// this.setWebChromeClient(chromeClient);
		// WebStorage webStorage = WebStorage.getInstance();
		initWebViewSettings();
		this.getView().setClickable(true);
	}

	private void initWebViewSettings() {
		WebSettings webSetting = this.getSettings();
		webSetting.setJavaScriptEnabled(true);
		webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
		webSetting.setAllowFileAccess(true);
		webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		webSetting.setSupportZoom(true);
		webSetting.setBuiltInZoomControls(true);
		webSetting.setUseWideViewPort(true);
		webSetting.setSupportMultipleWindows(true);
		// webSetting.setLoadWithOverviewMode(true);
		webSetting.setAppCacheEnabled(true);
		// webSetting.setDatabaseEnabled(true);
		webSetting.setDomStorageEnabled(true);
		webSetting.setGeolocationEnabled(true);
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
		// webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
		webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);

		// this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
		// settings 的设计
	}

	//	@Override
	//	protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
	//		boolean ret = super.drawChild(canvas, child, drawingTime);
	//		canvas.save();
	//		Paint paint = new Paint();
	//		paint.setColor(0x7fff0000);
	//		paint.setTextSize(24.f);
	//		paint.setAntiAlias(true);
	//		if (getX5WebViewExtension() != null) {
	//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
	//					+ android.os.Process.myPid(), 10, 50, paint);
	//			canvas.drawText(
	//					"X5  Core:" + QbSdk.getTbsVersion(this.getContext()), 10,
	//					100, paint);
	//		} else {
	//			canvas.drawText(this.getContext().getPackageName() + "-pid:"
	//					+ android.os.Process.myPid(), 10, 50, paint);
	//			canvas.drawText("Sys Core", 10, 100, paint);
	//		}
	//		canvas.drawText(Build.MANUFACTURER, 10, 150, paint);
	//		canvas.drawText(Build.MODEL, 10, 200, paint);
	//		canvas.restore();
	//		return ret;
	//	}

	public X5WebView(Context arg0) {
		super(arg0);
		setBackgroundColor(0);
	}

}
