package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_REQUEST_CODE = 1;
    private EditText etUserName;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.et_user_name);
        tvResult = findViewById(R.id.tv_result);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                User user = new User(userName, 20, "pochta@gmail.com");

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("USER", user);
                startActivityForResult(intent, PROFILE_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PROFILE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                User updatedUser = data.getParcelableExtra("UPDATED_USER");
                tvResult.setText("Данные обновлены: " + updatedUser.getEmail() +
                        ", возраст: " + updatedUser.getAge());
            } else if (resultCode == RESULT_CANCELED) {
                tvResult.setText("Редактирование отменено");
            }
        }
    }
}