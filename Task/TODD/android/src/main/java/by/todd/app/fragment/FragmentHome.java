package by.todd.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.todd.R;

/**
 * Created by sergey on 14.11.2014.
 */
public class FragmentHome extends Fragment {

    private RecyclerView mRecyclerView;
    private CountryAdapter mAdapter;

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

        List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        list.addAll(list);
        mAdapter = new CountryAdapter(list, R.layout.item_home, getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    private View findViewById(int id) {
        View view = getView();
        if (view == null) {
            return null;
        }
        return view.findViewById(id);
    }

    private static class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
        private List<String> countries;
        private int rowLayout;
        private Context mContext;

        public CountryAdapter(List<String> countries, int rowLayout, Context context) {
            this.countries = countries;
            this.rowLayout = rowLayout;
            this.mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            String country = countries.get(i);
            viewHolder.countryName.setText(country);
            viewHolder.countryImage.setImageResource(R.drawable.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return countries == null ? 0 : countries.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView countryName;
            public ImageView countryImage;

            public ViewHolder(View itemView) {
                super(itemView);
                countryName = (TextView) itemView.findViewById(R.id.countryName);
                countryImage = (ImageView) itemView.findViewById(R.id.countryImage);
            }
        }
    }
}
