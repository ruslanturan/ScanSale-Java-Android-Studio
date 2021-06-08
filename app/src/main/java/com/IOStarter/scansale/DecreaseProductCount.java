package com.IOStarter.scansale;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DecreaseProductCount extends AsyncTask<Void, Void, Void> {
    public static String productCount = "";
    String data;
    String line;

    public DecreaseProductCount() {
        String str = "";
        this.data = str;
        this.line = str;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        for (int i = 0; i < ActivityHome.basket.size(); i++) {
            int intValue = ((ClassBasket) ActivityHome.basket.get(i)).getId().intValue();
            int intValue2 = ((ClassBasket) ActivityHome.basket.get(i)).getItem_count().intValue();
            StringBuilder sb = new StringBuilder();
            sb.append(intValue);
            sb.append("&count=");
            sb.append(intValue2);
            productCount = sb.toString();
            try {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("https://myoutlet.ge/api/decreaseproductcount?id=");
                sb2.append(productCount);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL(sb2.toString()).openConnection()).getInputStream()));
                while (this.line != null) {
                    String readLine = bufferedReader.readLine();
                    this.line = readLine;
                    if (readLine != null) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(this.data);
                        sb3.append(this.line);
                        this.data = sb3.toString();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
    }
}
