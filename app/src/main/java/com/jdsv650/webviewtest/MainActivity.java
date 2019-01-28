package com.jdsv650.webviewtest;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        warmUpChrome();
        launchUrl();

        /*
        myWebView = findViewById(R.id.webviewID);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setLoadsImagesAutomatically(true);  */

        // myWebView.getSettings().setLoadWithOverviewMode(true);
        // myWebView.getSettings().setUseWideViewPort(true);
        //  myWebView.setWebContentsDebuggingEnabled(true);


        // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://xxxxxxxxxxxxxx")));

       // String userAgent = "Mozilla/5.0 (Linux; Android 6.0; Android SDK built for x86 Build/MASTER; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/44.0.2403.119 Mobile Safari/537.36";

        // myWebView.getSettings().setUserAgentString(userAgent
        //);


/*
        myWebView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
                Toast.makeText(getBaseContext(), "on progress changed", Toast.LENGTH_SHORT).show();


                // Your custom code.
        }
        } );*/

/**
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {


                if (url.toLowerCase().contains("/favicon.ico")) {
                    try {
                        return new WebResourceResponse("image/png", null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
  **/
            /**
             @Override
             @SuppressLint("NewApi") public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {



             if(!request.isForMainFrame() && request.getUrl().getPath().endsWith("/favicon.ico")) {
             try {
             //                    } catch (Exception e) {
             e.printStackTrace();
             }
             }

             return null;
             }  **/

            /***
            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                Toast.makeText(getBaseContext(), "on page finished", Toast.LENGTH_SHORT).show();


            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getBaseContext(), description, Toast.LENGTH_SHORT).show();
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Toast.makeText(getBaseContext(), "should overidw url loading", Toast.LENGTH_SHORT).show();


                //view.loadUrl(url);
                return true;
            }
        }); ***/

       // openURL();


    }

    /**
     * Opens the URL in a browser
     */
    private void openURL() {
      //  myWebView.loadUrl("https://xxxxxxxxxxxxxx");


        //myWebView.loadUrl("http://xxxxxxxxxx/"); js ok
        // myWebView.requestFocus();


    }

    final String EXTRA_CUSTOM_TABS_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    final String PACKAGE_NAME = "com.android.chrome";
    CustomTabsClient mClient;

    private void warmUpChrome() {
        CustomTabsServiceConnection service = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                mClient = client;
                mClient.warmup(0);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mClient = null;
            }
        };

        CustomTabsClient.bindCustomTabsService(getApplicationContext(), PACKAGE_NAME, service);
    }

    private void launchUrl() {

        Uri uri = Uri.parse("xxxxxxxxxxxxxxxxx");

       // Uri uri = Uri.parse("https://www.google.com");
        if (uri == null) {
            return;
        }
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
        customTabsIntent.intent.setData(uri);
        customTabsIntent.intent.putExtra(EXTRA_CUSTOM_TABS_TOOLBAR_COLOR, getResources().getColor(R.color.colorPrimary));

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(customTabsIntent.intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolveInfo : resolveInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            if (TextUtils.equals(packageName, PACKAGE_NAME))
                customTabsIntent.intent.setPackage(PACKAGE_NAME);
        }

        customTabsIntent.launchUrl(this, uri);

    }
}