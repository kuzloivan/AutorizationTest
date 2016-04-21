package com.pass.ann.autorization.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.pass.ann.autorization.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    @Bind(R.id.login)
    EditText login;
    @Bind(R.id.pass)
    EditText pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_btn, R.id.registration_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                Backendless.UserService.login(login.getText().toString(), pass.getText().toString(), new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser backendlessUser) {
                        MainActivity.start(LoginActivity.this);
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        Toast.makeText(LoginActivity.this, backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.registration_btn:
                RegistrationActivity.start(LoginActivity.this);
                break;
        }
    }
}

