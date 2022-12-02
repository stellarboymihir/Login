package com.background.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class OTPVerification extends AppCompatActivity {

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.length() > 0){

                if(selectedETPosition == 0){

                    selectedETPosition = 1;
                    showKeyboard(otpEt2);
                }else if(selectedETPosition == 1){

                    selectedETPosition = 2;
                    showKeyboard(otpEt3);
                }else if(selectedETPosition == 2){

                    selectedETPosition = 3;
                    showKeyboard(otpEt4);
                }
            }
        }
    };

    private EditText otpEt1, otpEt2, otpEt3, otpEt4;
    private TextView resendBtn;

//    true after every 60 seconds
    private boolean resendEnabled = false;

//    resend time in seconds
    private int resendTime = 60;

    private int selectedETPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        otpEt1 = findViewById(R.id.otpET1);
        otpEt2 = findViewById(R.id.otpET2);
        otpEt3 = findViewById(R.id.otpET3);
        otpEt4 = findViewById(R.id.otpET4);

        resendBtn = findViewById(R.id.resendBtn);

        final Button verifyBtn = findViewById(R.id.verifyBtn);
        final TextView otpEmail = findViewById(R.id.otpEmail);
        final TextView otpMobile = findViewById(R.id.otpMobile);

//       Getting Email and Mobile from Register Activity through Intent
        final String getEmail = getIntent().getStringExtra("email");
        final String getMobile = getIntent().getStringExtra("mobile");

//        Setting email and mobile to TextView
        otpEmail.setText(getEmail);
        otpMobile.setText(getMobile);

        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);

//        by default open keyboard at otpEt1
        showKeyboard(otpEt1);

//        start resend count down timer
        startCountDownTimer();

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resendEnabled){
//                    Handle your resend code

//                    start new resend count down timer
                }
            }
        });

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String generateOtp = otpEt1.getText().toString()+otpEt2.getText().toString()+otpEt3.getText().toString()+otpEt4.getText().toString();

                if(generateOtp.length() == 4){

//                    Handle your OTP verification here
                }
            }
        });
    }


    private void showKeyboard(EditText otpET){
        otpET.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTimer(){

        resendEnabled = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendTime * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resendBtn.setText("Resend Code (" + (millisUntilFinished/60)+" )" );
            }

            @Override
            public void onFinish() {
                resendEnabled = true;
                resendBtn.setText("Resend Code");
                resendBtn.setTextColor(getResources().getColor(R.color.primary));
            }
        }.start();
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onKeyUp(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_DEL){

            if(selectedETPosition == 3){

                selectedETPosition = 2;
                showKeyboard(otpEt3);
            }else if(selectedETPosition == 2){

                selectedETPosition = 1;
                showKeyboard(otpEt2);
            }else if(selectedETPosition == 1){

                selectedETPosition = 0;
                showKeyboard(otpEt1);
            }
            return true;

        }else {
            return super.onKeyUp(keyCode, event);
        }
    }
}