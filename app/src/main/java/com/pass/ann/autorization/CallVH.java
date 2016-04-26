package com.pass.ann.autorization;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ivan.k on 21.04.2016.
 */
public class CallVH extends SimpleRecyclerAdapter.BaseViewHolder<CallShedule> {
    @Bind(R.id.num)
    TextView num;
    @Bind(R.id.start)
    TextView start;
    @Bind(R.id.end)
    TextView end;


    public CallVH(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void fill(CallShedule object) {
        num.setText(object.number+"");
        start.setText(object.start);
        end.setText(object.end);
    }

}
