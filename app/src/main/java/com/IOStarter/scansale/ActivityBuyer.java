package com.IOStarter.scansale;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityBuyer extends AppCompatActivity {
    public static String Desc = "";
    public static String address;
    public static ImageButton btnPay;
    public static Context context;
    public static EditText editAddress;
    public static EditText editCity;
    public static EditText editMail;
    public static EditText editName;
    public static EditText editPhone;
    public static String mail;
    public static String name;
    public static String orderId;
    public static String phone;
    public static ProgressDialog progressDialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_buyer);
        context = this;
        editName = (EditText) findViewById(R.id.editName);
        editMail = (EditText) findViewById(R.id.editMail);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editCity = (EditText) findViewById(R.id.editCity);
        editAddress = (EditText) findViewById(R.id.editAddress);
        ImageButton imageButton = (ImageButton) findViewById(R.id.btnPay);
        btnPay = imageButton;
        imageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ActivityBuyer.name = ActivityBuyer.editName.getText().toString();
                if (ActivityBuyer.name.isEmpty()) {
                    Toast.makeText(ActivityBuyer.context, "გთხოვთ, შეავსოთ სახელის ნაწილი", Toast.LENGTH_LONG).show();
                    return;
                }
                ActivityBuyer.mail = ActivityBuyer.editMail.getText().toString();
                if (ActivityBuyer.mail.isEmpty()) {
                    Toast.makeText(ActivityBuyer.context, "გთხოვთ, შეავსოთ ელექტრონული ფოსტის ნაწილი", Toast.LENGTH_LONG).show();
                    return;
                }
                ActivityBuyer.phone = ActivityBuyer.editPhone.getText().toString();
                String str = "გთხოვთ, შეავსოთ ნომრის ნაწილი";
                if (ActivityBuyer.phone.isEmpty() || !ActivityBuyer.phone.startsWith("5") || ActivityBuyer.phone.length() != 9) {
                    Toast.makeText(ActivityBuyer.context, str, Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Integer.parseInt(ActivityBuyer.phone);
                    ActivityBuyer.address = ActivityBuyer.editAddress.getText().toString();
                    if (ActivityBuyer.address.isEmpty()) {
                        Toast.makeText(ActivityBuyer.context, "გთხოვთ, შეავსოთ მისამართის ნაწილი", Toast.LENGTH_LONG).show();
                        return;
                    }
                    new CreateOrder().execute(new Void[0]);
                    ActivityBuyer.context.startActivity(new Intent(ActivityBuyer.context, ActivityPay.class));
                } catch (NumberFormatException unused) {
                    Toast.makeText(ActivityBuyer.context, str, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
