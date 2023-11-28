package edu.fje.plantillaexamen;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.GeolocationPermissions;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    WebView navegador;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.m27_webview);
        navegador = (WebView) findViewById(R.id.webkit);

        navegador.getSettings().setJavaScriptEnabled(true);
        navegador.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        navegador.getSettings().setDatabaseEnabled(true);
        navegador.getSettings().setDomStorageEnabled(true);

        navegador.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        navegador.setWebViewClient(new WebViewClient());

        // Carregar el fitxer webview.html des de la carpeta assets
        navegador.loadUrl("file:///android_asset/webview.html");

    }
}
