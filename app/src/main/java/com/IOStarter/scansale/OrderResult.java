package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OrderResult extends AsyncTask<Void, Void, Void> {
    String data;
    String line;
    int orderId = 0;

    public OrderResult() {
        String str = "";
        this.data = str;
        this.line = str;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://myoutlet.ge/api/successorder/get/");
            sb.append(ActivityBuyer.orderId);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL(sb.toString()).openConnection()).getInputStream()));
            while (this.line != null) {
                String readLine = bufferedReader.readLine();
                this.line = readLine;
                if (readLine != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.data);
                    sb2.append(this.line);
                    this.data = sb2.toString();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        ActivityPaymentResult.progressDialog = new ProgressDialog(ActivityPaymentResult.context);
        ActivityPaymentResult.progressDialog.show();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
        ActivityPaymentResult.progressDialog.dismiss();
    }
}
