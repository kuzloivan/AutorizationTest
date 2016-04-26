package com.pass.ann.autorization.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ivan.k on 26.04.2016.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    public void showDialog(){
        if(dialog == null){
            dialog  = new ProgressDialog(this);
            dialog.setCancelable(false);
            dialog.setMessage("Please wait");
        }
        dialog.show();
    }

    public void hideDialog(){
        if(dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
