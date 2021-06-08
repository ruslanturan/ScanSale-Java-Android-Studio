package com.IOStarter.scansale;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityPay extends AppCompatActivity {
    public static Context context = null;
    public static String testurl = "";
    /* access modifiers changed from: private */
    public WebView webView;

    private class SSLTolerentWebViewClient extends WebViewClient {
        private SSLTolerentWebViewClient() {
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_pay);
        context = this;
        WebView webView2 = (WebView) findViewById(R.id.webview);
        this.webView = webView2;
        webView2.setWebViewClient(new SSLTolerentWebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                if (ActivityPay.this.webView.getUrl().contains("myoutlet.ge")) {
                    ActivityPay.this.webView.destroy();
                    ActivityPay.context.startActivity(new Intent(ActivityPay.context, ActivityPaymentResult.class));
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                String url = ActivityPay.this.webView.getUrl();
                if (url.contains("unipay.com/checkout?id=")) {
                    ActivityPay.testurl = url;
                }
            }
        });
        WebView webView3 = this.webView;
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.myoutlet.ge/unipay/createorder/");
        sb.append(ActivityBuyer.orderId);
        webView3.loadUrl(sb.toString());
        this.webView.getSettings().setJavaScriptEnabled(true);
    }

    public void onBackPressed() {
        Builder builder = new Builder(context);
        String str = "დიახ";
        builder.setMessage((CharSequence) "ოპერაცია ჩაიწერება, როგორც ჩავარდნილი. გსურთ გასვლა?").setCancelable(false).setNegativeButton((CharSequence) "არა", (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setPositiveButton((CharSequence) str, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityPay.context.startActivity(new Intent(ActivityPay.context, ActivityHome.class));
            }
        });
        AlertDialog create = builder.create();
        create.setTitle("ყურადღება");
        create.show();
    }
}
