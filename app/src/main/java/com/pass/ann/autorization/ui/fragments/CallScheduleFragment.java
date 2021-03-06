package com.pass.ann.autorization.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.pass.ann.autorization.CallShedule;
import com.pass.ann.autorization.CallVH;
import com.pass.ann.autorization.R;
import com.pass.ann.autorization.SimpleRecyclerAdapter;
import com.pass.ann.autorization.ui.BaseActivity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ivan.k on 21.04.2016.
 */
public class CallScheduleFragment extends BaseFragment {

    @Bind(R.id.list)
    RecyclerView list;
    private SimpleRecyclerAdapter<CallShedule> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_calls_schedule;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Calls");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SimpleRecyclerAdapter<CallShedule>(CallVH.class, R.layout
                .item_call);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(adapter);
        baseActivity.showDialog();
        AsyncCallback<BackendlessCollection<CallShedule>> asyncCallback = new AsyncCallback<BackendlessCollection<CallShedule>>() {
            @Override
            public void handleResponse(BackendlessCollection<CallShedule> callBackendlessCollection) {
                List<CallShedule> data = callBackendlessCollection.getData();
                Collections.sort(data, new Comparator<CallShedule>() {
                    @Override
                    public int compare(CallShedule lhs, CallShedule rhs) {
                        return lhs.number - rhs.number;
                    }
                });
                adapter.addAll(data);
                adapter.notifyDataSetChanged();
                baseActivity.hideDialog();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                baseActivity.hideDialog();
            }
        };
        Backendless.Data.of(CallShedule.class).find(asyncCallback);
    }
}
