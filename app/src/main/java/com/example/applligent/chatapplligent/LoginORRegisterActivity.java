package com.example.applligent.chatapplligent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginORRegisterActivity extends AppCompatActivity {
    private Button login;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_orregister);
        login=(Button)findViewById(R.id.loginButton);
        register=(Button)findViewById(R.id.registerButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginORRegisterActivity.this, LoginActvity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginORRegisterActivity.this,RegisterActivity.class));
            }
        });

    }
}
