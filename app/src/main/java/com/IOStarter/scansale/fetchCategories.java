package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Button;

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

public class fetchCategories extends AsyncTask<Void, Void, Void> {
    public static Button btn;
    public static ArrayList<ClassCategory> categories = new ArrayList<>();
    String data;
    Integer singleParsedId;
    String singleParsedName;

    public fetchCategories() {
        String str = "";
        this.data = str;
        this.singleParsedName = str;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL("https://myoutlet.ge/api/categories/get").openConnection()).getInputStream()));
            String str = "";
            categories.clear();
            while (str != null) {
                str = bufferedReader.readLine();
                StringBuilder sb = new StringBuilder();
                sb.append(this.data);
                sb.append(str);
                this.data = sb.toString();
            }
            JSONArray jSONArray = new JSONArray(this.data);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                this.singleParsedId = Integer.valueOf(jSONObject.getInt("Id"));
                this.singleParsedName = jSONObject.getString("Name");
                categories.add(new ClassCategory(this.singleParsedId, this.singleParsedName));
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
        ActivityCategories.progressDialog = new ProgressDialog(ActivityCategories.context);
        ActivityCategories.progressDialog.show();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Void voidR) {
        super.onPostExecute(voidR);
        ActivityCategories.list.setAdapter(new ListAdapterCategory(ActivityCategories.context, R.layout.adapter_categories, categories));
        ActivityCategories.progressDialog.dismiss();
    }
}