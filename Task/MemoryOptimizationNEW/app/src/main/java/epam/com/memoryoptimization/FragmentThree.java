package epam.com.memoryoptimization;

import android.app.Activity;
import android.app.Fragment;
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

    private static Fragment mCurrent;

    public static FragmentThree newInstance(int sectionNumber) {
        FragmentThree fragment = new FragmentThree();
        Bundle args = new Bundle();
        args.putInt(MyActivity.PlaceholderFragment.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentThree() {
        mCurrent = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        if (view == null) {
            return;
        }
        TextView viewById = (TextView) view.findViewById(R.id.section_label);
        viewById.setText(getString(R.string.click_me));
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
            View v = mCurrent.getView();
            if (v == null) {
                return;
            }
            ((ImageView) v.findViewById(R.id.section_icon)).setImageBitmap(Utils.getIcon(XApplication.getContext(), SocialType.GOOGLE_PLUS));
        }
    };
}
