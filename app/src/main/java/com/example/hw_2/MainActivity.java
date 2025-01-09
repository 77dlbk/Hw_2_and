package com.example.hw_2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText emailInput = findViewById(R.id.email);
        EditText passwordlInput = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.button);
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        TextView verificationText = findViewById(R.id.verification);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsForEmptyValues(emailInput, passwordlInput, loginButton); // Вставил вызов checkFieldsForEmptyValues
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        emailInput.addTextChangedListener(textWatcher);
        passwordlInput.addTextChangedListener(textWatcher);
        checkFieldsForEmptyValues(emailInput, passwordlInput, loginButton);

        if (!emailInput.getText().toString().isEmpty() || !passwordlInput.getText().toString().isEmpty()) {

            loginButton.setBackgroundColor(getResources().getColor(R.color.orange));
        } else {

            loginButton.setBackgroundColor(getResources().getColor(R.color.gray));
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordlInput.getText().toString();
                if (email.equals("admin@gmail.com") && password.equals("admin")) {
                    Snackbar.make(findViewById(R.id.main), "Вы успешно зарегестрировались!", Snackbar.LENGTH_SHORT).show();
                    emailInput.animate().alpha(0f).setDuration(500).withEndAction(() -> emailInput.setVisibility(View.GONE));
                    passwordlInput.animate().alpha(0f).setDuration(500).withEndAction(() -> passwordlInput.setVisibility(View.GONE));
                    loginButton.animate().alpha(0f).setDuration(500).withEndAction(() -> loginButton.setVisibility(View.GONE));
                    verificationText.animate().alpha(0f).setDuration(500).withEndAction(() -> verificationText.setVisibility(View.GONE));
                    tvWelcome.setVisibility(View.VISIBLE);
                } else {
                    Snackbar.make(findViewById(R.id.main), "Неправильный логин и пароль", Snackbar.LENGTH_SHORT).show();
                }
            }

        });;
    }

    private void checkFieldsForEmptyValues(EditText emailInput, EditText passwordInput, Button loginButton) {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()) {
            loginButton.setEnabled(true);
            loginButton.setBackgroundColor(getResources().getColor(R.color.orange));
        } else {
            loginButton.setEnabled(false);
            loginButton.setBackgroundColor(getResources().getColor(R.color.gray));
        }
    }



}