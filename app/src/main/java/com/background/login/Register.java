package com.background.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = findViewById(R.id.emailET);
        final EditText mobile = findViewById(R.id.mobileET);

        final EditText password = findViewById(R.id.passwordET);
        final EditText conPassword = findViewById(R.id.conPasswordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conPasswordIcon = findViewById(R.id.conPasswordIcon);

        final AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);
        final TextView signInBtn = findViewById(R.id.signInBtn);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Checking if Password is showing or not.
                if (passwordShowing) {
                    passwordShowing = false;

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_show);
                } else {
                    passwordShowing = true;

                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.password_hide);
                }
//                move the cursor at last of the text
                password.setSelection(password.length());
            }
        });

        conPasswordIcon.setOnClickListener(new View.OnClickListener()

            @Override
                    public void onCLick(View v){
            //                Checking if Password is showing or not.
            if (conPasswordShowing) {
                conPasswordShowing = false;

                conPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                conPasswordIcon.setImageResource(R.drawable.password_show);
            } else {
                conPasswordShowing = true;

                conPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                conPasswordIcon.setImageResource(R.drawable.password_hide);
            }
//            move the cursor at last of the text
            conPassword.setSelection(conPassword.length());
        }
    });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                final String getMobileTxt = mobile.getText().toString();
                final String getEmailTxt = email.getText().toString();
//                Opening OTP verification Activity along with Mobile and Email

            Intent intent = new Intent(action Register.this, OTPVerification.class);
            intent.putExtra("mobile",getMobileTxt);
            intent.putExtra("email", getEmailTxt);

            startActivity(intent);

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                finish();
            }
        });
    }
}