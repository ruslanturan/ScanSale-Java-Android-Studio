package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class fetchProducts extends AsyncTask<Void, Void, Void> {
    Integer Id;
    String Photo;
    String ProductName;
    String Sale;
    String SaledCost;
    String UniqueNum;
    Integer c;
    String data = "";
    ArrayList<ClassProducts> products = new ArrayList<>();

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://myoutlet.ge/api/products/getbykind/");
            sb.append(ListAdapterKind.f58Id);
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
                this.Id = Integer.valueOf(jSONObject.getInt("id"));
                this.ProductName = jSONObject.getString("productName");
                this.Photo = jSONObject.getString("photo");
                this.Sale = jSONObject.getString("sale");
                this.SaledCost = jSONObject.getString("saledCost");
                Integer valueOf = Integer.valueOf(jSONObject.getInt("productUniqueNumber"));
                this.c = valueOf;
                String num = valueOf.toString();
                this.UniqueNum = num;
                if (num.length() > 9) {
                    String[] split = this.UniqueNum.substring(0, 9).split(".");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(split[0]);
                    sb3.append(split[1]);
                    this.UniqueNum = sb3.toString();
                }
                ClassProducts classProducts = new ClassProducts(this.Id, this.Sale, this.SaledCost, this.UniqueNum, this.ProductName, this.Photo);
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
        ActivityProducts.progressDialog = new ProgressDialog(ActivityKinds.kindContext);
        ActivityProducts.progressDialog.show();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
        ActivityProducts.kinds.setAdapter(new HorizontalAdapterKinds(ActivityProducts.productsContext, fetchKinds.kinds));
        ActivityProducts.productsList.setAdapter(new ListAdapterProducts(ActivityProducts.productsContext, R.layout.adapter_products, this.products));
        ActivityProducts.progressDialog.dismiss();
    }
}
