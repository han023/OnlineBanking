package com.codewithmuddasir.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codewithmuddasir.onlinebanking.databinding.ActivityLoginLoadingBinding;

public class LoginLoadingActivity extends AppCompatActivity {

    ActivityLoginLoadingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login_loading);

        ImageView imageView = (ImageView) findViewById(R.id.img);

        Glide.with(this)
                .asGif()
//                .load(R.drawable.loading)
                .into(imageView);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (savedInstanceState.getString("form").equals("Card")) {
                    // Code to start the second activity
                    Intent intent = new Intent(LoginLoadingActivity.this, CardDetail.class);
                    startActivity(intent);
                    finish();
                } else if (savedInstanceState.getString("form").equals("Credit")) {
                    startActivity(new Intent(LoginLoadingActivity.this, CreditActivity.class));
                    finish();
                } else if (savedInstanceState.getString("form").equals("Cong")) {
                    startActivity(new Intent(LoginLoadingActivity.this, CongratulationActivity.class));
                    finish();
                }

                // Finish the current activity
                finish();
            }
        }, 5000);

    }
}