package com.pe.sise.trackingtaxi;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
 
public class ActivityWeb extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        this.setContentView(R.layout.webview);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        WebView myWebView = (WebView) this.findViewById(R.id.webView);
        myWebView.loadUrl("http://misitiodemostracion.site90.net/gcm_server_php/index.php");
 
    }
 
}
