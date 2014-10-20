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
public class FragmentTwo extends Fragment {

    private static Fragment mCurrent;

    public static FragmentTwo newInstance(int sectionNumber) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putInt(MyActivity.PlaceholderFragment.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentTwo() {
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
        final View v = getView();
        if (v == null) {
            return;
        }
        //don't create temporary object for view
        ((TextView) v.findViewById(R.id.section_label)).setText(getString(R.string.click_me));
        ((ImageView) v.findViewById(R.id.section_icon)).setImageBitmap(IconManager.get().getIcon(SocialType.LINKED_IN));
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
            final View v = mCurrent.getView();
            if (v == null) {
                return;
            }
            ((ImageView) v.findViewById(R.id.section_icon)).setImageBitmap(Utils.getIcon(XApplication.getContext(), SocialType.GOOGLE_PLUS));
        }
    };

}
