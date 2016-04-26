package com.pass.ann.autorization.ui;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.backendless.BackendlessUser;
import com.pass.ann.autorization.R;
import com.pass.ann.autorization.SimpleRecyclerAdapter;

/**
 * Created by ivan.k on 26.04.2016.
 */
public class UserVH extends SimpleRecyclerAdapter.BaseViewHolder<BackendlessUser> {


    @Bind(R.id.fname)
    TextView fname;

    public UserVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void fill(BackendlessUser user) {
        String s = "";
        s+=(String) user.getProperty("fname") +" " + (String) user.getProperty("lname")+"\n";
        s+=user.getEmail()+"\n";
        s+="phone: "+(String) user.getProperty("phone")+"\n";
        s+="b day" + (String) user.getProperty("bday")+"\n";
        fname.setText(s);

    }
}
