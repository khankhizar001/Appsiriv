package com.example.appiness.appsrivnews.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.appiness.appsrivnews.R;

/**
 * Created by appiness on 11/5/18.
 */

public class WebviewActivity extends AppCompatActivity {

    private WebView webView;

    String href,title;
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent i = getIntent();
        href = i.getStringExtra("url");
        title = i.getStringExtra("title");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(title);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(href);
        webView.setHorizontalScrollBarEnabled(false);
        //webView.setWebViewClient(new WebViewClient());
        WebClientClass webViewClient = new WebClientClass();
        webView.setWebViewClient(webViewClient);
        WebChromeClient webChromeClient=new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);

    }
    public class WebClientClass extends WebViewClient {
        ProgressDialog pd = null;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(WebviewActivity.this);
            pd.setTitle("Please wait");
            pd.setMessage("Page is loading..");
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }

    public class WebChromeClass extends WebChromeClient{
    }



}
