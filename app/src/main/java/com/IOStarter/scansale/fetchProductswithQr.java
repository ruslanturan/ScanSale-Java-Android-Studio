package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class fetchProductswithQr extends AsyncTask<Void, Void, Void> {

    /* renamed from: Id */
    Integer f63Id;
    String Photo;
    String ProductName;
    String Sale;
    String SaledCost;
    String UniqueNum;

    /* renamed from: c */
    Integer f64c;
    String data = "";
    ArrayList<ClassProducts> products = new ArrayList<>();

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://myoutlet.ge/api/barcode/get/");
            sb.append(ActivityScanning.f50id);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL(sb.toString()).openConnection()).getInputStream()));
            String str = "";
            while (str != null) {
                str = bufferedReader.readLine();
                StringBuilder sb2 = new StringBuilder();
                sb2.append(this.data);
                sb2.append(str);
                this.data = sb2.toString();
            }
            JSONArray jSONArray = new JSONArray(this.data);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                this.f63Id = Integer.valueOf(jSONObject.getInt("id"));
                this.ProductName = jSONObject.getString("productName");
                this.Photo = jSONObject.getString("photo");
                this.Sale = jSONObject.getString("sale");
                this.SaledCost = jSONObject.getString("saledCost");
                Integer valueOf = Integer.valueOf(jSONObject.getInt("productUniqueNumber"));
                this.f64c = valueOf;
                String num = valueOf.toString();
                this.UniqueNum = num;
                if (num.length() > 9) {
                    String[] split = this.UniqueNum.substring(0, 9).split(".");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(split[0]);
                    sb3.append(split[1]);
                    this.UniqueNum = sb3.toString();
                }
                ClassProducts classProducts = new ClassProducts(this.f63Id, this.Sale, this.SaledCost, this.UniqueNum, this.ProductName, this.Photo);
                this.products.add(classProducts);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        ActivityProductswithQr.progressDialog = new ProgressDialog(ActivityProductswithQr.context);
        ActivityProductswithQr.progressDialog.show();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
        ActivityProductswithQr.productsListQr.setAdapter(new ListAdapterProductswithQr(ActivityProductswithQr.context, R.layout.adapter_products, this.products));
        ActivityProductswithQr.progressDialog.dismiss();
    }
}
