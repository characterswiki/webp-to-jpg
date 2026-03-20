package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

public class MainActivity extends Activity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the WebView programmatically
        myWebView = new WebView(this);
        
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Allow JS for conversion logic
        webSettings.setAllowFileAccess(true);   // Allow access to local assets
        webSettings.setDomStorageEnabled(true); // Required for many JS tools

        // Prevent links from opening in an external browser
        myWebView.setWebViewClient(new WebViewClient());
        
        // This is necessary to handle file uploads/inputs in HTML
        myWebView.setWebChromeClient(new WebChromeClient());

        // Load your HTML file from the assets folder
        myWebView.loadUrl("file:///android_asset/index.html");

        setContentView(myWebView);
    }

    // Handle the "Back" button so it doesn't close the app immediately
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
