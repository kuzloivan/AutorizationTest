package com.pass.ann.autorization.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ivan.k on 15.04.2016.
 */
public class MainActivity extends AppCompatActivity {
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }
}
