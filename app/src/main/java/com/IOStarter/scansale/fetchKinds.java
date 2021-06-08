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

public class fetchKinds extends AsyncTask<Void, Void, Void> {
    public static ArrayList<ClassKind> kinds = new ArrayList<>();
    String data;
    Integer singleParsedId;
    String singleParsedImage;
    String singleParsedName;

    public fetchKinds() {
        String str = "";
        this.data = str;
        this.singleParsedName = str;
        this.singleParsedImage = str;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("https://myoutlet.ge/api/kinds/get/");
            sb.append(ListAdapterCategory.f57Id);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL(sb.toString()).openConnection()).getInputStream()));
            String str = "";
            kinds.clear();
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
                this.singleParsedId = Integer.valueOf(jSONObject.getInt("Id"));
                this.singleParsedName = jSONObject.getString("Name");
                this.singleParsedImage = jSONObject.getString("image");
                kinds.add(new ClassKind(this.singleParsedId, this.singleParsedName, this.singleParsedImage));
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
        ActivityKinds.progressDialog = new ProgressDialog(ActivityCategories.context);
        ActivityKinds.progressDialog.show();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
        ActivityKinds.categories.setAdapter(new HorizontalAdapterCategories(ActivityKinds.kindContext, fetchCategories.categories));
        ActivityKinds.kindsList.setAdapter(new ListAdapterKind(ActivityKinds.kindContext, R.layout.adapter_kinds, kinds));
        ActivityKinds.progressDialog.dismiss();
    }
}
