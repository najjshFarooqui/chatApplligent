package com.example.applligent.chatapplligent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActvity extends AppCompatActivity {
  private EditText userNAme;
  private EditText userPassword;
  private Button login;
  Toolbar toolbar;
  private FirebaseAuth mAuth;
  private static final String TAG="login success";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);
        mAuth= FirebaseAuth.getInstance();
        userNAme=(EditText)findViewById(R.id.userName);
        userPassword=(EditText)findViewById(R.id.userPassword);
        login=(Button)findViewById(R.id.signInButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName= userNAme.getText().toString();
                String password= userPassword.getText().toString();
                if(!TextUtils.isEmpty(userName)|| !TextUtils.isEmpty(password)){
                    setFinishOnTouchOutside(false);
                    loginUser(userName,password);
                }
            }
        });
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login page");

    }
    private void  loginUser(String userName, String password){
        mAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActvity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
