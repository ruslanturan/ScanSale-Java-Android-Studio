package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ActivityPaymentResult extends AppCompatActivity {
    public static ImageButton btnOk;
    public static Context context;
    public static ProgressDialog progressDialog;
    public static TextView txtResult;

    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_payment_result);
        context = this;
        getWebsite(ActivityPay.testurl);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnOk = (ImageButton) findViewById(R.id.btnOk);
        LayoutParams layoutParams = new LayoutParams(-1, (ActivityMain.android_height * 2) / 3);
        layoutParams.setMargins(0, 0, 0, 0);
        txtResult.setLayoutParams(layoutParams);
        LayoutParams layoutParams2 = new LayoutParams(ActivityMain.android_width / 3, ActivityMain.android_width / 6);
        layoutParams2.setMargins((ActivityMain.android_width - (ActivityMain.android_width / 3)) / 2, 0, 0, 0);
        btnOk.setLayoutParams(layoutParams2);
        btnOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ActivityPaymentResult.this.startActivity(new Intent(ActivityPaymentResult.this.getApplicationContext(), ActivityHome.class));
            }
        });
    }

    private void getWebsite(final String url) {
        new Thread(new Runnable() {
            public void run() {
                String str = "\n";
                final StringBuilder sb = new StringBuilder();
                try {
                    Document document = Jsoup.connect(url).get();
                    String title = document.title();
                    Elements select = document.select("div");
                    sb.append(title);
                    sb.append(str);
                    Iterator it = select.iterator();
                    while (it.hasNext()) {
                        Element element = (Element) it.next();
                        sb.append(str);
                        sb.append("Class: ");
                        sb.append(element.attr("class"));
                    }
                } catch (IOException e) {
                    sb.append("Error: ");
                    sb.append(e.getMessage());
                    sb.append(str);
                }
                ActivityPaymentResult.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (sb.toString().contains("icon_confirmed")) {
                            new OrderResult().execute(new Void[0]);
                            ActivityPaymentResult.txtResult.setText("გადახდა წარმატებით დასრულდა");
                            new DecreaseProductCount().execute(new Void[0]);
                            ActivityHome.basket.clear();
                            return;
                        }
                        ActivityPaymentResult.txtResult.setText("გადახდა ვერ მოხერხდა");
                    }
                });
            }
        }).start();
    }
}
