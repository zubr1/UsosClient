package com.uksw.fraktal.www.usosclient;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mWebview = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        String url = "https://login.uksw.edu.pl/cas/login?service=https%3A%2F%2Fusosweb.uksw.edu.pl%2Fkontroler.php%3F_action%3Dlogowaniecas%2Findex&locale=pl";

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://usosweb")) {
                    Intent i = new Intent(getApplicationContext(), PlanActivity.class);
                    startActivity(i);
                    return true;
                } else if (url.startsWith("https://login")) {
                    return false;
                }
                return false;
            }
        });

        mWebview.loadUrl(url);
        setContentView(mWebview);
    }
}

