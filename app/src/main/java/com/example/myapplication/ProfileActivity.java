package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private User user;
    private EditText etAge;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvUserInfo = findViewById(R.id.tv_user_info);
        etAge = findViewById(R.id.et_age);
        etEmail = findViewById(R.id.et_email);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnCancel = findViewById(R.id.btn_cancel);

        user = getIntent().getParcelableExtra("USER");

        if (user != null) {
            tvUserInfo.setText("Имя: " + user.getName() +
                    "\nВозраст: " + user.getAge() +
                    "\nEmail: " + user.getEmail());

            etAge.setText(String.valueOf(user.getAge()));
            etEmail.setText(user.getEmail());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newAge = Integer.parseInt(etAge.getText().toString());
                String newEmail = etEmail.getText().toString();

                user.setAge(newAge);
                user.setEmail(newEmail);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("UPDATED_USER", user);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}