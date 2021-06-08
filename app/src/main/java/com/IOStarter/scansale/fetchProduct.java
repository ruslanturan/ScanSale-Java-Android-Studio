package com.IOStarter.scansale;

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

public class fetchProduct extends AsyncTask<Void, Void, Void> {
    String Descriptions;
    JSONArray Galleries;

    /* renamed from: Id */
    Integer f60Id;
    String PartnerName;
    String ProductName;
    String Sale;
    String SaledCost;
    String count;
    String data = "";
    ArrayList<ClassProduct> productList = new ArrayList<>();

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://myoutlet.ge/api/product/get/");
            sb.append(ListAdapterProducts.f59Id);
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
                this.f60Id = Integer.valueOf(jSONObject.getInt("id"));
                this.ProductName = jSONObject.getString("productName");
                this.PartnerName = jSONObject.getString("partner");
                this.count = jSONObject.getString("count");
                this.Sale = jSONObject.getString("sale");
                this.SaledCost = jSONObject.getString("saledCost");
                this.Galleries = jSONObject.getJSONArray("galleries");
                this.Descriptions = jSONObject.getString("descriptions");
                ClassProduct classProduct = new ClassProduct(this.f60Id, this.Sale, this.SaledCost, this.count, this.ProductName, this.PartnerName, this.Descriptions, this.Galleries);
                this.productList.add(classProduct);
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
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
        ActivityProduct.productInfo.setAdapter(new ListAdapterProduct(ActivityProduct.productContext, R.layout.adapter_product, this.productList));
    }
}