package com.speedtrolley.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    private WebView webView;
    private Button btnTry,canceltBtn,acceptBtn;
    private RelativeLayout errorlayout;
    private ImageView imageView;
    private String URL = "https://speedtrolley.com/";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private ScrollView scrollaccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        errorlayout=(RelativeLayout)findViewById(R.id.errorlayout);
        btnTry=(Button)findViewById(R.id.btntry);
        scrollaccept=(ScrollView)findViewById(R.id.scrollaccept);
        canceltBtn = (Button)findViewById(R.id.canceltBtn);
        acceptBtn = (Button)findViewById(R.id.acceptBtn);
        imageView=(ImageView) findViewById(R.id.imageview);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);





        loadpage();


        loadData();



        webView.setWebViewClient(new WebViewClient() {


            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {


                return shouldOverrideUrlLoading(url);


            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
                Uri uri = request.getUrl();
                return shouldOverrideUrlLoading(uri.toString());
            }

            private boolean shouldOverrideUrlLoading(final String url) {
                Log.i("TAGLoad", "shouldOverrideUrlLoading() URL : " + url);
                // Here put your code

                return false; // Returning True means that application wants to leave the current WebView and handle the url itself, otherwise return false.
            }
        });
        btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadpage();
            }
        });


    }
public void loadpage()
{
    ConnectivityManager connectivityManager = (ConnectivityManager) DetailActivity.this
        .getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();

    if (networkInfo!=null&& networkInfo.isConnectedOrConnecting())
    {

        errorlayout.setVisibility(View.GONE);
        webView.loadUrl(URL);
        webView.setWebViewClient(new WebViewClient());
    }
    else
    {
        errorlayout.setVisibility(View.VISIBLE);


    }

}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {

                        webView.goBack();

                    } else {
                        finish();

                    }

                    return true;

            }

        }
        return super.onKeyDown(keyCode, event);
    }
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, "true");
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String text = sharedPreferences.getString(TEXT, "");

        if (!(text.equals("true")))
        {
            scrollaccept.setVisibility(View.VISIBLE);

            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveData();

                    scrollaccept.setVisibility(View.GONE);
                }
            });
            canceltBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    finish();
                }
            });
        }
        else
        {


            scrollaccept.setVisibility(View.GONE);
        }


    }
}