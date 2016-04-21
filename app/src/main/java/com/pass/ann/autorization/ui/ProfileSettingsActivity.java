package com.pass.ann.autorization.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
 * Created by ivan.k on 21.04.2016.
 */
public class ProfileSettingsActivity extends AppCompatActivity {


    @Bind(R.id.first_name)
    EditText firstName;
    @Bind(R.id.last_name)
    EditText lastName;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.birth_day)
    TextView birthDay;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ProfileSettingsActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.birth_day, R.id.save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.birth_day:
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthDay.setText(year+"."+monthOfYear+"."+dayOfMonth);
                    }
                },2016,1,1);
                dialog.show();
                break;
            case R.id.save:
                BackendlessUser user = Backendless.UserService.CurrentUser();
                user.setProperty("fname", firstName.getText().toString());
                user.setProperty("lname", lastName.getText().toString());
                user.setProperty("phone", phone.getText().toString());
                user.setProperty("bday", birthDay.getText().toString());
                Backendless.Persistence.save(user, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser backendlessUser) {
                        MainActivity.start(ProfileSettingsActivity.this);
                    }

                    @Override
                    public void handleFault(BackendlessFault backendlessFault) {
                        Toast.makeText(ProfileSettingsActivity.this, backendlessFault.getMessage(), Toast
                                .LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

}
