package epam.com.memoryoptimization;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mike on 19.10.2014.
 */
public class FragmentThree extends Fragment {

    private static Fragment current;
    private Activity activity;

    public static FragmentThree newInstance(int sectionNumber) {
        FragmentThree fragment = new FragmentThree();
        Bundle args = new Bundle();
        args.putInt(MyActivity.PlaceholderFragment.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentThree() {
        current = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);
        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        IconManager iconManager = new IconManager(activity);
        Bitmap icon = iconManager.getIcon(SocialType.LINKED_IN);
        TextView viewById = (TextView) getView().findViewById(R.id.section_label);
        viewById.setText("Click Me");
        viewById.setOnClickListener(onClickListener);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MyActivity) activity).onSectionAttached(
                getArguments().getInt(MyActivity.PlaceholderFragment.ARG_SECTION_NUMBER));
    }


    private static View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View viewById = current.getView().findViewById(R.id.section_icon);
            ((ImageView) viewById).setImageBitmap(Utils.getIcon(XApplication.mCurrentActivity, SocialType.GOOGLE_PLUS));
        }
    };
}
