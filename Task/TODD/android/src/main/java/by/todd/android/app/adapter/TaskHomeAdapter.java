package by.todd.android.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.todd.android.R;
import by.todd.entity.Task;

/**
 * Created by sergey on 23.11.2014.
 */
public class TaskHomeAdapter extends RecyclerView.Adapter<TaskHomeAdapter.ViewHolder> {
    private List<Task> mTasks;
    private int mRowLayout;
    private Context mContext;

    public TaskHomeAdapter(List<Task> tasks, int rowLayout, Context context) {
        mTasks = tasks;
        mRowLayout = rowLayout;
        mContext = context;
    }

    public void setData(List<Task> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(mRowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Task task = mTasks.get(i);
        viewHolder.mTaskTitle.setText(task.getTitle());
        viewHolder.mTaskContent.setText(task.getContent());
        viewHolder.mTaskTags.setText(task.getTags());
        viewHolder.mTaskTimestamp.setText(task.getTimestamp());

    }

    @Override
    public int getItemCount() {
        return mTasks == null ? 0 : mTasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTaskTitle;
        public TextView mTaskContent;
        public TextView mTaskTags;
        public TextView mTaskTimestamp;


        public ViewHolder(View itemView) {
            super(itemView);
            mTaskTitle = (TextView) itemView.findViewById(R.id.taskTitle);
            mTaskContent = (TextView) itemView.findViewById(R.id.taskContent);
            mTaskTags = (TextView) itemView.findViewById(R.id.taskTags);
            mTaskTimestamp = (TextView) itemView.findViewById(R.id.taskTimestamp);


        }
    }
}
