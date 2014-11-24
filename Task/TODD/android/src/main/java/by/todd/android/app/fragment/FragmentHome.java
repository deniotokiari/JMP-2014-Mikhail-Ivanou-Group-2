package by.todd.android.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.todd.android.R;
import by.todd.android.ToddApplication;
import by.todd.android.app.ActivityHome;
import by.todd.android.app.Success;
import by.todd.android.app.adapter.TaskHomeAdapter;
import by.todd.api.Api;
import by.todd.entity.Task;

/**
 * Created by sergey on 14.11.2014.
 */
public class FragmentHome extends Fragment {

    public static final String TAG = "FragmentHome";
    private RecyclerView mRecyclerView;
    private TaskHomeAdapter mAdapter;

    public static FragmentHome newInstance() {
        return new FragmentHome();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new TaskHomeAdapter(Collections.EMPTY_LIST, R.layout.item_task_home, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        ((ActivityHome) getActivity()).get(new Success() {
            @Override
            public void success(String content) {
                try {
                    final JSONObject jo = new JSONObject(content);
                    final JSONArray tasks = jo.getJSONArray(Task.TASKS);

                    int length = tasks.length();
                    List<Task> list = new ArrayList<Task>(length);

                    for (int i = 0; i < length; i++) {
                        list.add(new Task((JSONObject) tasks.get(i)));
                    }
                    mAdapter.setData(list);
                } catch (JSONException e) {
                }
            }
        }, Api.BASE_PATH + Api.GetTask.PATH + ToddApplication.getOwner());
    }


    private View findViewById(int id) {
        View view = getView();
        if (view == null) {
            return null;
        }
        return view.findViewById(id);
    }

}
