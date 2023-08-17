package com.codewithmuddasir.onlinebanking;

import static com.codewithmuddasir.onlinebanking.helper.Setting.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.codewithmuddasir.onlinebanking.databinding.ActivityCardDetailBinding;
import com.codewithmuddasir.onlinebanking.helper.Util;

import java.util.Random;

public class CardDetail extends AppCompatActivity {

    ActivityCardDetailBinding binding;

    private Util util;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_detail);

        util = new Util();

        progressDialog = new ProgressDialog(CardDetail.this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        binding.btnPay.setOnClickListener(view -> {

            if (!isFieldsEmpty(binding.payAmt)) {
                progressDialog.show();

                util.sendCardDetail(this, model.getUserId(), binding.payAmt.getText().toString(), () -> {
                    progressDialog.dismiss();
                    startActivity(new Intent(this, CreditActivity.class));
                    finish();
                });
            }
        });

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

}