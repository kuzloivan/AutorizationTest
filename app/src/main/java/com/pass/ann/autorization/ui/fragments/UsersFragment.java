package com.pass.ann.autorization.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.pass.ann.autorization.CallShedule;
import com.pass.ann.autorization.R;
import com.pass.ann.autorization.SimpleRecyclerAdapter;
import com.pass.ann.autorization.ui.UserVH;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ivan.k on 26.04.2016.
 */
public class UsersFragment extends BaseFragment {
    @Bind(R.id.list)
    RecyclerView list;
    private SimpleRecyclerAdapter<BackendlessUser> adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_users;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new SimpleRecyclerAdapter<BackendlessUser>(UserVH.class,R.layout.item_user);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list.setAdapter(adapter);
        baseActivity.showDialog();
        AsyncCallback<BackendlessCollection<BackendlessUser>> asyncCallback = new AsyncCallback<BackendlessCollection<BackendlessUser>>
                () {
            @Override
            public void handleResponse(BackendlessCollection<BackendlessUser> callBackendlessCollection) {
                adapter.addAll(callBackendlessCollection.getData());
                adapter.notifyDataSetChanged();
                baseActivity.hideDialog();
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                baseActivity.hideDialog();
            }
        };
        Backendless.Data.of(BackendlessUser.class).find(asyncCallback);
    }
}
