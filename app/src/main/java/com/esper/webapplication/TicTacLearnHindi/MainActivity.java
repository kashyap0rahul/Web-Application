package com.esper.webapplication.TicTacLearnHindi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideNavigationBar(); // To hide the Navigation bar in devices (Recent, Home and Back button)

        CustomWebViewClient client = new CustomWebViewClient(this);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);

        webView.setWebViewClient(client);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false); // to enable the zoom control by default just put "true".
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl("https://www.youtube.com/channel/UCkxfbAky2v0yXuKWOKCDy1w");

        //URL 1: For Barc India - https://forms.office.com/pages/responsepage.aspx?id=l0X4RB_knkuE8BLm1bl12EMSC_E1mfxEkywFdWuJE6VUQVhOSllGS0QxSjZRSjVZTkZVNVpFMVJJWC4u
        //URL 2: For Barc India - https://backcheck.barcindia.in/surveybackcheck/login
        //URL 3: Elasticrum Pro - https://cas.elasticrun.in/login#login
        //URL 4: Patient Portal - http://patientportal.tccoh.com/patientPortal/kioskLogin (Works on Intranet)
        //URL 5: For Byjus Actgrants: https://www.youtube.com/c/Bodhaguru/featured
        //URL 6: For Byjus ActGrants: https://www.youtube.com/channel/UCkxfbAky2v0yXuKWOKCDy1w
        // URL 7: For Byjus Actgrants: https://www.youtube.com/c/KhanAcademyHindi/videos

        //To enable the Swipe down refresh
        final SwipeRefreshLayout finalMySwipeRefreshLayout1 = findViewById(R.id.swiperefresh);

        finalMySwipeRefreshLayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                webView.loadUrl(webView.getUrl());
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            public void onProgressChanged(WebView view, int newProgress) {
                MainActivity.this.progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            public void onPageFinished(WebView view, String url) {
                finalMySwipeRefreshLayout1.setRefreshing(false);
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);

                // This is your not youtube site, so do not override; let your WebView load the page
            }
        });
    }

    class CustomWebViewClient extends WebViewClient {
        private Activity activity;

        public CustomWebViewClient(Activity activity2) {
            this.activity = activity2;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return false;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
            return false;
        }
    }
    private void hideNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.webView.goBack();
        return true;
    }

}
