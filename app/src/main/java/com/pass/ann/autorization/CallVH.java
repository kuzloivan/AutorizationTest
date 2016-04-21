package com.pass.ann.autorization;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ivan.k on 21.04.2016.
 */
public class CallVH extends SimpleRecyclerAdapter.BaseViewHolder<call_shedule> {
    @Bind(R.id.text)
    TextView view;


    public CallVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void fill(call_shedule object) {
        view.setText(object.start+" - " + object.end);
    }

}
