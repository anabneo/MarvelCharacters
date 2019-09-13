package com.example.marvelcharacters.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.marvelcharacters.R;
import com.example.marvelcharacters.repository.RegisterDataBaseHelper;

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText mTextEmail;
    EditText mTextPassword;
    EditText mCfnPassword;
    Button mButtonRegister;

    RegisterDataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new RegisterDataBaseHelper(this);

        mTextEmail = findViewById(R.id.et_registerEmail);
        mTextPassword = findViewById(R.id.et_registerPassword);
        mCfnPassword = findViewById(R.id.et_cfnPassword);
        mButtonRegister = findViewById(R.id.btn_register);

        mTextEmail.setOnClickListener(this);
        mTextPassword.setOnClickListener(this);
        mCfnPassword.setOnClickListener(this);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mTextEmail.getText().toString();
                String password = mTextPassword.getText().toString();
                String cfnPassword = mCfnPassword.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(Register.this, "Email inválido", Toast.LENGTH_LONG).show();

                } else if (password.equals("") || cfnPassword.equals("")) {
                    Toast.makeText(Register.this, "Insira uma senha", Toast.LENGTH_LONG).show();

                } else if (!password.equals(cfnPassword)) {
                    Toast.makeText(Register.this, "Senhas não correspondentes", Toast.LENGTH_LONG).show();

                } else {
                    long res = db.Login(email, password);
                    if (res > 0) {
                        Toast.makeText(Register.this, "Registro Efetuado", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Register.this, Login.class);

                        startActivity(intent);

                    } else {
                        Toast.makeText(Register.this, "Registro Inválido, tente novamente", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

    }

    @Override
    public void onClick(View view) {
        mTextEmail.getText().clear();
        mTextPassword.getText().clear();
        mCfnPassword.getText().clear();
    }
}
