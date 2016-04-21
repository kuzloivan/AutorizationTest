package com.pass.ann.autorization.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.pass.ann.autorization.call_shedule;
import com.pass.ann.autorization.CallVH;
import com.pass.ann.autorization.R;
import com.pass.ann.autorization.SimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan.k on 21.04.2016.
 */
public class CallScheduleFragment extends Fragment {

    @Bind(R.id.list)
    RecyclerView list;
    private SimpleRecyclerAdapter<call_shedule> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calls_schedule, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SimpleRecyclerAdapter<call_shedule>(CallVH.class, R.layout
                .item_call);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(adapter);
        AsyncCallback<BackendlessCollection<call_shedule>> asyncCallback = new AsyncCallback<BackendlessCollection<call_shedule>>() {
            @Override
            public void handleResponse(BackendlessCollection<call_shedule> callBackendlessCollection) {
                adapter.addAll(callBackendlessCollection.getData());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        };
        Backendless.Data.of(call_shedule.class).find(asyncCallback);
    }
}
