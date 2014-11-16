package com.epam.jmp.web;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Toast;

public class ActivitySite extends Activity {
    private static final String INTERFACE_NAME = "JMP";
    public static final String EXTRA_URL = "com.epam.jmp.web.ActivitySite.EXTRA_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        WebView mWebView = (WebView) findViewById(R.id.WebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new WebAppInterface(this, mWebView), INTERFACE_NAME);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        mWebView.loadUrl(getIntent().getStringExtra(EXTRA_URL));
    }

    private static class WebAppInterface {
        private final WebView mWebView;
        private final Context mContext;
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        private WebAppInterface(Context c, WebView wv) {
            mContext = c;
            mWebView = wv;
        }

        @JavascriptInterface
        public void validate(String fio) {
            if (fio.length() < 3) {
                Toast.makeText(mContext, R.string.msg_error, Toast.LENGTH_SHORT).show();
            }
        }

        @JavascriptInterface
        public void datePicker() {
            DatePickerDialog dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            String date = String.format("%s.%s.%d", checkNumber(dayOfMonth), checkNumber(monthOfYear + 1), year);
                            mWebView.loadUrl("javascript:document.getElementById('birthday').value='" + date + "'");
                        }
                    });
                }
            }, 1990, 0, 1);
            dialog.show();
        }

        @JavascriptInterface
        public void notification(String val) {
            final Dialog d = new Dialog(mContext);
            d.setTitle("NumberPicker");
            d.setContentView(R.layout.dialog_picker);
            final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker);
            d.findViewById(R.id.bSet).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mWebView.loadUrl("javascript:document.getElementById('notifications').value='" + np.getValue() + "'");
                        }
                    });
                    d.dismiss();
                }
            });
            d.findViewById(R.id.bCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });
            np.setMaxValue(21);
            np.setMinValue(7);
            np.setValue(Integer.valueOf(val));
            d.show();
        }

    }

    private static String checkNumber(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}
