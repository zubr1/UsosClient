package com.uksw.fraktal.www.usosclient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.uksw.fraktal.www.usosclient.authorization.UsosAuth;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import butterknife.BindString;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final String CALLBACK_URL = "https://www.usosweb.uksw.edu.pl";
    private static final String CONSUMER_KEY = "26DYUWpXK4qh7sChfdLs";
    private static final String CONSUMER_SECRET = "4hZ486LpvhVR2M6XMCvxaxfndCcVGmjsKjDmaJyS";

    @BindString(R.string.enable_network) String noConnectionMsg;
    @BindString(R.string.ok) String okMsg;

    private WebView webView;
    private OAuth10aService service;
    private OAuth1RequestToken requestToken;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        // connect to network box
        if (!isNetConnected()) {
            showDialog(noConnectionMsg);
        }

        service = new ServiceBuilder()
                .apiKey(CONSUMER_KEY)
                .apiSecret(CONSUMER_SECRET)
                .callback(CALLBACK_URL)
                .build(UsosAuth.instance());

//        webView = (WebView) findViewById(R.id.webview);
        webView = new WebView(this);
        setContentView(webView);
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setWebViewClient(mWebViewClient);
//        webView.setWebChromeClient(mWebChromeClient);


        startAuthorize();
        setContentView(webView);

    }

    public boolean isNetConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else
            return false;
    }

    public boolean showDialog(String msg)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                okMsg,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (!isNetConnected()) {
                            showDialog(noConnectionMsg);
                        }
                        else {
                            dialog.cancel();
                        }
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
        return true;
    }

    private void startAuthorize() {
        (new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    requestToken = service.getRequestToken();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return service.getAuthorizationUrl(requestToken);
            }

            @Override
            protected void onPostExecute(String url) {
                Log.d("url load", "link: " + url+"&oauth_token="+requestToken.getToken());
                webView.loadUrl(url+"?oauth_token="+requestToken.getToken());
            }
        }).execute();
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if ((url != null) && (url.startsWith(CALLBACK_URL))) { // Override webview when user came back to CALLBACK_URL
                webView.stopLoading();
                webView.setVisibility(View.INVISIBLE);
                Uri uri = Uri.parse(url);
                final String verifier = new String(uri.getQueryParameter("oauth_verifier"));
                (new AsyncTask<Void, Void, Token>() {
                    @Override
                    protected Token doInBackground(Void... params) {

                        try {
                            return service.getAccessToken(requestToken, verifier);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        return requestToken;
                    }

                    @Override
                    protected void onPostExecute(Token accessToken) {
                        // AccessToken is passed here! Do what you want!
                        Log.d("access token ", "toke" + accessToken);
                        showDialog("Uzyskano dostep do access token");
//                        finish();
                    }
                }).execute();
            } else {
                super.onPageStarted(view, url, favicon);
            }
        }
    };



}

