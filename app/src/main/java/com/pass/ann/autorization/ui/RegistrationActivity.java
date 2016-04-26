package com.pass.ann.autorization.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
 * Created by ivan.k on 15.04.2016.
 */
public class RegistrationActivity extends BaseActivity {

    @Bind(R.id.login)
    EditText login;
    @Bind(R.id.pass)
    EditText pass;
    @Bind(R.id.pass_confirm)
    EditText passConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    public static void start(LoginActivity loginActivity) {
        Intent intent = new Intent(loginActivity, RegistrationActivity.class);
        loginActivity.startActivity(intent);
    }

    @OnClick(R.id.register)
    public void onClick() {
        if(pass.getText().toString().equals(passConfirm.getText().toString())){
            showDialog();
            BackendlessUser user = new BackendlessUser();
            user.setEmail(login.getText().toString());
            user.setPassword(pass.getText().toString());
            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser backendlessUser) {
                    ProfileSettingsActivity.start(RegistrationActivity.this);
                    Backendless.UserService.setCurrentUser(backendlessUser);
                    hideDialog();
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    Toast.makeText(RegistrationActivity.this, backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                }
            });
        }else {
            Toast.makeText(this, "wrong password", Toast.LENGTH_SHORT).show();
        }
    }
}
