package com.example.marvelcharacters.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.marvelcharacters.R;
import com.example.marvelcharacters.repository.RegisterDataBaseHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText mTextEmail;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextRegister;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signin;
    int RC_SIGN_IN = 0;

    RegisterDataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new RegisterDataBaseHelper(this);

        mTextEmail = findViewById(R.id.etEmail);
        mTextPassword = findViewById(R.id.etPassword);
        mButtonLogin = findViewById(R.id.btn_sign_in);
        mTextRegister = findViewById(R.id.txt_register);

        mTextEmail.setOnClickListener(this);
        mTextPassword.setOnClickListener(this);

        signin = findViewById(R.id.sign_in_button);
        signin.setSize(SignInButton.SIZE_STANDARD);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        startActivity(new Intent(getBaseContext(), MainActivity.class));

                        break;
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String email = mTextEmail.getText().toString().trim();
                String password = mTextPassword.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(Login.this, "Por favor, insira um email", Toast.LENGTH_LONG).show();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Login.this, "Insert an valid Email address", Toast.LENGTH_LONG).show();
                } else if (password.equals("")) {
                    Toast.makeText(Login.this, "Insira uma senha", Toast.LENGTH_LONG).show();

                } else {
                    String res = db.LoginValidation(email, password);

                    if (res.equals("OK")) {
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    } else {
                        Toast.makeText(Login.this, "Registro Inválido, tente novamente", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        mTextRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
            }
        });

    }

    public void login_checked(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

        } catch (ApiException e) {

            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void onClick(View view) {
        mTextEmail.getText().clear();
        mTextPassword.getText().clear();
    }
}

