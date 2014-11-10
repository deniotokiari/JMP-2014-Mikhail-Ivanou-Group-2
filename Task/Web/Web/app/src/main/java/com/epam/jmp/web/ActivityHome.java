package com.epam.jmp.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ActivityHome extends Activity {

    private static final String INTERFACE_NAME = "JMP";

    private static final String URL_LOCAL = "file:///android_asset/index.html";
    private static final String URL_SITE = "http://my-sb.ru/jmp/index.html";
    private static final String URL_FORM = "file:///android_asset/registration.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        WebView webView = (WebView) findViewById(R.id.WebView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this), INTERFACE_NAME);
        webView.loadUrl(URL_LOCAL);
    }

    private static class WebAppInterface {
        private Context mContext;

        private WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void openSite() {
            Intent intent = new Intent(mContext, ActivitySite.class);
            intent.putExtra(ActivitySite.EXTRA_URL, URL_SITE);
            mContext.startActivity(intent);
        }

        @JavascriptInterface
        public void openForm() {
            Intent intent = new Intent(mContext, ActivitySite.class);
            intent.putExtra(ActivitySite.EXTRA_URL, URL_FORM);
            mContext.startActivity(intent);
        }
    }
}
