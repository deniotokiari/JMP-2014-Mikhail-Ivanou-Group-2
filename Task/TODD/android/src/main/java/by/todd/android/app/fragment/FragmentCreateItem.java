package by.todd.android.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import by.todd.android.R;
import by.todd.android.app.ActivityHome;
import by.todd.android.app.Success;
import by.todd.entity.Task;

/**
 * Created by sergey on 14.11.2014.
 */
public class FragmentCreateItem extends Fragment {

    public static FragmentCreateItem newInstance() {
        return new FragmentCreateItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void save() {
        String title = getEditText(R.id.taskTitle);
        String content = getEditText(R.id.taskContent);
        String tags = getEditText(R.id.taskTags);

        String user = "";
        String timestamp = "";
        Task task = new Task(user, title, content, timestamp, tags);
        JSONObject jo=null;
        try {
            jo = task.toJSONObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ((ActivityHome) getActivity()).post(jo, new Success() {
            @Override
            public void success(String content) {

            }
        });

    }

    private String getEditText(int id) {
        return ((TextView) findViewById(id)).getText().toString();
    }

    private View findViewById(int id) {
        View view = getView();
        if (view == null) {
            return null;
        }
        return view.findViewById(id);
    }

}
