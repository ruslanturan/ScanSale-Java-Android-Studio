package com.IOStarter.scansale;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.BarcodeDetector.Builder;
import java.io.IOException;

public class ActivityScanning extends AppCompatActivity {
    public static BarcodeDetector barcodeDetector;
    public static CameraSource cameraSource;
    public static Context context;
    public static CardView crdCam;

    /* renamed from: id */
    public static String f50id;
    public static RelativeLayout layCam;
    public static SurfaceView surfaceView;
    public static TextView textView;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_scanning);
        context = this;
        layCam = (RelativeLayout) findViewById(R.id.layCam);
        LayoutParams layoutParams = new LayoutParams((ActivityMain.android_width * 27) / 41, (ActivityMain.android_width * 27) / 41);
        layoutParams.setMargins(0, (ActivityMain.android_height * 11) / 73, 0, 0);
        layCam.setLayoutParams(layoutParams);
        crdCam = (CardView) findViewById(R.id.crdCam);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((ActivityMain.android_width * 22) / 41, (ActivityMain.android_width * 22) / 41);
        layoutParams2.setMargins(0, 0, 0, 0);
        crdCam.setLayoutParams(layoutParams2);
        crdCam.setRadius((float) ((ActivityMain.android_width * 11) / 41));
        surfaceView = (SurfaceView) findViewById(R.id.cameraPreview);
        textView = (TextView) findViewById(R.id.scanningText);
        LayoutParams layoutParams3 = new LayoutParams(-1, 100);
        layoutParams3.setMargins(0, (ActivityMain.android_width * 11) / 41, 0, 0);
        textView.setLayoutParams(layoutParams3);
        barcodeDetector = new Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640, 480).build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            }

            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(context.getApplicationContext(), "android.permission.CAMERA") == 0) {
                    try {
                        cameraSource.start(surfaceHolder);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            public void release() {
            }

            public void receiveDetections(Detections<Barcode> detections) {
                final SparseArray<Barcode> detectedItems = detections.getDetectedItems();
                if (detectedItems.size() != 0) {
                    textView.post(new Runnable() {
                        public void run() {
                            String str = ((Barcode) detectedItems.valueAt(0)).displayValue;
                            if (str.startsWith("https://myoutlet.ge/api/barcode/get/")) {
                                textView.setText("მზად არის");
                                Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(500);
                                ActivityScanning.f50id = str.split("get/")[1];
                                ActivityScanning.context.startActivity(new Intent(ActivityScanning.context, ActivityProductswithQr.class));
                                return;
                            }
                            ActivityScanning.textView.setText("QR კოდი არასწორია");
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                        }
                    });
                }
            }
        });
    }

    public void onBackPressed() {
        context.startActivity(new Intent(context, ActivityHome.class));
    }
}
