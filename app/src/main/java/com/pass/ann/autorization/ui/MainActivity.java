package com.pass.ann.autorization.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.pass.ann.autorization.R;

/**
 * Created by ivan.k on 15.04.2016.
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.email)
    TextView email;
    @Bind(R.id.fname)
    TextView fname;
    @Bind(R.id.lname)
    TextView lname;
    @Bind(R.id.birth_day)
    TextView birthDay;
    @Bind(R.id.phone)
    TextView phone;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BackendlessUser user = Backendless.UserService.CurrentUser();
        email.setText(user.getEmail());
        fname.setText((String)user.getProperty("fname"));
        lname.setText((String)user.getProperty("lname"));
        phone.setText((String)user.getProperty("phone"));
        birthDay.setText((String)user.getProperty("bday"));
    }
}
