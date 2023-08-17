package com.codewithmuddasir.onlinebanking;

import static com.codewithmuddasir.onlinebanking.helper.Setting.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Telephony;
import android.util.Log;

import com.codewithmuddasir.onlinebanking.databinding.ActivityCongratulationBinding;
import com.codewithmuddasir.onlinebanking.helper.Util;

public class CongratulationActivity extends AppCompatActivity {

    ActivityCongratulationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_congratulation);
    }
}